package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML private ChoiceBox choiceBox;

    public void refreshWithNewValues(Event event) {
        this.event = event;
        // TODO: dopisać ustawianie nowych wartości zgodnych z aktualnym eventem
    }

    public void initialize(){
        choiceBox.setItems(FXCollections.observableArrayList("Opinia","Skarga"));
        choiceBox.setValue("Opinia");
    }
    public void check(){
        if(choiceBox.getValue().toString()=="Opinia")if(!gradeSlider.isDisabled())gradeSlider.setDisable(false);
        else gradeSlider.setDisable(true);

    }
    public void confirm() {
        int grade = (int) gradeSlider.getValue();
        String opinion = opinionTA.getText();
        threadClient.sendReview(new ReviewMessage(Integer.valueOf(viewManager.getHistoryViewController().getRowData().getIdEvent()), Integer.valueOf(viewManager.getHistoryViewController().getRowData().getIdClient()), grade, opinion,choiceBox.getValue().toString()));
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
