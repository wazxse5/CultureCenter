package client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty userSurname = new SimpleStringProperty();
    private StringProperty userMail = new SimpleStringProperty();
    private StringProperty userLogin = new SimpleStringProperty();

    public Connection(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    public void send(Message message) {
        try {
            output.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserData(String userName, String userSurname, String userMail, String userLogin) {
        this.userName.setValue(userName);
        this.userSurname.setValue(userSurname);
        this.userMail.setValue(userMail);
        this.userLogin.setValue(userLogin);
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

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname.get();
    }

    public StringProperty userSurnameProperty() {
        return userSurname;
    }

    public String getUserMail() {
        return userMail.get();
    }

    public StringProperty userMailProperty() {
        return userMail;
    }

    public String getUserLogin() {
        return userLogin.get();
    }

    public StringProperty userLoginProperty() {
        return userLogin;
    }
}
