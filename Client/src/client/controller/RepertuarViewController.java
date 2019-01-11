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
    @FXML private Button ChooseButton;


    public void initialize() {
        columnIdEvent.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEvent"));
        columnName.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Name"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("Date"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("StartTime"));
        columnIdEventType.setCellValueFactory(new PropertyValueFactory<Repertoire, String>("idEventType"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setRowFactory(tv -> {
            TableRow<Repertoire> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    if(ChooseButton.isDisabled())ChooseButton.setDisable(false);
                    rowData = row.getItem();
                }
            });
            return row;
        });
    }




    public void choose() {
       // viewManager.setChoosingSeatScene();
        if(ChooseButton.isDisabled()==false)ChooseButton.setDisable(true);
    }

    public void back(){


    }

    public void add(){
        threadClient.sendGetIdAndNameOfEvents();
        viewManager.setAddRepertoireScene();
    }
    public Repertoire getRowData() {
        return rowData;
    }

    public void refresh(){
        tableView.setItems(getValues());
        tableView.refresh();
        if(ChooseButton.isDisabled()==false)ChooseButton.setDisable(true);
    }

    public ObservableList<Repertoire> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getRepertoireCheckData()){
            list.add(new Repertoire(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4)));
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