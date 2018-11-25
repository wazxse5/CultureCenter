package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoggedViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private Label userNameLabel;


    public void initialize() {

    }

    public void CheckHistory(){
        viewManager.setHistoryScene();
    }

    public void ActualReserved(){
        viewManager.setActualReservedScene();
    }
    public void AccountSettings(){

        viewManager.setAccountSettingsScene();
    }
    public void LogOut(){
        // LOG OUT
        viewManager.setInitScene();
    }
    public void Repertuar()    {
        viewManager.setRepertuarScene();
    }
    public void CheckLogs(){
        viewManager.setLogsScene();
    }

    public void SetSchedule(){
        //viewManager.setSetScheduleScene();
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        userNameLabel.textProperty().bind(threadClient.userNameProperty());
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
