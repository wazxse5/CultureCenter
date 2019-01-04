package server.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import server.Connection;
import server.ThreadServer;
import server.ViewManager;

public class InitViewController {
    private ViewManager viewManager;
    private ThreadServer threadServer;

    @FXML private Button startButton;
    @FXML private Button addEmployeeButton;
    @FXML private ListView<Connection> connectedConnectionsLV;

    public void initialize() {
        connectedConnectionsLV.setCellFactory(param -> new ListCell<Connection>() {
            @Override
            protected void updateItem(Connection p, boolean empty){
                super.updateItem(p, empty);
                if(empty || p == null) setText("");
                else setText(p.getDescription());
                connectedConnectionsLV.refresh();
            }
        });
    }

    public void start() {
        startButton.setDisable(true);
        addEmployeeButton.setDisable(false);
        threadServer.start(8989);
    }

    public void addEmployee() {
        viewManager.setAddEmployeeScene();
    }

    public void close() {
        threadServer.close();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadServer(ThreadServer threadServer) {
        this.threadServer = threadServer;
        connectedConnectionsLV.itemsProperty().bind(threadServer.connectedConnectionsProperty());
    }
}
