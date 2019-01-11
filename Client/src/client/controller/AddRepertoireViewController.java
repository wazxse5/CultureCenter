package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import javafx.collections.ObservableList;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddRepertoireViewController {




    private ViewManager viewManager;
    private ThreadClient threadClient;
    private List <String> lstFile;
    private ObservableList<ShortEvent> list;
    @FXML private TextField titleTF;
    @FXML private TextField timeTF;
    @FXML private DatePicker dateTF;




    @FXML private ComboBox<ShortEvent> filmID;
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
        dateTF.setValue( LocalDate.now());


        filmID.setConverter(new StringConverter<ShortEvent>(){
            @Override
            public String toString(ShortEvent object) {
                return object.getTitle();
            }
            @Override
            public ShortEvent fromString(String string) {
                return null;
            }
        });
    }

    public ObservableList<ShortEvent> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getIdAndNameOfEvents()){
            list.add(new ShortEvent(x.get(0),x.get(1)));
        }
        return list;
    }

    public void refresh(){
        viewManager.setAddRepertoireScene();
        filmID.setItems(getValues());
    }


    public void RestrictionTime(){
        timeTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9:]*")){
                timeTF.setText(oldValue);
            }
        });

    }


    public void confirm() {

        String  title = titleTF.getText();
        String  time = timeTF.getText();
        String  date = dateTF.getValue().toString();
        String  id = filmID.getValue().getIdEventType();

        if(!time.equals("")&&!title.equals("")&&!date.equals("")&&!id.equals("")) {
            threadClient.sendAddRepertoireRequest(title, time,date,id);
            infoLabel.setText("Dodano nowy film");
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


    }
    public void clear() {
        titleTF.clear();
        timeTF.clear();
        infoLabel.setText("");
    }



    public ComboBox getFilmID() {
        return filmID;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }


}

