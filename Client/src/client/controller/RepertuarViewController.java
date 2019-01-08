package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RepertuarViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private ObservableList<Event> list;
    @FXML private TableView<Event> tableView;
    @FXML private TableColumn<Event, String> columnId;
    @FXML private TableColumn<Event,String> columnName;
    @FXML private TableColumn<Event,String> columnDate;
    @FXML private TableColumn<Event,String>columnStartTime;
    @FXML private TableColumn<Event,String>columnTitle;
    @FXML private TableColumn<Event,String>columnDuration;
    @FXML private TableColumn<Event,String>columnAgeRestriction;
    @FXML private TableColumn<Event,String>columnLanguage;
    @FXML private TableColumn<Event,String>columnReleaseDate;
    @FXML private TableColumn<Event,String>columnType;

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("idEventType"));
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("idEvent"));
        columnName.setCellValueFactory(new PropertyValueFactory<Event, String>("Name"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Event, String>("Date"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("StartTime"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("Title"));
        columnType.setCellValueFactory(new PropertyValueFactory<Event, String>("Type"));
        columnDuration.setCellValueFactory(new PropertyValueFactory<Event, String>("Duration"));
        columnAgeRestriction.setCellValueFactory(new PropertyValueFactory<Event, String>("AgeRestriction"));
        columnLanguage.setCellValueFactory(new PropertyValueFactory<Event, String>("Language"));
        columnReleaseDate.setCellValueFactory(new PropertyValueFactory<Event, String>("ReleaseDate"));

    }

    public void reserve() {

    }

    public void buy(){

    }
    public void add(){
        viewManager.setAddRepertoireScene();
    }

    public void edit(){


    }

    public void back(){
        if(threadClient.isConnected()) {
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
            list.add(new Event(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7),x.get(8),x.get(9),x.get(10)));
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
