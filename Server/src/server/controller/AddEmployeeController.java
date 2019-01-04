package server.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.ThreadServer;
import server.ViewManager;

public class AddEmployeeController {
    private ViewManager viewManager;
    private ThreadServer threadServer;

    @FXML private TextField nameTF;
    @FXML private TextField surnameTF;
    @FXML private TextField departmentTF;
    @FXML private TextField loginTF;
    @FXML private PasswordField passwordPF;
    @FXML private PasswordField password1PF;
    @FXML private TextField salaryTF;
    @FXML private Label infoLabel;
    @FXML private Button confirmButton;

    public void initialize() {

    }

    public void confirm() {
        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String department = departmentTF.getText();
        String login = loginTF.getText();
        String password = passwordPF.getText();
        String password1 = password1PF.getText();
        try {
            int salary = Integer.parseInt(salaryTF.getText());
            if (password.equals(password1)) {
                boolean result = threadServer.addEmployee(name, surname, department, login, password, salary);
                if (result) infoLabel.setText("Dodano pracownika");
                else infoLabel.setText("Nie dodano pracownika");
            } else infoLabel.setText("Podane hasła różnią się");
        } catch (NumberFormatException e) {
            infoLabel.setText("Niepoprawna pensja");
        }
    }

    public void back() {
        viewManager.setInitScene();
    }

    public void clear() {
        nameTF.clear();
        surnameTF.clear();
        departmentTF.clear();
        loginTF.clear();
        passwordPF.clear();
        password1PF.clear();
        salaryTF.clear();
        infoLabel.setText("");
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadServer(ThreadServer threadServer) {
        this.threadServer = threadServer;
    }
}
