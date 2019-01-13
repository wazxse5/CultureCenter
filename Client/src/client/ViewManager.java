package client;

import client.controller.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import message.ChangeUserDataAnswerMessage;
import java.time.LocalDate;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewManager {
    private ThreadClient threadClient;
    private StringProperty connectionState = new SimpleStringProperty();

    private Stage primaryStage;
    private BorderPane contentPane;

    private Scene initScene;
    private Region loginScene;
    private Region registerScene;
    private Region restorePasswordScene;
    private Region accountSettingsScene;
    private Region historyScene;
    private Region logsScene;
    private Region repertoireScene;
    private Region actualReservedScene;
    private Region recommendationScene;
    private Region eventsScene;
    private Region infosScene;
    private Region contactScene;
    private Region choosingSeatScene;
    private Region addEventsScene;
    private Region editEventsScene;
    private Region addRepertoireScene;
    private Region reviewScene;
    private Region editRepertoireScene;


    private InitViewController initViewController;
    private LoginViewController loginViewController;
    private RegisterViewController registerViewController;
    private RestorePasswordController restorePasswordViewController;
    private AccountSettingsViewController accountSettingsViewController;
    private HistoryViewController historyViewController;
    private LogsViewController logsViewController;
    private RepertuarViewController repertuarViewController;
    private ActualReservedViewController actualReservedViewController;
    private RecommendationViewController recommendationViewController;
    private EventsViewController eventsViewController;
    private InfosViewController infosViewController;
    private ContactViewController contactViewController;
    private ChoosingSeatViewController choosingSeatViewController;
    private AddEventsViewController addEventsViewController;
    private EditEventsViewController editEventsViewController;
    private AddRepertoireViewController addRepertoireViewController;
    private ReviewViewController reviewViewController;
    private EditRepertoireViewController editRepertoireViewController;
    private LocalDate dt;
    private final String mainCssPath = "/../commonSources/css/styles.css";


    public ViewManager(Stage primaryStage, ThreadClient threadClient) {
        this.primaryStage = primaryStage;
        this.threadClient = threadClient;

        primaryStage.setMinWidth(810);
        primaryStage.setMinHeight(500);

        connectionState.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("NONE")) setTitle("Client");
            if (newValue.equals("CONNECTING")) setTitle("Client - łączenie");
            if (newValue.equals("CONNECTED")) setTitle("Client - połączono");
            if (newValue.equals("NOT_CONNECTED")) setTitle("Client - brak połączenia");
        });
    }

    public void setContactScene() {
        if (contactScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/contactView.fxml"));
                contactScene = loader.load();
                contactScene.getStylesheets().add("/../commonSources/css/styles.css");

                contactViewController = loader.getController();
                contactViewController.setViewManager(this);
                contactViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku ContactsView");
            }
        }
        contentPane.setCenter(contactScene);
    }



    public void setReviewScene() {
        if (reviewScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reviewView.fxml"));
                reviewScene = loader.load();
                reviewScene.getStylesheets().add("/../commonSources/css/styles.css");

                reviewViewController = loader.getController();
                reviewViewController.setViewManager(this);
                reviewViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku ReviewView");
            }
        }
        contentPane.setCenter(reviewScene);
    }

    public void setEditEventsScene() {
        if (editEventsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editEventsView.fxml"));
                editEventsScene = loader.load();
                editEventsScene.getStylesheets().add("/../commonSources/css/styles.css");

                editEventsViewController = loader.getController();
                editEventsViewController.setViewManager(this);
                editEventsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku EditEventsView");
            }
        }
        contentPane.setCenter(editEventsScene);
        prepareFields();
    }
    public void setEditRepertoireScene() {
        if (editRepertoireScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editRepertoireView.fxml"));
                editRepertoireScene = loader.load();
                editRepertoireScene.getStylesheets().add("/../commonSources/css/styles.css");

                editRepertoireViewController = loader.getController();
                editRepertoireViewController.setViewManager(this);
                editRepertoireViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku EditRepertoireView");
            }
        }
        contentPane.setCenter(editRepertoireScene);
        prepareRepertoireFields();
    }


    public void setInfosScene() {
        if (infosScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/infosView.fxml"));
                infosScene = loader.load();
                infosScene.getStylesheets().add("/../commonSources/css/styles.css");

                infosViewController = loader.getController();
                infosViewController.setViewManager(this);
                infosViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku InfosView");
            }
        }
        threadClient.sendGetInfos();

        contentPane.setCenter(infosScene);

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

    public void setEventsScene() {
        if (eventsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eventsView.fxml"));
                eventsScene = loader.load();
                eventsScene.getStylesheets().add("/../commonSources/css/styles.css");

                eventsViewController = loader.getController();
                eventsViewController.setViewManager(this);
                eventsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku EventsView");
            }
        }
        contentPane.setCenter(eventsScene);
    }

    public void setRecommendationsScene() {
        if (recommendationScene == null) {
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
        Platform.runLater(() -> recommendationViewController.refresh());
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

    public void setRepertoireScene() {
        if (repertoireScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/repertoireView.fxml"));
                repertoireScene = loader.load();
                repertoireScene.getStylesheets().add("/../commonSources/css/styles.css");

                repertuarViewController = loader.getController();
                repertuarViewController.setViewManager(this);
                repertuarViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku repertuarView");
            }
        }
        contentPane.setCenter(repertoireScene);
    }
    public void setAddEventsScene() {
        if (addEventsScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/addEventsView.fxml"));
                addEventsScene = loader.load();
                addEventsScene.getStylesheets().add("/../commonSources/css/styles.css");

                addEventsViewController = loader.getController();
                addEventsViewController.setViewManager(this);
                addEventsViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku addEventsView");
            }
        }
        contentPane.setCenter(addEventsScene);
    }
    public void setAddRepertoireScene() {
        if (addRepertoireScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/addRepertoireView.fxml"));
                addRepertoireScene = loader.load();
                addRepertoireScene.getStylesheets().add("/../commonSources/css/styles.css");

                addRepertoireViewController = loader.getController();
                addRepertoireViewController.setViewManager(this);
                addRepertoireViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku addRepertoireView");
            }
        }
        contentPane.setCenter(addRepertoireScene);
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
        loginViewController.prepareFocus();
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
        registerViewController.prepareFocus();
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

    public void setChoosingSeatScene(List<List<Integer>> seats) {
        if (choosingSeatScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoosingSeatView.fxml"));
                choosingSeatScene = loader.load();
                choosingSeatScene.getStylesheets().add("/../commonSources/css/styles.css");

                choosingSeatViewController = loader.getController();
                choosingSeatViewController.setViewManager(this);
                choosingSeatViewController.setThreadClient(threadClient);
            } catch (IOException e) {
                setTitle("Nie można załadować widoku repertuarView");
            }
        }
        choosingSeatViewController.setLayout(seats);
        contentPane.setCenter(choosingSeatScene);
    }


    public void clearContentPane() {
        contentPane.setCenter(null);
    }

    public void handleChangeUserDateAnswet(ChangeUserDataAnswerMessage answerMessage) {
        StringBuilder builder = new StringBuilder();
        if (answerMessage.isAnythingChanged()) {
            builder.append("Zmieniono ");
            if (answerMessage.isNameChanged()) builder.append("imię ");
            if (answerMessage.isSurnameChanged()) builder.append("nazwisko ");
            if (answerMessage.isMailChanged()) builder.append("mail ");
            if (answerMessage.isPasswordChanged()) builder.append("hasło ");
        } else builder.append("Nic nie zmieniono");
        accountSettingsViewController.setAnswerLabelText(builder.toString());
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

    public EventsViewController getEventsViewController() {
        return eventsViewController;
    }
    public EditEventsViewController getEditEventsViewController(){return editEventsViewController;}

    public RestorePasswordController getRestorePasswordViewController(){
        return restorePasswordViewController;
    }

    public AccountSettingsViewController getAccountSettingsViewController(){
        return accountSettingsViewController;
    }
    public InfosViewController getInfosViewController(){
        return infosViewController;
    }


    public RegisterViewController getRegisterViewController() {
        return registerViewController;
    }
    public AddEventsViewController getAddEventsViewController() {
        return addEventsViewController;
    }

    public RepertuarViewController getRepertuarViewController() {
        return repertuarViewController;
    }

    public RecommendationViewController getRecommendationViewController() {
        return recommendationViewController;
    }

    public ReviewViewController getReviewViewController() {
        return reviewViewController;
    }
    public EditRepertoireViewController getEditRepertoireViewController() {
        return editRepertoireViewController;
    }

    public void setContentPane(BorderPane contentPane) {
        this.contentPane = contentPane;
    }
    public void prepareFields(){
        editEventsViewController.getImagePathTF().setText(eventsViewController.getRowData().getImagePath());
        editEventsViewController.getTitleTF().setText(eventsViewController.getRowData().getTitle());
        editEventsViewController.getTypeTF().setText(eventsViewController.getRowData().getType());
        editEventsViewController.getReleaseDateTF().setValue(LocalDate.parse(eventsViewController.getRowData().getReleaseDate()));
        editEventsViewController.getLanguageTF().setText(eventsViewController.getRowData().getLanguage());
        editEventsViewController.getAgeRestrictionTF().setText(eventsViewController.getRowData().getAgeRestriction());
        editEventsViewController.getDurationTF().setText(eventsViewController.getRowData().getDuration());
        editEventsViewController.getIdEventTF().setText(eventsViewController.getRowData().getIdEventType());

    }
    public void prepareRepertoireFields(){
        editRepertoireViewController.getTitleTF().setText(repertuarViewController.getRowData().getName());
        editRepertoireViewController.getDateTF().setValue(LocalDate.parse((repertuarViewController.getRowData().getDate())));
        editRepertoireViewController.getTimeTF().setText(repertuarViewController.getRowData().getStartTime());
        editRepertoireViewController.getFilmID().setItems(FXCollections.observableArrayList(new ShortEvent(repertuarViewController.getRowData().getIdEventType(),repertuarViewController.getRowData().getName())));
        editRepertoireViewController.getFilmID().setValue(new ShortEvent(repertuarViewController.getRowData().getIdEventType(),threadClient.getNameOfEvent(Integer.valueOf(repertuarViewController.getRowData().getIdEventType()))));
        editRepertoireViewController.getRoomCB().setItems(editRepertoireViewController.getRoomValues());
        editRepertoireViewController.getRoomCB().setValue( new Room(repertuarViewController.getRowData().getIdRoom(),"1", "0","0","1"));
        editRepertoireViewController.getIdEventTF().setText(repertuarViewController.getRowData().getIdEvent());
    }
    public void prepareInfos(){
        if(!threadClient.getInfos().isEmpty()){
            getInfosViewController().getInfoLabel1().setText(threadClient.getInfos().get(0).get(1)+" - " + threadClient.getInfos().get(0).get(2));
            getInfosViewController().getInfoLabel2().setText(threadClient.getInfos().get(1).get(1)+" - " + threadClient.getInfos().get(1).get(2));
            getInfosViewController().getInfoLabel3().setText(threadClient.getInfos().get(2).get(1)+" - " + threadClient.getInfos().get(2).get(2));
            getInfosViewController().getInfoLabel4().setText(threadClient.getInfos().get(3).get(1)+" - " + threadClient.getInfos().get(3).get(2));
        }

    }
}
