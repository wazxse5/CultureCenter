package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class LogsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private ObservableList<Log> list;
    @FXML private TableView<Log> tableView;
    @FXML private TableColumn<Log, String> columnId;
    @FXML private TableColumn<Log, String> columnUser;
    @FXML private TableColumn<Log, String> columnMail;
    @FXML private TableColumn<Log, String> columnDate;
    @FXML private TableColumn<Log, String> columnType;
    @FXML private TableColumn<Log, String> columnAddInfo;
    @FXML private Button refreshButton;


    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<Log, String>("id"));
        columnUser.setCellValueFactory(new PropertyValueFactory<Log, String>("user"));
        columnMail.setCellValueFactory(new PropertyValueFactory<Log, String>("mail"));
        columnType.setCellValueFactory(new PropertyValueFactory<Log, String>("type"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Log, String>("date"));
        columnAddInfo.setCellValueFactory(new PropertyValueFactory<Log, String>("addInfo"));
    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void refresh(){
        tableView.setItems(getValues());
//        tableView.getColumns().addAll(columnId,columnUser,columnMail,columnDate,columnType,columnAddInfo);
       tableView.refresh();
    }


    public ObservableList<Log> getValues(){
       // list.clear();
       // tableView.getItems().clear();
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getLogsCheckData()){
            list.add(new Log(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5)));
        }
        return list;
    }


    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
        refreshButton.disableProperty().bind(threadClient.loggedProperty().not());
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }


}
