package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField loginTFR;
    @FXML private PasswordField passwordTFR;
    @FXML private Label infoLabel;
    @FXML private PasswordField password1TFR;
    @FXML private TextField MailTFR;


    public void initialize() {

    }

    public void sendRegisterRequest() {

        String login = loginTFR.getText();
        String password = passwordTFR.getText();
        String password2 = password1TFR.getText();
        String email = MailTFR.getText();
        if (login != null && password != null && password2 != null & email != null) {
            //if(login) nie ma takiego loginu :){ - tylko wymaga polaczenia ;fffffffffffff
            if (password == password2)
                threadClient.sendRegisterRequest(login, password, email);
            else setInfoLabel(2);
        }
        //} else setInfoLabel(1);
        else setInfoLabel(3);
    }

    public void setInfoLabel(int code) {
        if (code == 1) infoLabel.setText("Nazwa użytkownika jest w użyciu");
        else if (code == 2) infoLabel.setText("Hasła się od siebie różnią");
        else if (code == 3) infoLabel.setText("Proszę wypełnić wszystkie pola");
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
    public void connect() {
        threadClient.connect("localhost", 8989);
    }

}
