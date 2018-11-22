package client.controller;

import client.ThreadClient;
import client.ViewManager;
import exception.NameIsInUseException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField loginTF;
    @FXML private PasswordField passwordTF;
    @FXML private Label infoLabel;

    public void login() {
        try {

            try {
                String login = loginTF.getText();
                String password = passwordTF.getText();

                boolean logged = threadClient.loginUser(login, password);
                if (logged) {
                    viewManager.setLoggedView();
                }
            } catch (ExecutionException e) {
                if (e.getCause() instanceof NameIsInUseException)
                    infoLabel.setText("Nazwa użytkownika jest w użyciu");
                else if (e.getCause() instanceof NoSuchUserException)
                    infoLabel.setText("Nie ma takiego użytkownika");
                else if (e.getCause() instanceof WrongPasswordException)
                    infoLabel.setText("Nieprawidłowe hasło");
                else infoLabel.setText("Nie można nawiązać połączenia");
            } catch (IOException | InterruptedException | TimeoutException e) {
                infoLabel.setText("Nie można nawiązać połączenia");
            }

        } catch (NumberFormatException exception) {
            infoLabel.setText("Niepoprawny port");
        }
    }

    public void back() {
        viewManager.setInitScene();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
}
