package server;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private static int idCounter = 0;
    private int id;

    private final Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Client client;

    private StringProperty description = new SimpleStringProperty();

    public Connection(Socket socket) {
        this.id = idCounter++;
        this.socket = socket;
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        description.setValue("Połączenie id=" + id + " - niezalogowano");
    }


    public void send(Message message) {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (!socket.isClosed()) socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Callback<Connection, Observable[]> extractor() {
        return (Connection c) -> new Observable[]{c.descriptionProperty()};
    }

    public int getId() {
        return id;
    }

    public void setClient(Client client) {
        this.client = client;

        String text = "Połączenie id=" + id;
        if (client == null) text += " - niezalogowano";
        else text += " - użytkownik " + client.getName();
        String finalText = text;
        Platform.runLater(() -> description.setValue(finalText));
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }
}
