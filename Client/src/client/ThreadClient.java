package client;

import client.task.ConnectTask;
import client.task.LoginTask;
import client.task.ReceiveTask;
import message.GoodbyeMessage;
import message.Message;

import javax.swing.text.View;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadClient {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private ViewManager viewManager;
    private ExecutorService executor;
    private ReceiveTask receiveTask;


    public ThreadClient() {
        this.executor = Executors.newCachedThreadPool();
    }

    public boolean connect(String host, int port) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        socket = new Socket(host, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        ConnectTask connectTask = new ConnectTask(input, output);
        Future<Boolean> futureConnectionResult = executor.submit(connectTask);
        try {
            boolean connectionResult = futureConnectionResult.get(10, TimeUnit.SECONDS);
            if (connectionResult) {
                receiveTask = new ReceiveTask(input);
                receiveTask.valueProperty().addListener((observable, oldValue, newValue) -> handleReceivedMessage(newValue));
                executor.execute(receiveTask);
            }
            return connectionResult;
        } catch (TimeoutException timeoutException) {
            futureConnectionResult.cancel(true);
            throw timeoutException;
        }
    }

    public boolean loginUser(String name, String password) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        LoginTask loginTask = new LoginTask(input, output, name, password);
        Future<Boolean> futureLoginResult = executor.submit(loginTask);
        try {
            boolean loginResult = futureLoginResult.get(3, TimeUnit.SECONDS);
            return loginResult;
        } catch (TimeoutException timeoutException) {
            futureLoginResult.cancel(true);
            throw timeoutException;
        }
    }


    private void handleReceivedMessage(Message message) {

    }


    public void close() {
        try {
            if (output != null) output.writeObject(new GoodbyeMessage());
        } catch (IOException ignored) {}
        try {
            if (socket != null)socket.close();
        } catch (IOException ignored) {}
        if (receiveTask != null) receiveTask.cancel(true);
        executor.shutdown();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
