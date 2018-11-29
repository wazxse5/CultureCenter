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
    @FXML private TextField mailTFR;


    public void initialize() {

    }

    public void sendRegisterRequest() {

        String login = loginTFR.getText();
        String password = passwordTFR.getText();
        String password2 = password1TFR.getText();
        String email = mailTFR.getText();

        if (!login.equals("") && !password.equals("") && !password2.equals("") && !email.equals("")) {

            if (password.equals(password2)==true) {
                if(!sendLoginCheckRequest()) {
                    threadClient.sendRegisterRequest(login, password, email);
                    setInfoLabel(4);
                } else setInfoLabel(1);
            } else setInfoLabel(2);
        }
        else setInfoLabel(3);
    }
    public boolean sendLoginCheckRequest(){
        String login = loginTFR.getText();
        boolean result = false;
        if(!login.equals("")){
           threadClient.sendLoginCheckRequest(login);
        }
        return result;
    }

    public void setInfoLabel(int code) {
        if (code == 1) infoLabel.setText("Podany login jest już zajęty");
        else if (code == 2) infoLabel.setText("Hasła się od siebie różnią");
        else if (code == 3) infoLabel.setText("Proszę wypełnić wszystkie pola");
        else if (code == 4) infoLabel.setText("Zarejestrowano użytkownika: " + loginTFR.getText());
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
