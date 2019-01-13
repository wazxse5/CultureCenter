package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EditEventsViewController {




    private ViewManager viewManager;
    private ThreadClient threadClient;
    private List <String> lstFile;
    private EventsViewController eventsViewController;


    @FXML private TextField imagePathTF;
    @FXML private TextField titleTF;
    @FXML private TextField durationTF;
    @FXML private TextField ageRestrictionTF;
    @FXML private TextField languageTF;
    @FXML private DatePicker releaseDateTF;
    @FXML private TextField typeTF;



    @FXML private Label infoLabel;
    @FXML private TextField idEventTF;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Button clearButton;
    private final String pattern = "yyyy-MM-dd";

    public void initialize() {
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
        lstFile.add("*.png");
        lstFile.add("*.jpeg");

    }
    public void numOnly(){
        ageRestrictionTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                ageRestrictionTF.setText(oldValue);
            }
        });

    }


    public void RestrictionAge(){
        durationTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9:]*")){
                durationTF.setText(oldValue);
            }
        });



    }
    public void RestrictionText(){
        titleTF.textProperty().addListener((observable, oldValue, newValue) -> {
         if(!newValue.matches(threadClient.getRegex())){
             titleTF.setText(oldValue);
         }
        });
        languageTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                languageTF.setText(oldValue);
            }
        });
        typeTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches(threadClient.getRegex())){
                typeTF.setText(oldValue);
            }
        });

    }
    public void confirm() {

        String idEvent = idEventTF.getText();
        String imagePath=imagePathTF.getText();
        String  title = titleTF.getText();
        String  duration = durationTF.getText();
        String  ageRestriction = ageRestrictionTF.getText();
        String  language = languageTF.getText();
        String  releaseDate = releaseDateTF.getValue().toString();
        String  type = typeTF.getText();

        if(!imagePath.equals("")&&!title.equals("")&&!duration.equals("")&&!ageRestriction.equals("")&&!language.equals("")&&!releaseDate.equals("")&&!type.equals("")) {
            threadClient.sendEditEventsRequest(idEvent, title, duration, ageRestriction, language, releaseDate, type, imagePath);
           // infoLabel.setText("Zmieniono dane");
        } else infoLabel.setText("Proszę wypełnić wszystkie pola");
    }

    public void back() {

        threadClient.sendEventsCheckRequest();
        viewManager.setEventsScene();
    }


    public void clear() {
        titleTF.clear();
        durationTF.clear();
        ageRestrictionTF.clear();
        languageTF.clear();
        typeTF.clear();
        imagePathTF.clear();
        infoLabel.setText("");
    }

    public void singleFileChooser(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Obrazy",lstFile));
        File f = fc.showOpenDialog(null);

        if(f!=null){
            imagePathTF.setText(f.getAbsolutePath());
        }

        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        releaseDateTF.setConverter(converter);

    }

    public TextField getImagePathTF() {
        return imagePathTF;
    }

    public TextField getTitleTF() {
        return titleTF;
    }

    public TextField getDurationTF() {
        return durationTF;
    }

    public TextField getAgeRestrictionTF() {
        return ageRestrictionTF;
    }

    public TextField getLanguageTF() {
        return languageTF;
    }

    public DatePicker getReleaseDateTF() {
        return releaseDateTF;
    }

    public TextField getTypeTF() {
        return typeTF;
    }

    public TextField getIdEventTF() {
        return idEventTF;
    }
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }
    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }



}

