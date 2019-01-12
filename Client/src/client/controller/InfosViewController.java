package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfosViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private Label infoLabel1;
    @FXML private Label infoLabel2;
    @FXML private Label infoLabel3;
    @FXML private Label infoLabel4;



    public void initialize() {

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
