package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField nameTF;
    @FXML private TextField surnameTF;
    @FXML private TextField loginTF;
    @FXML private PasswordField passwordTF;
    @FXML private PasswordField password1TF;
    @FXML private TextField mailTF;
    @FXML private Label infoLabel;
    @FXML private Button registerButton;
    @FXML private Button connectButton;


    public void RestrictionEmail(){
        mailTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegexEmail())){
                mailTF.setText(oldValue);
            }
        });

    }
    public void RestrictionText(){
        nameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                nameTF.setText(oldValue);
            }
        });
        surnameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                surnameTF.setText(oldValue);
            }
        });
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
        password1TF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                password1TF.setText(oldValue);
            }
        });

    }

    public void initialize() {

    }

    public void sendRegisterRequest() {
        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String login = loginTF.getText();
        String password = passwordTF.getText();
        String password2 = password1TF.getText();
        String email = mailTF.getText();

        if (!login.equals("") && !password.equals("") && !password2.equals("") && !email.equals("")) {

            if (password.equals(password2)) {
                threadClient.sendRegisterRequest(name, surname, login, password, email);
                setInfoLabel(4);
            } else setInfoLabel(2);
        }
        else setInfoLabel(3);
    }

    public void setInfoLabel(int code) {
        if (code == 1) infoLabel.setText("Podany login jest już zajęty");
        else if (code == 2) infoLabel.setText("Hasła się od siebie różnią");
        else if (code == 3) infoLabel.setText("Proszę wypełnić wszystkie pola");
        else if (code == 4) infoLabel.setText("Zarejestrowano użytkownika: " + loginTF.getText());
    }


    public void back() {
        viewManager.setInitScene();
    }


    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        registerButton.visibleProperty().bind(threadClient.connectedProperty());
        connectButton.visibleProperty().bind(threadClient.connectedProperty().not());
        nameTF.disableProperty().bind(threadClient.connectedProperty().not());
        nameTF.disabledProperty().addListener(observable -> nameTF.requestFocus());
        surnameTF.disableProperty().bind(threadClient.connectedProperty().not());
        loginTF.disableProperty().bind(threadClient.connectedProperty().not());
        passwordTF.disableProperty().bind(threadClient.connectedProperty().not());
        password1TF.disableProperty().bind(threadClient.connectedProperty().not());
        mailTF.disableProperty().bind(threadClient.connectedProperty().not());
    }

    public void connect() {
        threadClient.connect("localhost", 8989);
    }

    public void prepareFocus() {
        nameTF.requestFocus();
    }
}
