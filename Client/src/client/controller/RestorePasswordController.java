package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RestorePasswordController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    public Label getTextLabel() {
        return textLabel;
    }

    @FXML private Label textLabel;
    @FXML private TextField emailRestoreTF;
    public void initialize() {

    }

    public void sendRestorePasswordRequest() {
        String email = emailRestoreTF.getText();
       threadClient.sendRestorePasswordRequest(email);
    }

    public void back(){
        viewManager.setLoginScene();

    }
    public void RestrictionEmail(){
        emailRestoreTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegexEmail())){
                emailRestoreTF.setText(oldValue);
            }
        });

    }



    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
