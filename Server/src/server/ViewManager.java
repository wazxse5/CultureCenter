package server;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.AddEmployeeController;
import server.controller.AddRoomController;
import server.controller.InitViewController;

import java.io.IOException;

public class ViewManager {

    private Stage primaryStage;
    private ThreadServer threadServer;

    private Scene initScene;
    private Scene addEmployeeScene;
    private Scene addRoomScene;
    private InitViewController initViewController;
    private AddEmployeeController addEmployeeController;
    private AddRoomController addRoomController;
    private String mainCssPath;

    public ViewManager(Stage primaryStage, ThreadServer threadServer) {
        this.primaryStage = primaryStage;
        this.threadServer = threadServer;
        mainCssPath = String.valueOf(getClass().getResource("/css/styles.css"));
    }

    public void setInitScene() {
        if (initScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/initView.fxml"));
                Parent parent = loader.load();
                initScene = new Scene(parent);
                initScene.getStylesheets().add(mainCssPath);

                initViewController = loader.getController();
                initViewController.setViewManager(this);
                initViewController.setThreadServer(threadServer);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku initView");
            }
        }
        primaryStage.setScene(initScene);

    }

    public void setAddEmployeeScene() {
        if (addEmployeeScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEmployeeView.fxml"));
                Parent parent = loader.load();
                addEmployeeScene = new Scene(parent);
                addEmployeeScene.getStylesheets().add(mainCssPath);

                addEmployeeController = loader.getController();
                addEmployeeController.setViewManager(this);
                addEmployeeController.setThreadServer(threadServer);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku addEmployeeView");
            }
        }
        primaryStage.setScene(addEmployeeScene);
    }
    public void setAddRoomScene() {
        if (addRoomScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addRoomView.fxml"));
                Parent parent = loader.load();
                addRoomScene = new Scene(parent);
                addRoomScene.getStylesheets().add(mainCssPath);

                addRoomController = loader.getController();
                addRoomController.setViewManager(this);
                addRoomController.setThreadServer(threadServer);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku addRoomView");
            }
        }
        primaryStage.setScene(addRoomScene);
    }



    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public String getTitle() {
        return primaryStage.getTitle();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
