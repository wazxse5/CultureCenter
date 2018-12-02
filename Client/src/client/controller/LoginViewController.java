package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField loginTF;
    @FXML private PasswordField passwordTF;
    @FXML private Label infoLabel;
    @FXML private Button loginButton;
    @FXML private Button connectButton;

    public void connect() {
        threadClient.connect("localhost", 8989);
    }

    public void sendLoginRequest() {

        String login = loginTF.getText();
        String password = passwordTF.getText();
        threadClient.sendLoginRequest(login, password);
        infoLabel.setText("Nie można nawiązać połączenia");

    }

    public void back() {
        viewManager.clearContentPane();
    }

    public void setInfoLabel(String text) {
        infoLabel.setText(text);
    }

    public void setInfoLabel(int code) {
        if (code == 1) infoLabel.setText("Nazwa użytkownika jest w użyciu");
        else if (code == 2) infoLabel.setText("Nie ma takiego użytkownika");
        else if (code == 3) infoLabel.setText("Nieprawidłowe hasło");
    }

    public void RememberPassword() {
        viewManager.setInitScene();
    }

    public void restorePassword() {
        viewManager.setRestorePasswordScene();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        loginButton.visibleProperty().bind(threadClient.connectedProperty());
        connectButton.visibleProperty().bind(threadClient.connectedProperty().not());
        loginTF.disableProperty().bind(threadClient.connectedProperty().not());
        passwordTF.disableProperty().bind(threadClient.connectedProperty().not());
    }
}
