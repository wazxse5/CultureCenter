package server.task;

import exception.AuthenticationException;
import javafx.concurrent.Task;
import message.LoginAnswerMessage;
import message.LoginRequestMessage;
import server.Client;
import server.DataLoader;
import server.Connection;

import java.sql.SQLException;

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
        String name = loginRequestMessage.getLogin();
        String password = loginRequestMessage.getPassword();
        try {
            Client client = dataLoader.login(name, password);
            connection.send(new LoginAnswerMessage(true, client.getId(), client.getLogin(), client.getName(), client.getSurname(), client.getMail()));
            connection.setClient(client);
            return client;
        } catch (AuthenticationException e) {
            connection.send(new LoginAnswerMessage(false, e.getCode()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
