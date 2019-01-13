package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InfosViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private Label infoLabel1;
    @FXML private Label infoLabel2;
    @FXML private Label infoLabel3;
    @FXML private Label infoLabel4;
    @FXML private Button addBT;
    @FXML private TextField infoTF;


    public void initialize() {

    }

    public void add(){
     String text = infoTF.getText();
     threadClient.addInfo(text);
     infoTF.clear();

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


    public Label getInfoLabel1() {
        return infoLabel1;
    }

    public Label getInfoLabel2() {
        return infoLabel2;
    }

    public Label getInfoLabel3() {
        return infoLabel3;
    }

    public Label getInfoLabel4() {
        return infoLabel4;
    }

}
