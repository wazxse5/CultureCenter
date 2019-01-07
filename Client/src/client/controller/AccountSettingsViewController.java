package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import message.ChangeUserDataRequestMessage;

public class AccountSettingsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TabPane tabPane;
    @FXML private Label userLoginLabel;
    @FXML private Label userNameLabel;
    @FXML private Label userSurnameLabel;
    @FXML private Label userMailLabel;

    @FXML private TextField newNameTF;
    @FXML private TextField newSurnameTF;
    @FXML private TextField newMailTF;
    @FXML private CheckBox changeNameCheck;
    @FXML private CheckBox changeSurnameCheck;
    @FXML private CheckBox changeMailCheck;

    @FXML private PasswordField currentPasswordPF;
    @FXML private PasswordField newPasswordPF;
    @FXML private PasswordField newPassword1PF;

    @FXML private Label answerLabel;


    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener(observable -> {
            changeNameCheck.setSelected(false);
            changeSurnameCheck.setSelected(false);
            changeMailCheck.setSelected(false);
            currentPasswordPF.clear();
            answerLabel.setText("");
        });
    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void changeUserData() {
        boolean changeName = changeNameCheck.isSelected();
        boolean changeSurname = changeSurnameCheck.isSelected();
        boolean changeMail = changeMailCheck.isSelected();
        String newName = newNameTF.getText();
        String newSurname = newSurnameTF.getText();
        String newMail = newMailTF.getText();
        ChangeUserDataRequestMessage changeMessage = new ChangeUserDataRequestMessage(changeName, changeSurname, changeMail, newName, newSurname, newMail);
        threadClient.sendChangeUserDataRequest(changeMessage);
    }

    public void changePassword() {
        String currentPassword = currentPasswordPF.getText();
        String newPassword = newPasswordPF.getText();
        String newPassword1 = newPassword1PF.getText();
        if (newPassword.equals(newPassword1)) {
            ChangeUserDataRequestMessage changeMessage = new ChangeUserDataRequestMessage(true, currentPassword, newPassword);
            threadClient.sendChangeUserDataRequest(changeMessage);
        }
        else {
            setAnswerLabelText("Wpisane hasła różnią się");
        }

    }

    public void removeAccount() {

    }

    public void setAnswerLabelText(String text) {
        answerLabel.setText(text);
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
