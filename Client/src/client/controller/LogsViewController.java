package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class LogsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    @FXML private TableView tableView;
    public void initialize() {

    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void refresh(){
       tableView = threadClient.getLogsCheckData();
        //System.out.println(threadClient.getLogsCheckData());
    }





    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
