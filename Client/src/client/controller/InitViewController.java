package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class InitViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private BorderPane contentPane;
    @FXML private ImageView imageView;

    @FXML private HBox hboxLoggedNO;
    @FXML private HBox hboxLoggedYES;
    @FXML private HBox hboxLoggedAs;
    @FXML private Label loggedUserName;
    @FXML private Label notLoggedLabel;

    @FXML private Button connectButton;
    @FXML private Button recommendedButton;
    @FXML private Button repertoireButton;
    @FXML private Button eventsButton;
    @FXML private Button InfoButton;
    @FXML private Button ContactButton;
    @FXML private Button LogsButton;
    @FXML private Button historyButton;

    public void initialize() {
        Image logo = new Image(String.valueOf(getClass().getResource("/../commonSources/images/logo1.png")));
        imageView.setImage(logo);
        imageView.setOnMouseClicked((MouseEvent event) -> viewManager.setRecommendationsScene());
    }

    public void connect() {
        threadClient.connect("localhost", 8989);
    }


    public void login() {
        viewManager.setLoginScene();
    }

    public  void logout() {
        threadClient.sendLogoutRequest();
    }

    public void register() {
        viewManager.setRegisterScene();
    }

    public void accountSettings() {
        viewManager.setAccountSettingsScene();
    }

    public void repertoire() {
        viewManager.setRepertoireScene();
        threadClient.sendRepertoireCheckRequest();
    }
    public void history(){
        viewManager.setHistoryScene();
    }

    public void recommendation() {
        viewManager.setRecommendationsScene();
    }

    public void events() {
        viewManager.setEventsScene();
        threadClient.sendEventsCheckRequest();
    }

    public void infos() {
        viewManager.prepareInfos();
        viewManager.setInfosScene();
    }

    public void contact(){
        viewManager.setContactScene();
    }

    public void logs() {
        viewManager.setLogsScene();
        if(threadClient.isConnected()) threadClient.sendLogsCheckRequest((threadClient.getUserName()));
        threadClient.sendLogsCheckRequest("");
    }


    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        hboxLoggedNO.visibleProperty().bind(threadClient.loggedProperty().not());
        hboxLoggedYES.visibleProperty().bind(threadClient.loggedProperty());
        hboxLoggedAs.visibleProperty().bind(threadClient.loggedProperty());
        loggedUserName.textProperty().bind(threadClient.userNameProperty());
        notLoggedLabel.visibleProperty().bind(threadClient.loggedProperty().not());
        connectButton.visibleProperty().bind(threadClient.connectedProperty().not());
        LogsButton.visibleProperty().bind(threadClient.loggedAsEmployeeProperty());
        historyButton.visibleProperty().bind(threadClient.loggedProperty());

        recommendedButton.disableProperty().bind(threadClient.connectedProperty().not());
        repertoireButton.disableProperty().bind(threadClient.connectedProperty().not());
        eventsButton.disableProperty().bind(threadClient.connectedProperty().not());
        InfoButton.disableProperty().bind(threadClient.connectedProperty().not());
        ContactButton.disableProperty().bind(threadClient.connectedProperty().not());
        LogsButton.disableProperty().bind(threadClient.connectedProperty().not());
        historyButton.disableProperty().bind(threadClient.connectedProperty().not());
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        viewManager.setContentPane(contentPane);
    }

}
