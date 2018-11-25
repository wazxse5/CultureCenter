package client;

import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Connection {
    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Connection(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    public void send(Message message) {
        try {
            output.writeObject(message);
        } catch (IOException ignored) {
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

}
