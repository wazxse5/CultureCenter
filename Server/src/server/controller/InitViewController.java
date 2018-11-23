package server.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import server.ThreadServer;
import server.ViewManager;

public class InitViewController {
    private ViewManager viewManager;
    private ThreadServer threadServer;

    @FXML private Button startButton;

    public void start() {
        startButton.setDisable(true);
        threadServer.start(8989);
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadServer(ThreadServer threadServer) {
        this.threadServer = threadServer;
    }
}
