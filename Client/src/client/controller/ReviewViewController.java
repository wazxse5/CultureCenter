package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import message.ReviewMessage;

public class ReviewViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private Event event;

    @FXML private Label eventName;
    @FXML private Label eventDateTime;
    @FXML private Slider gradeSlider;
    @FXML private TextArea opinionTA;
    @FXML private Button confirmButton;
    @FXML private Label infoLabel;

    public void refreshWithNewValues(Event event) {
        this.event = event;
        // TODO: dopisać ustawianie nowych wartości zgodnych z aktualnym eventem
    }

    public void confirm() {
        int grade = (int) gradeSlider.getValue();
        String opinion = opinionTA.getText();
//        threadClient.sendReview(new ReviewMessage(event.getId(), threadClient.getUserID(), grade, opinion));
        infoLabel.setText("Opinia została wysłana");
//        confirmButton.setDisable(true);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
