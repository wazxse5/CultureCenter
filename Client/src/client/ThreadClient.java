package client;

import client.task.ConnectTask;
import client.task.ReceiveTask;
import message.GoodbyeMessage;
import message.LoginAnswerMessage;
import message.LoginRequestMessage;
import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                viewManager.setTitle(viewManager.getTitle() + " - połączono");
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

    public void sendLoginRequest(String name, String password) throws IOException {
        output.writeObject(new LoginRequestMessage(name, password));
    }

    private void handleReceivedMessage(Message message) {
        if (message instanceof LoginAnswerMessage) {
            LoginAnswerMessage loginAnswer = (LoginAnswerMessage) message;
            if (loginAnswer.isGood()) {
                viewManager.setLoggedView();
            } else {
                viewManager.getLoginViewController().setInfoLabel(loginAnswer.getInfoCode());
            }
        }
    }

    public void disconnect() {
        try {
            if (output != null) output.writeObject(new GoodbyeMessage());
        } catch (IOException ignored) { }
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) { }
        if (receiveTask != null) receiveTask.cancel(true);
        executor.shutdown();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
