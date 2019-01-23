package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EventTypeViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private Label titleLabel;
    @FXML private Label typeLabel;
    @FXML private Label ageLabel;
    @FXML private Label durationLabel;
    @FXML private Label languageLabel;
    @FXML private Label releaseLabel;
    @FXML private Label descriptionLabel;
    @FXML private ImageView imageView;

    public void setLayout(Event event) {
        titleLabel.setText(event.getTitle());
        typeLabel.setText(event.getType());
        ageLabel.setText("Od lat: " + event.getAgeRestriction());
        durationLabel.setText("Czas: " + event.getDuration());
        languageLabel.setText("Język: " + event.getLanguage());
        releaseLabel.setText("Premiera: " + event.getReleaseDate());
        descriptionLabel.setText("Opis wydarzenia");
        int idEventType = Integer.parseInt(event.getIdEventType());
        threadClient.sendImageEventTypeRequest(idEventType);
        imageView.imageProperty().bind(threadClient.getDataLoader().getImageProperty(idEventType));
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

}
