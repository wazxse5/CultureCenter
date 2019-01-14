package client.controller;

import client.DataLoader;
import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;


import java.util.List;

public class RecommendationViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private DataLoader dataLoader;

    @FXML private ImageView imageview1;
    @FXML private ImageView imageview2;
    @FXML private ImageView imageview3;
    @FXML private ImageView imageview4;
    @FXML private ImageView imageview5;
    @FXML private ImageView imageview6;
    @FXML private ImageView imageview7;
    @FXML private ImageView imageview8;

    @FXML private ScrollPane scrollPane;
    @FXML private AnchorPane anchorPane;
    @FXML private FlowPane flowPane;

    public void initialize() {
        flowPane.prefWrapLengthProperty().bind(scrollPane.widthProperty().subtract(40));
    }

    public void refresh() {
        List<Integer> recommended = dataLoader.getRecommendation();
        for (int i = 0; i < recommended.size(); i++) {
            int eventTypeId = recommended.get(i);
            if (eventTypeId != -1) {
                threadClient.sendImageEventTypeRequest(eventTypeId);
            }
        }
    }

    public void rebindImageViews() {
        List<Integer> recommended = dataLoader.getRecommendation();
        imageview1.imageProperty().bind(dataLoader.getImageProperty(recommended.get(0)));
        imageview2.imageProperty().bind(dataLoader.getImageProperty(recommended.get(1)));
        imageview3.imageProperty().bind(dataLoader.getImageProperty(recommended.get(2)));
        imageview4.imageProperty().bind(dataLoader.getImageProperty(recommended.get(3)));
        imageview5.imageProperty().bind(dataLoader.getImageProperty(recommended.get(4)));
        imageview6.imageProperty().bind(dataLoader.getImageProperty(recommended.get(5)));
        imageview7.imageProperty().bind(dataLoader.getImageProperty(recommended.get(6)));
        imageview8.imageProperty().bind(dataLoader.getImageProperty(recommended.get(7)));
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        this.dataLoader = threadClient.getDataLoader();
        rebindImageViews();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
