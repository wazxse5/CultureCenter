package client;

import client.controller.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    private ThreadClient threadClient;
    private StringProperty connectionState = new SimpleStringProperty();

    private Stage primaryStage;
    private BorderPane contentPane;

    private Scene initScene;
    private Region loginScene;
    private Region loggedScene;
    private Region registerScene;
    private Region restorePasswordScene;
    private Region accountSettingsScene;
    private Region historyScene;
    private Region logsScene;
    private Region repertuarScene;
    private Region actualReservedScene;
    private Region recommendationScene;

    private InitViewController initViewController;
    private LoginViewController loginViewController;
    private LoggedViewController loggedViewController;
    private RegisterViewController registerViewController;
    private RestorePasswordController restorePasswordViewController;
    private AccountSettingsViewController accountSettingsViewController;
    private HistoryViewController historyViewController;
    private LogsViewController logsViewController;
    private RepertuarViewController repertuarViewController;
    private ActualReservedViewController actualReservedViewController;
    private RecommendationViewController recommendationViewController;

    private final String mainCssPath = "/../commonSources/css/styles.css";


    public ViewManager(Stage primaryStage, ThreadClient threadClient) {
        this.primaryStage = primaryStage;
        this.threadClient = threadClient;

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);

        connectionState.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("NONE")) setTitle("Client");
            if (newValue.equals("CONNECTING")) setTitle("Client - łączenie");
            if (newValue.equals("CONNECTED")) setTitle("Client - połączono");
            if (newValue.equals("NOT_CONNECTED")) setTitle("Client - brak połączenia");
        });
    }


    public void setHistoryScene() {
        if (historyScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/historyView.fxml"));
                historyScene = loader.load();
                historyScene.getStylesheets().add("/../commonSources/css/styles.css");

                historyViewController = loader.getController();
                historyViewController.setViewManager(this);
                historyViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku HistoryView");
            }
        }
        contentPane.setCenter(historyScene);
    }

    public void setRecommendationsScene() {
        if (historyScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/recommendationView.fxml"));
                recommendationScene = loader.load();
                recommendationScene.getStylesheets().add("/../commonSources/css/styles.css");

                recommendationViewController = loader.getController();
                recommendationViewController.setViewManager(this);
                recommendationViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku RecommendationView");
            }
        }
        contentPane.setCenter(recommendationScene);
    }

    public void setActualReservedScene() {
        if (actualReservedScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/actualReservedView.fxml"));
                actualReservedScene = loader.load();
                actualReservedScene.getStylesheets().add("/../commonSources/css/styles.css");

                actualReservedViewController = loader.getController();
                actualReservedViewController.setViewManager(this);
                actualReservedViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku actualReservedView");
            }
        }
        contentPane.setCenter(actualReservedScene);
    }

    public void setLogsScene() {
        if (logsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/logsView.fxml"));
                logsScene = loader.load();
                logsScene.getStylesheets().add("/../commonSources/css/styles.css");

                logsViewController = loader.getController();
                logsViewController.setViewManager(this);
                logsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku logsView");
            }
        }
        contentPane.setCenter(logsScene);
    }

    public void setRepertuarScene() {
        if (repertuarScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/repertuarView.fxml"));
                repertuarScene = loader.load();
                repertuarScene.getStylesheets().add("/../commonSources/css/styles.css");

                repertuarViewController = loader.getController();
                repertuarViewController.setViewManager(this);
                repertuarViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku repertuarView");
            }
        }
        contentPane.setCenter(repertuarScene);
    }

    public void setAccountSettingsScene() {
        if (accountSettingsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/accountSettingsView.fxml"));
                accountSettingsScene = loader.load();
                accountSettingsScene.getStylesheets().add("/../commonSources/css/styles.css");

                accountSettingsViewController = loader.getController();
                accountSettingsViewController.setViewManager(this);
                accountSettingsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku accountSettingsView");
            }
        }
        contentPane.setCenter(accountSettingsScene);
    }

    public void setInitScene() {
        if (initScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/initView.fxml"));
                Parent parent = loader.load();
                initScene = new Scene(parent);
                initScene.getStylesheets().add(mainCssPath);

                initViewController = loader.getController();
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
                loginScene = loader.load();
                loginScene.getStylesheets().add("/../commonSources/css/styles.css");

                loginViewController = loader.getController();
                loginViewController.setViewManager(this);
                loginViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku loginView");
            }
        }
        contentPane.setCenter(loginScene);
    }

    public void setRegisterScene() {
        if (registerScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/registerView.fxml"));
                registerScene = loader.load();
                registerScene.getStylesheets().add("/../commonSources/css/styles.css");

                registerViewController = loader.getController();
                registerViewController.setViewManager(this);
                registerViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku registerView");
            }
        }
        contentPane.setCenter(registerScene);
    }

    public void setRestorePasswordScene() {
        if (restorePasswordScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/restorePasswordView.fxml"));
                restorePasswordScene = loader.load();
                restorePasswordScene.getStylesheets().add("/../commonSources/css/styles.css");

                restorePasswordViewController = loader.getController();
                restorePasswordViewController.setViewManager(this);
                restorePasswordViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku restorePasswordView");
            }
        }
        contentPane.setCenter(restorePasswordScene);
    }

    public void setLoggedScene() {
        if (loggedScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/loggedView.fxml"));
                loggedScene = loader.load();
                loggedScene.getStylesheets().add("/../commonSources/css/styles.css");

                loggedViewController = loader.getController();
                loggedViewController.setViewManager(this);
                loggedViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku loggedView");
            }
        }
        contentPane.setCenter(loggedScene);
    }

    public void clearContentPane() {
        contentPane.setCenter(null);
    }



    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public String getTitle() {
        return primaryStage.getTitle();
    }

    public StringProperty connectionStateProperty() {
        return connectionState;
    }

    public InitViewController getInitViewController() {

        return initViewController;
    }

    public LoginViewController getLoginViewController() {
        return loginViewController;
    }

    public LoggedViewController getLoggedViewController() {
        return loggedViewController;
    }

    public RestorePasswordController getRestorePasswordViewController(){
        return restorePasswordViewController;
    }

    public AccountSettingsViewController getAccountSettingsViewController(){
        return accountSettingsViewController;
    }

    public RegisterViewController getRegisterViewController() {
        return registerViewController;
    }

    public RepertuarViewController getRepertuarViewController() {
        return repertuarViewController;
    }

    public void setContentPane(BorderPane contentPane) {
        this.contentPane = contentPane;
    }
}
