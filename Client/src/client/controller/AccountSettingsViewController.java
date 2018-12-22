package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class AccountSettingsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TabPane tabPane;
    @FXML private AnchorPane changeData;
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

    public void changePassword() {


    }

    public void removeAccount() {

    }

    public void changeLogin() {

    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        userLoginLabel.textProperty().bind(threadClient.getConnection().userLoginProperty());
        userNameLabel.textProperty().bind(threadClient.getConnection().userNameProperty());
        userSurnameLabel.textProperty().bind(threadClient.getConnection().userSurnameProperty());
        userMailLabel.textProperty().bind(threadClient.getConnection().userMailProperty());
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
