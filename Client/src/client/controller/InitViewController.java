package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


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

    public void initialize() {
        Image logo = new Image(String.valueOf(getClass().getResource("/../commonSources/images/polynesian.jpg")));
        imageView.setImage(logo);
        imageView.setOnMouseClicked((MouseEvent event) -> {
            viewManager.setRecommendationsScene();
//            contentPane.setCenter(null);

        });

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

    }

    public void repertuar(){
        viewManager.setRepertuarScene();
    }

    public void recommendation() {
        viewManager.setRecommendationsScene();
    }

    public void events(){
        viewManager.setEventsScene();
    }

    public void infos(){
        viewManager.setInfosScene();

    }

    public void contact(){
        viewManager.setContactScene();
    }
    public void logs(){
        viewManager.setLogsScene();
        if((boolean)threadClient.getConnected().get()) threadClient.sendLogsCheckRequest((String)(threadClient.getUserName().get()));
        threadClient.sendLogsCheckRequest("");


    }


    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        hboxLoggedNO.visibleProperty().bind(threadClient.loggedProperty().not());
        hboxLoggedYES.visibleProperty().bind(threadClient.loggedProperty());
        hboxLoggedAs.visibleProperty().bind(threadClient.loggedProperty());
        loggedUserName.textProperty().bind(threadClient.userNameProperty());
        notLoggedLabel.visibleProperty().bind(threadClient.loggedProperty().not());
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        viewManager.setContentPane(contentPane);
    }
}
