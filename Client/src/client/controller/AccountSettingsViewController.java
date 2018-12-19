package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountSettingsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private Label userLoginLabel;
    @FXML private Label userNameLabel;
    @FXML private Label userSurnameLabel;
    @FXML private Label userMailLabel;


    public void initialize() {

    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void changePassword(){


    }

    public void removeAccount(){

    }
    public void changeLogin(){

    }

    public void setUserData(String login, String name, String surname, String mail) {
        this.userLoginLabel.setText(login);
        this.userNameLabel.setText(name);
        this.userSurnameLabel.setText(surname);
        this.userMailLabel.setText(mail);
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
