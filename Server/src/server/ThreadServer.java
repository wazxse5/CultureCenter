package server;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import message.*;
import server.task.AcceptingTask;
import server.task.AuthenticationTask;
import server.task.ReceiveTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServer {
    private ViewManager viewManager;
    private DataLoader dataLoader;

    private final ExecutorService executor;
    private AcceptingTask acceptingTask;
    private List<ReceiveTask> receiveTasks;
    private final ObservableList<Connection> connectedConnections;

    public ThreadServer() {
        this.dataLoader = new DataLoader();
        this.executor = Executors.newCachedThreadPool();
        this.receiveTasks = new ArrayList<>();
        this.connectedConnections = FXCollections.observableArrayList(Connection.extractor());
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            acceptingTask = new AcceptingTask(serverSocket);
            acceptingTask.valueProperty().addListener((observable, oldValue, newValue) -> handleNewConnection(newValue));
            executor.submit(acceptingTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleNewConnection(Connection connection) {
        connectedConnections.add(connection);

        ReceiveTask receiveTask = new ReceiveTask(connection);
        receiveTasks.add(receiveTask);
        receiveTask.valueProperty().addListener((observable, oldValue, newValue) -> handleReceivedMessage(connection, newValue));
        executor.submit(receiveTask);
    }

    private void handleReceivedMessage(Connection connection, Message message) {
        if (message instanceof WelcomeMessage) {
            connection.send(new WelcomeAnswerMessage());
        }
        if (message instanceof LoginRequestMessage) {
            LoginRequestMessage loginRequest = (LoginRequestMessage) message;
            AuthenticationTask authenticationTask = new AuthenticationTask(loginRequest, connection, dataLoader);
            executor.submit(authenticationTask);
        }
        if (message instanceof LogoutRequestMessage) {
            connection.send(new LogoutAnswerMessage());
            connection.setClient(null);
        }
        if (message instanceof GoodbyeMessage) {
            connectedConnections.remove(connection);
            connection.close();
        }
        if (message instanceof RegisterRequestMessage) {
            RegisterRequestMessage registerRequest = (RegisterRequestMessage) message;
            try {
                int res = dataLoader.register(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getLogin(), registerRequest.getPassword(), registerRequest.getMail());
                if (res == 0) connection.send(new RegisterAnswerMessage(true));
                else connection.send(new RegisterAnswerMessage(false, res));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof LogsCheckRequestMessage) {
            LogsCheckRequestMessage checkRequest = (LogsCheckRequestMessage) message;
            ArrayList<ArrayList<String>> result;
            try {
                result = dataLoader.getLogs(checkRequest.getLogin());
                connection.send(new LogsCheckAnswerMessage(result));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof EventsCheckRequestMessage) {
            EventsCheckRequestMessage checkRequest = (EventsCheckRequestMessage) message;
            ArrayList<ArrayList<String>> result;
            try {
                result = dataLoader.getEvents();
                System.out.println(result);
                connection.send(new EventsCheckAnswerMessage(result));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (message instanceof RepertoireCheckRequestMessage) {
            RepertoireCheckRequestMessage checkRequest = (RepertoireCheckRequestMessage) message;
            ArrayList<ArrayList<String>> result;
            try {
                result = dataLoader.getEvents();
                connection.send(new RepertoireCheckAnswerMessage(result));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (message instanceof RepertoireCheckRequestMessage) {
            RepertoireCheckRequestMessage checkRequest = (RepertoireCheckRequestMessage) message;
            ArrayList<ArrayList<String>> result;

            try {

                result = dataLoader.getRepertuar();
                connection.send(new RepertoireCheckAnswerMessage(result));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof ChangeUserDataRequestMessage) {
            ChangeUserDataRequestMessage changeMessage = (ChangeUserDataRequestMessage) message;
            if (connection.getClient() != null) {
                ChangeUserDataAnswerMessage answerMessage = dataLoader.changeUserData(connection.getClient().getLogin(), changeMessage);
                connection.send(answerMessage);
            }
        }
        if(message instanceof AddRepertuarRequestMessage){
            AddRepertuarRequestMessage addRepertuarMessage = (AddRepertuarRequestMessage) message;
                dataLoader.addRepertoire(((AddRepertuarRequestMessage) message).getName());
        }
    }

    public boolean addEmployee(String name, String surname, String department, String login, String password, int salary) {
        return dataLoader.addEmployee(name, surname, department, login, password, salary);
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public ListProperty<Connection> connectedConnectionsProperty() {
        return new SimpleListProperty<>(connectedConnections);
    }

    public void close() {
        if (executor != null) executor.shutdown();
        if (acceptingTask != null) acceptingTask.cancel(true);
        for (Connection c : connectedConnections) c.close();
        for (ReceiveTask r : receiveTasks) r.cancel(true);
        Platform.exit();
    }
}