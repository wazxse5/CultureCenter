package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ContactViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private AnchorPane anchorPaneMap;
    @FXML private ImageView imageViewMap;


    public void initialize() {
        Image image = new Image(String.valueOf(getClass().getResource("/../commonSources/images/map.jpg")));
        imageViewMap.setImage(image);

        imageViewMap.fitWidthProperty().bind(anchorPaneMap.widthProperty());
        imageViewMap.fitHeightProperty().bind(anchorPaneMap.heightProperty());

    }




    public void back() {
        viewManager.setInitScene();
    }


    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
    public void connect() {
        threadClient.connect("localhost", 8989);
    }

}
