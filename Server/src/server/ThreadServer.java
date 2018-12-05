package server;

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

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private AcceptingTask acceptingTask;
    private List<ReceiveTask> receiveTasks = new ArrayList<>();

    private  DataLoader dataLoader;
    private final ObservableList<Connection> connectedConnections;

    public ThreadServer() {
        dataLoader = new DataLoader();
        connectedConnections = FXCollections.observableArrayList(Connection.extractor());
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
        if(message instanceof RegisterRequestMessage){
            RegisterRequestMessage registerRequest = (RegisterRequestMessage) message;
            try {
                int res = dataLoader.register(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getLogin(), registerRequest.getPassword(), registerRequest.getMail());
                if (res == 0) connection.send(new RegisterAnswerMessage(true));
                else connection.send(new RegisterAnswerMessage(false, res));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(message instanceof  LoginCheckAnswerMessage){
            LoginCheckRequestMessage checkRequest = (LoginCheckRequestMessage) message;
            if(dataLoader.getKnownClients().contains( checkRequest.getLogin())) {
                checkRequest.stateTrue();
            }
        }
        if (message instanceof LogsCheckRequestMessage){
            LogsCheckRequestMessage checkRequest = (LogsCheckRequestMessage) message;
            ArrayList<ArrayList<String>> result;
            try {
                result = dataLoader.getLogs(checkRequest.getLogin());
                connection.send(new LogsCheckAnswerMessage(result));
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public ListProperty<Connection> connectedConnectionsProperty() {
        return new SimpleListProperty<>(connectedConnections);
    }

    public void close() {
        executor.shutdown();
        if (acceptingTask != null) acceptingTask.cancel(true);
        for (Connection c : connectedConnections) c.close();
        for (ReceiveTask r : receiveTasks) r.cancel(true);
    }
}