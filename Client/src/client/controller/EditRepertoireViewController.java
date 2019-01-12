package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditRepertoireViewController {




    private ViewManager viewManager;
    private ThreadClient threadClient;
    private List <String> lstFile;
    private ObservableList<ShortEvent> list;
    private ObservableList<Room> roomList;
    @FXML private TextField titleTF;
    @FXML private TextField timeTF;
    @FXML private TextField idEventTF;
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



    public ObservableList<Room> getRoomValues(){
        roomList = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getIdOfRooms()){
            roomList.add(new Room(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4)));
        }
        return roomList;
    }
    public ObservableList<ShortEvent> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getIdAndNameOfEvents()){
            list.add(new ShortEvent(x.get(0),x.get(1)));
        }
        return list;
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
        String eventId = idEventTF.getText();
        String  title = titleTF.getText();
        String  time = timeTF.getText();
        String  date = dateTF.getValue().toString();
        String  id = filmID.getValue().getIdEventType();
        String room = roomCB.getValue().getNumber();

        if(!time.equals("")&&!title.equals("")&&!date.equals("")&&!id.equals("")&&!room.equals("")) {
            threadClient.sendEditRepertoireRequest(title, time,date,id,room,eventId);
            infoLabel.setText("Dodano edytowano seans");
        } else infoLabel.setText("Proszę wypełnić wszystkie pola");
    }

    public void back() {
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
    public TextField getTitleTF() {
        return titleTF;
    }

    public TextField getTimeTF() {
        return timeTF;
    }

    public DatePicker getDateTF() {
        return dateTF;
    }

    public ComboBox<Room> getRoomCB() {
        return roomCB;
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


}

