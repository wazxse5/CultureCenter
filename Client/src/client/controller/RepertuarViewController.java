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
    private ObservableList<Repertoire> list;
    @FXML private TableView<Repertoire> tableView;
    @FXML private TableColumn<Repertoire, String> columnIdEvent;
    @FXML private TableColumn<Repertoire, String> columnIdSchedule;
    @FXML private TableColumn<Repertoire, String> columnStatus;
    @FXML private TableColumn<Repertoire, String> columnStartDate;
    @FXML private TableColumn<Repertoire, String> columnEndDate;
    @FXML private TableColumn<Repertoire, String> columnCreationTime;
    @FXML private TableColumn<Repertoire, String> columnIdBranch;
    @FXML private TableColumn<Repertoire, String> columnName;
    @FXML private TableColumn<Repertoire, String> columnDate;
    @FXML private TableColumn<Repertoire, String> columnStartTime;
    @FXML private TableColumn<Repertoire, String> columnIdEventType;

    public void initialize() {
        columnIdEvent.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEvent"));
        columnIdSchedule.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idSchedule"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Status"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("StartDate"));
        columnEndDate.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("EndDate"));
        columnCreationTime.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("CreationTime"));
        columnIdBranch.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idBranch"));
        columnName.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Name"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Date"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("StartTime"));
        columnIdEventType.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEventType"));
    }

    public void reserve() {

    }

    public void buy(){

    }

    public void refresh(){
        tableView.setItems(getValues());
//        tableView.getColumns().addAll(columnId,columnUser,columnMail,columnDate,columnType,columnAddInfo);
        tableView.refresh();
    }

    public ObservableList<Repertoire> getValues(){
        // list.clear();
        // tableView.getItems().clear();
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getRepertoireCheckData()){
            list.add(new Repertoire(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7),x.get(8),x.get(9),x.get(10)));
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
