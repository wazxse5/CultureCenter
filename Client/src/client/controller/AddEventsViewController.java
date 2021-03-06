package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.*;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AddEventsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private List <String> lstFile;
    private File selectedImageFile;

    @FXML private TextField imagePathTF;
    @FXML private TextField titleTF;
    @FXML private TextField durationTF;
    @FXML private TextField ageRestrictionTF;
    @FXML private TextField languageTF;
    @FXML private DatePicker releaseDateTF;
    @FXML private TextField typeTF;

    @FXML private Label infoLabel;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Button clearButton;
    private final String pattern = "yyyy-MM-dd";

    public void initialize() {
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
        lstFile.add("*.png");
        lstFile.add("*.jpeg");
        releaseDateTF.setValue( LocalDate.now());
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
       // SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
       // durationTF.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));
    }


    public void confirm() {
        String imagePath = imagePathTF.getText();
        String title = titleTF.getText();
        String duration = durationTF.getText();
        String ageRestriction = ageRestrictionTF.getText();
        String language = languageTF.getText();
        String releaseDate = releaseDateTF.getValue().toString();
        String type = typeTF.getText();
        byte[] image = new byte[0];
        try {
            image = Files.readAllBytes(Paths.get(selectedImageFile.getPath()));
        } catch (IOException e) { e.printStackTrace(); }

        if(!imagePath.equals("")&&!title.equals("")&&!duration.equals("")&&!ageRestriction.equals("")&&!language.equals("")&&!releaseDate.equals("")&&!type.equals("")) {
            threadClient.sendAddEventsRequest(image, imagePath, title, duration, ageRestriction, language, releaseDate, type);
            infoLabel.setText("Wysłanie żądania dodania nowego filmu");
        } else infoLabel.setText("Proszę wypełnić wszystkie pola");
    }

    public void back() {
        viewManager.setEventsScene();
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
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Obrazy",lstFile));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            imagePathTF.setText(file.getAbsolutePath());
            selectedImageFile = file;
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



    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }


}

