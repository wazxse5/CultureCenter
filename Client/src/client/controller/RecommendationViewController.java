package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecommendationViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private ImageView imageview1;
    @FXML private ImageView imageview2;
    @FXML private ImageView imageview3;
    @FXML private ImageView imageview4;
    @FXML private ImageView imageview5;
    @FXML private ImageView imageview6;
    @FXML private ImageView imageview7;
    @FXML private ImageView imageview8;

    public void initialize() {
        Image image = new Image(String.valueOf(getClass().getResource("/../commonSources/images/no-image.jpg")));
        imageview1.setImage(image);
        imageview2.setImage(image);
        imageview3.setImage(image);
        imageview4.setImage(image);
        imageview5.setImage(image);
        imageview6.setImage(image);
        imageview7.setImage(image);
        imageview8.setImage(image);

    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
