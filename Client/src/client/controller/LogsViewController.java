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
import java.util.Iterator;

public class LogsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private Iterator<ArrayList<String>> it;
    private ObservableList<ArrayList> list;
    @FXML private TableView<ArrayList> tableView;
    @FXML private TableColumn<ArrayList, String> columnId;
    @FXML private TableColumn<ArrayList, String> columnUser;
    @FXML private TableColumn<ArrayList, String> columnMail;
    @FXML private TableColumn<ArrayList, String> columnDate;
    @FXML private TableColumn<ArrayList, String> columnType;
    @FXML private TableColumn<ArrayList, String> columnAddInfo;


    public void initialize() {

        columnId.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("id"));
        columnUser.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("user"));
        columnMail.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("mail"));
        columnDate.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("date"));
        columnType.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("type"));
        columnAddInfo.setCellValueFactory(new PropertyValueFactory<ArrayList, String>("addInfo"));

    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void refresh(){

        tableView.setItems(getValues());
        tableView.refresh();
    }


    public ObservableList<ArrayList> getValues(){
       // list.clear();
        tableView.getItems().clear();
        list = FXCollections.observableArrayList();
        it = threadClient.getLogsCheckData().iterator();
        while(it.hasNext()){
            list.add(it.next());
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
