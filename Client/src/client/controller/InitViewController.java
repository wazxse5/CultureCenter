package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InitViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private BorderPane contentPane;
    @FXML private ImageView imageView;

    public void initialize() {
        Image logo = new Image(String.valueOf(getClass().getResource("/../commonSources/images/polynesian.jpg")));
        imageView.setImage(logo);
    }

    public void login() {
        viewManager.setLoginScene();
    }

    public void register() {
        viewManager.setRegisterScene();
    }

    public void repertuar(){
        viewManager.setRepertuarScene();
    }

    public void recommendation() {
        viewManager.setRecommendationsScene();
    }


    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        viewManager.setContentPane(contentPane);
    }
}
