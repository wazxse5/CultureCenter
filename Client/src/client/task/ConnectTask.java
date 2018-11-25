package client.task;

import client.Connection;
import javafx.concurrent.Task;
import message.WelcomeAnswerMessage;
import message.WelcomeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectTask extends Task<Connection> {
    private String host;
    private int port;

    public ConnectTask(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override public Connection call() throws Exception {
        Socket socket = new Socket(host, port);
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        output.writeObject(new WelcomeMessage());
        while (true) {
            Object answerObject = input.readObject();
            if (answerObject instanceof WelcomeAnswerMessage) {
                return new Connection(socket, input, output);
            }
            if (Thread.interrupted()) return null;
        }
    }
}
