package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RepertuarViewController {

    private ViewManager viewManager;
    private ThreadClient threadClient;
    private ObservableList<Repertoire> list;
    private Repertoire rowData;
    private AddRepertoireViewController addRepertoireViewController;
    @FXML private TableView<Repertoire> tableView;
    @FXML private TableColumn<Repertoire, String> columnIdEvent;
    @FXML private TableColumn<Repertoire, String> columnName;
    @FXML private TableColumn<Repertoire, String> columnDate;
    @FXML private TableColumn<Repertoire, String> columnStartTime;
    @FXML private TableColumn<Repertoire, String> columnIdEventType;
    @FXML private TableColumn<Repertoire, String> columnIdRoom;
    @FXML private TableColumn<Repertoire, String> columnMaxSeats;
    @FXML private TableColumn<Repertoire, String> columnNumOfRows;
    @FXML private Button ChooseButton;
    @FXML private Button EditButton;


    public void initialize() {
        columnIdEvent.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEvent"));
        columnName.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Name"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Date"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("StartTime"));
        columnIdEventType.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEventType"));
        columnIdRoom.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idRoom"));
        columnMaxSeats.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("maxSeats"));
        columnNumOfRows.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("numOfRows"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setRowFactory(tv -> {
            TableRow<Repertoire> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    if(ChooseButton.isDisabled())ChooseButton.setDisable(false);
                    if(EditButton.isDisabled())EditButton.setDisable(false);
                    rowData = row.getItem();
                }
            });
            return row;
        });
    }




    public void choose() {
        threadClient.sendEventSeatsRequest(Integer.parseInt(rowData.getIdEvent()));
        viewManager.setChoosingSeatScene(Integer.parseInt(rowData.getIdEvent()),Integer.parseInt(rowData.getMaxSeats())/Integer.parseInt(rowData.getNumOfRows()), Integer.parseInt(rowData.getNumOfRows()));
        if(ChooseButton.isDisabled()==false)ChooseButton.setDisable(true);
    }

    public void back(){


    }
    public void edit(){

        threadClient.sendGetIdAndNameOfEvents();
        threadClient.sendGetIdOfRooms();
        viewManager.setEditRepertoireScene();
        if(EditButton.isDisabled()==false)EditButton.setDisable(true);
    }
    public void add(){

        threadClient.sendGetIdOfRooms();
        threadClient.sendGetIdAndNameOfEvents();
        viewManager.setAddRepertoireScene();
    }
    public Repertoire getRowData() {
        return rowData;
    }

    public void refresh(){
        threadClient.sendRepertoireCheckRequest();
        threadClient.sendGetIdOfRooms();
        threadClient.sendGetIdAndNameOfEvents();
        tableView.setItems(getValues());
        tableView.refresh();
        if(ChooseButton.isDisabled()==false)ChooseButton.setDisable(true);
        if(EditButton.isDisabled()==false)EditButton.setDisable(true);

    }

    public ObservableList<Repertoire> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getRepertoireCheckData()){
            list.add(new Repertoire(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7)));
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