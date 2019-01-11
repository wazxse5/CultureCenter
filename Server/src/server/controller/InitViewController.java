package server.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import server.Connection;
import server.ThreadServer;
import server.ViewManager;

import java.io.File;

public class InitViewController {
    private ViewManager viewManager;
    private ThreadServer threadServer;

    private DirectoryChooser directoryChooser;
    private File defaultImagesDir;
    private File selectedImagesDir;

    @FXML private Button startButton;
    @FXML private Button addEmployeeButton;
    @FXML private ListView<Connection> connectedConnectionsLV;
    @FXML private Label imagesDirPathLabel;

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

        directoryChooser = new DirectoryChooser();
        defaultImagesDir = new File(System.getProperty("user.dir") + "\\data");
        directoryChooser.setInitialDirectory(defaultImagesDir);
        selectedImagesDir = defaultImagesDir;
        imagesDirPathLabel.setText(selectedImagesDir.getAbsolutePath());
    }

    public void start() {
        startButton.setDisable(true);
        addEmployeeButton.setDisable(false);
        threadServer.getDataLoader().setEventTypeImagesDir(selectedImagesDir);
        threadServer.start(8989);
    }

    public void addEmployee() {
        viewManager.setAddEmployeeScene();
    }

    public void chooseDirectory() {
        File file = directoryChooser.showDialog(viewManager.getPrimaryStage());
        if (file != null) {
            selectedImagesDir = file;
            directoryChooser.setInitialDirectory(file);
        }
        else {
            selectedImagesDir = defaultImagesDir;
            directoryChooser.setInitialDirectory(defaultImagesDir);
        }
        imagesDirPathLabel.setText(selectedImagesDir.getAbsolutePath());
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
