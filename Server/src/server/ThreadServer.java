package server;

import message.*;
import server.task.AcceptingTask;
import server.task.AuthenticationTask;
import server.task.ReceiveTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadServer {
    private ViewManager viewManager;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private AcceptingTask acceptingTask;
    private Future acceptingTaskFuture;
    private List<ReceiveTask> receiveTasks = new ArrayList<>();

    private  DataLoader dataLoader;
    private final List<Connection> connectedConnections;

    public ThreadServer() {
        dataLoader = new DataLoader();
        connectedConnections = new ArrayList<>();
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            acceptingTask = new AcceptingTask(serverSocket);
            acceptingTask.valueProperty().addListener((observable, oldValue, newValue) -> handleNewConnection(newValue));
//            acceptingTaskFuture = executor.submit(acceptingTask);
            executor.submit(acceptingTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleNewConnection(Connection connection) {
        System.out.println("New connection: id=" + connection.getId());
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
        if (message instanceof GoodbyeMessage) {
            connectedConnections.remove(connection);
            connection.close();
        }
        if(message instanceof RegisterRequestMessage){
            RegisterRequestMessage registerRequest = (RegisterRequestMessage) message;
            dataLoader.addClient(registerRequest.getName(),registerRequest.getPassword(),registerRequest.getMail());
        }
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void close() {
        executor.shutdown();
//        if (acceptingTaskFuture != null) acceptingTaskFuture.cancel(true);
        if (acceptingTask != null) acceptingTask.cancel(true);
        for (Connection c : connectedConnections) c.close();
        for (ReceiveTask r : receiveTasks) r.cancel(true);
    }
}