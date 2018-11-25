package server.task;

import exception.AuthenticationException;
import javafx.concurrent.Task;
import message.LoginAnswerMessage;
import message.LoginRequestMessage;
import server.Client;
import server.DataLoader;
import server.Connection;

public class AuthenticationTask extends Task<Client> {
    private final LoginRequestMessage loginRequestMessage;
    private final Connection connection;
    private final DataLoader dataLoader;

    public AuthenticationTask(LoginRequestMessage loginRequestMessage, Connection connection, DataLoader dataLoader) {
        this.loginRequestMessage = loginRequestMessage;
        this.connection = connection;
        this.dataLoader = dataLoader;
    }


    @Override protected Client call() {
        String name = loginRequestMessage.getName();
        String password = loginRequestMessage.getPassword();
        try {
            Client client = dataLoader.login(name, password);
            connection.send(new LoginAnswerMessage(true));
            client.setConnection(connection);
            System.out.println("Connection id=" + connection.getId() + " - logged user " + name);
            return client;
        } catch (AuthenticationException e) {
            connection.send(new LoginAnswerMessage(false, e.getCode()));
        }

        System.out.println("end authentication");
        return null;
    }

}
