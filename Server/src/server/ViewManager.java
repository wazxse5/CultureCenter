package server;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.InitViewController;

import java.io.IOException;

public class ViewManager {
    private Stage primaryStage;
    private ThreadServer threadServer;

    private Scene initScene;

    public ViewManager(Stage primaryStage, ThreadServer threadServer) {
        this.primaryStage = primaryStage;
        this.threadServer = threadServer;
    }

    public void setInitScene() {
        if (initScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/initView.fxml"));
                Parent parent = loader.load();
                initScene = new Scene(parent);
                initScene.getStylesheets().add("/../commonSources/css/styles.css");


                InitViewController initViewController = loader.getController();
                initViewController.setViewManager(this);
                initViewController.setThreadServer(threadServer);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku initView");
            }
        }
        primaryStage.setScene(initScene);
    }


    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public String getTitle() {
        return primaryStage.getTitle();
    }


}
