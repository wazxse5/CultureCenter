package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField loginTF;
    @FXML private PasswordField passwordTF;
    @FXML private Label infoLabel;

    public void sendLoginRequest() {
        try {
            String login = loginTF.getText();
            String password = passwordTF.getText();
            threadClient.sendLoginRequest(login, password);
        } catch (IOException e) {
            infoLabel.setText("Nie można nawiązać połączenia");
        }
    }

    public void back() {
        viewManager.setInitScene();
    }

    public void setInfoLabel(String text) {
        infoLabel.setText(text);
    }

    public void setInfoLabel(int code) {
        if (code == 1) infoLabel.setText("Nazwa użytkownika jest w użyciu");
        else if (code == 2) infoLabel.setText("Nie ma takiego użytkownika");
        else if (code == 3) infoLabel.setText("Nieprawidłowe hasło");
    }

    public void RememberPassword(){
        viewManager.setInitScene();
    }

    public void restorePassword(){
        viewManager.setRestorePasswordScene();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
}
