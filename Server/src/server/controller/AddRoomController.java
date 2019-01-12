package server.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.ThreadServer;
import server.ViewManager;

public class AddRoomController {
    private ViewManager viewManager;
    private ThreadServer threadServer;

    @FXML private TextField numberTF;
    @FXML private TextField seatTF;
    @FXML private TextField rowTF;
    @FXML private TextField branchTF;
    @FXML private Label infoLabel;
    @FXML private Button confirmButton;

    public void initialize() {

    }

    public void confirm() {
        Integer number = Integer.parseInt(numberTF.getText());
        Integer seat= Integer.parseInt(seatTF.getText());
        Integer row = Integer.parseInt(rowTF.getText());
        Integer branch = Integer.parseInt(branchTF.getText());

        try {
                boolean result = threadServer.addRoom(number,seat,row,branch);
                if (result) infoLabel.setText("Dodano salę");
                else infoLabel.setText("Nie dodano sali");
        } catch (NumberFormatException e) {
            infoLabel.setText("Błąd");
        }
    }

    public void back() {
        viewManager.setInitScene();
    }

    public void clear() {
        numberTF.clear();
        seatTF.clear();
        rowTF.clear();
        infoLabel.setText("");
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadServer(ThreadServer threadServer) {
        this.threadServer = threadServer;
    }
}
