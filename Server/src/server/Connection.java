package server;

import javafx.concurrent.Task;
import message.Message;
import org.omg.CORBA.Object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Task {
    private static int idCounter = 0;
    private int id;

    private final Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Client client;

    public Connection(Socket socket) {
        this.id = idCounter++;
        this.socket = socket;
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override protected Object call() {
        return null;
    }

    public void send(Message message) {

        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.out.println("Connection.close");
        try {
            if (!socket.isClosed()) socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }
}
