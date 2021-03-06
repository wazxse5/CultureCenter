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
    private ObservableList<Room> roomList;

    @FXML private TextField titleTF;
    @FXML private TextField timeTF;
    @FXML private DatePicker dateTF;
    @FXML private ComboBox<ShortEvent> filmID;
    @FXML private ComboBox<Room> roomCB;
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
        roomCB.setConverter(new StringConverter<Room>(){
            @Override
            public String toString(Room object) {
                return object.getNumber();
            }
            @Override
            public Room fromString(String string) {
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

    public ObservableList<Room> getRoomValues(){
        roomList = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getIdOfRooms()){
            roomList.add(new Room(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4)));
        }
        return roomList;
    }


    public void refresh(){
       threadClient.getIdAndNameOfEvents();
       filmID.setItems(getValues());
    }
    public void refreshRooms(){
        threadClient.sendGetIdOfRooms();
        roomCB.setItems(getRoomValues());
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
        String room = roomCB.getValue().getNumber();

        if(!time.equals("")&&!title.equals("")&&!date.equals("")&&!id.equals("")&&!room.equals("")) {
            threadClient.sendAddRepertoireRequest(title, time,date,id,room);
            infoLabel.setText("Wysłano żądanie dodania nowego seansu");
        } else infoLabel.setText("Proszę wypełnić wszystkie pola");

    }

    public void back() {

        threadClient.sendRepertoireCheckRequest();
        viewManager.setRepertoireScene();
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

