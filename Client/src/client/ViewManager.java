package client;

import client.controller.InitViewController;
import client.controller.LoggedViewController;
import client.controller.LoginViewController;
import client.controller.RegisterViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    private Stage primaryStage;
    private ThreadClient threadClient;

    private Scene initScene;
    private Scene loginScene;
    private Scene loggedScene;
    private Scene registerScene;

    public ViewManager(Stage primaryStage, ThreadClient threadClient) {
        this.primaryStage = primaryStage;
        this.threadClient = threadClient;
    }

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public String getTitle() {
        return primaryStage.getTitle();
    }

    public void setInitScene() {
        if (initScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/initView.fxml"));
                Parent parent = loader.load();
                initScene = new Scene(parent);

                InitViewController initViewController = loader.getController();
                initViewController.setViewManager(this);
                initViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku initView");
            }
        }
        primaryStage.setScene(initScene);
    }

    public void setLoginScene() {
        if (loginScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
                Parent parent = loader.load();
                loginScene = new Scene(parent);

                LoginViewController loginViewController = loader.getController();
                loginViewController.setViewManager(this);
                loginViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku loginView");
            }
        }
        primaryStage.setScene(loginScene);
    }

    public void setRegisterScene() {
        if (registerScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/registerView.fxml"));
                Parent parent = loader.load();
                registerScene = new Scene(parent);

                RegisterViewController registerViewController = loader.getController();
                registerViewController.setViewManager(this);
                registerViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku registerView");
            }
        }
        primaryStage.setScene(registerScene);
    }

    public void setLoggedView() {
        if (loggedScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/loggedView.fxml"));
                Parent parent = loader.load();
                loggedScene = new Scene(parent);

                LoggedViewController loggedViewController = loader.getController();
                loggedViewController.setViewManager(this);
                loggedViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku loggedView");
            }
        }
        primaryStage.setScene(loggedScene);
    }
}
