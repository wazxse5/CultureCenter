package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class EventsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private ObservableList<Event> list;
    @FXML private TableView<Event> tableView;
    @FXML private TableColumn<Event, String> columnId;
    @FXML private TableColumn<Event,String> columnName;
    @FXML private TableColumn<Event,String>columnStartTime;
    @FXML private TableColumn<Event,String>columnTitle;
    @FXML private TableColumn<Event,String>columnDuration;
    @FXML private TableColumn<Event,Integer>columnAgeRestriction;
    @FXML private TableColumn<Event,String>columnLanguage;
    @FXML private TableColumn<Event,String>columnReleaseDate;

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("id"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("startTime"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("duration"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("ageRestriction"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("language"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("releaseDate"));

    }

    public void reserve() {

    }

    public void buy(){

    }

    public void back(){
        if((boolean)threadClient.getConnected().get()) {
        }
         else viewManager.setInitScene();
    }

    public void refresh(){
        tableView.setItems(getValues());
        tableView.refresh();
    }

    public ObservableList<Event> getValues(){

        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getEventsCheckData()){
            list.add(new Event(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7),x.get(8)));
        }
        return list;
    }




    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
