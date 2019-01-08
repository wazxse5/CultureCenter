package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddRepertoireViewController {

    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private TextField nameTF;
    @FXML private TextField dateTF;
    @FXML private TextField startTimeTF;
    @FXML private TextField titleTF;
    @FXML private TextField durationTF;
    @FXML private TextField ageRestrictionTF;
    @FXML private TextField languageTF;
    @FXML private TextField releaseDateTF;
    @FXML private TextField typeTF;


    @FXML private Label infoLabel;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Button clearButton;


    public void initialize() {

    }

    public void confirm() {
        String name = nameTF.getText();
        String  date = dateTF.getText();
        String startTime = startTimeTF.getText();
        String  title = titleTF.getText();
        String  duration = durationTF.getText();
        String  ageRestriction = ageRestrictionTF.getText();
        String  language = languageTF.getText();
        String  releaseDate = releaseDateTF.getText();
        String  type = typeTF.getText();
    }

    public void back() {
        viewManager.setRepertoireScene();
    }

    public void clear() {
        nameTF.clear();
        dateTF.clear();
        startTimeTF.clear();
        titleTF.clear();
        durationTF.clear();
        ageRestrictionTF.clear();
        languageTF.clear();
        releaseDateTF.clear();
        typeTF.clear();
        infoLabel.setText("");
    }



    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
}
