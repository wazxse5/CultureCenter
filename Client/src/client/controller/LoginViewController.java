package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField loginTF;
    @FXML private PasswordField passwordTF;
    @FXML private Label infoLabel;
    @FXML private Button loginButton;
    @FXML private Button connectButton;
    @FXML private CheckBox employeeCheck;

    public void connect()
    {
        threadClient.connect("localhost", 8989);

    }

    public void RestrictionText(){
        loginTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                loginTF.setText(oldValue);
            }
        });
        passwordTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                passwordTF.setText(oldValue);
            }
        });
    }
    public void sendLoginRequest() {
        String login = loginTF.getText();
        String password = passwordTF.getText();
        threadClient.sendLoginRequest(login, password, employeeCheck.isSelected());
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
        loginTF.disabledProperty().addListener(observable -> loginTF.requestFocus());
        passwordTF.disableProperty().bind(threadClient.connectedProperty().not());
    }

    public void prepareFocus() {
        loginTF.requestFocus();
    }
}
