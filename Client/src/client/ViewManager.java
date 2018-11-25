package client;

import client.controller.*;
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
    private Scene restorePasswordScene;
    private Scene accountSettingsScene;
    private Scene historyScene;
    private Scene logsScene;
    private Scene repertuarScene;
    private Scene actualReservedScene;

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


    public ViewManager(Stage primaryStage, ThreadClient threadClient) {
        this.primaryStage = primaryStage;
        this.threadClient = threadClient;
    }


    public void setHistoryScene() {
        if (historyScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/historyView.fxml"));
                Parent parent = loader.load();
                historyScene = new Scene(parent);

                historyViewController = loader.getController();
                historyViewController.setViewManager(this);
                historyViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku HistoryView");
            }
        }
        primaryStage.setScene(historyScene);
    }

    public void setActualReservedScene() {
        if (actualReservedScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/actualReservedView.fxml"));
                Parent parent = loader.load();
                actualReservedScene = new Scene(parent);

                actualReservedViewController = loader.getController();
                actualReservedViewController.setViewManager(this);
                actualReservedViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku actualReservedView");
            }
        }
        primaryStage.setScene(actualReservedScene);
    }

    public void setLogsScene() {
        if (logsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/logsView.fxml"));
                Parent parent = loader.load();
                logsScene = new Scene(parent);

                logsViewController = loader.getController();
                logsViewController.setViewManager(this);
                logsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku logsView");
            }
        }
        primaryStage.setScene(logsScene);
    }

    public void setRepertuarScene() {
        if (repertuarScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/repertuarView.fxml"));
                Parent parent = loader.load();
                repertuarScene = new Scene(parent);

                repertuarViewController = loader.getController();
                repertuarViewController.setViewManager(this);
                repertuarViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku repertuarView");
            }
        }
        primaryStage.setScene(repertuarScene);
    }



    public void setAccountSettingsScene() {
        if (accountSettingsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/accountSettingsView.fxml"));
                Parent parent = loader.load();
                accountSettingsScene = new Scene(parent);

                accountSettingsViewController = loader.getController();
                accountSettingsViewController.setViewManager(this);
                accountSettingsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku accountSettingsView");
            }
        }
        primaryStage.setScene(accountSettingsScene);
    }



    public void setInitScene() {
        if (initScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/initView.fxml"));
                Parent parent = loader.load();
                initScene = new Scene(parent);

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
                Parent parent = loader.load();
                loginScene = new Scene(parent);

                loginViewController = loader.getController();
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

                registerViewController = loader.getController();
                registerViewController.setViewManager(this);
                registerViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku registerView");
            }
        }
        primaryStage.setScene(registerScene);
    }

    public void setRestorePasswordScene() {
        if (restorePasswordScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/restorePasswordView.fxml"));
                Parent parent = loader.load();
                restorePasswordScene = new Scene(parent);

                restorePasswordViewController = loader.getController();
                restorePasswordViewController.setViewManager(this);
                restorePasswordViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku restorePasswordView");
            }
        }
        primaryStage.setScene(restorePasswordScene);
    }


    public void setLoggedScene() {
        if (loggedScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/loggedView.fxml"));
                Parent parent = loader.load();
                loggedScene = new Scene(parent);

                loggedViewController = loader.getController();
                loggedViewController.setViewManager(this);
                loggedViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku loggedView");
            }
        }
        primaryStage.setScene(loggedScene);
    }



    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    public String getTitle() {
        return primaryStage.getTitle();
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

}
