package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Iterator;

public class LogsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private TableView<ArrayList> table;
    @FXML private TableView<ArrayList> tableView;
    @FXML private TableColumn<ArrayList, String> columnId;
    @FXML private TableColumn<ArrayList, String> columnUser;
    @FXML private TableColumn<ArrayList, String> columnMail;
    @FXML private TableColumn<ArrayList, String> columnDate;
    @FXML private TableColumn<ArrayList, String> columnType;
    @FXML private TableColumn<ArrayList, String> columnAddInfo;


    public void initialize() {

    }

    public void sendRestorePasswordRequest() {
       //RESTORE EMAIL POP UP
    }

    public void back(){

    }

    public void refresh(){
       // ObservableList<ArrayList<String>> tableView = FXCollections.observableArrayList();
  /*      TableColumn<ArrayList,String> columnId = new TableColumn<>("columnId");
        TableColumn<ArrayList,String>columnUser = new TableColumn<>("columnUser");
        TableColumn<ArrayList,String>columnMail = new TableColumn<>("columnMail");
        TableColumn<ArrayList,String>columnDate = new TableColumn<>("columnDate");
        TableColumn<ArrayList,String>columnType = new TableColumn<>("columnType");
        TableColumn<ArrayList,String>columnAddInfo = new TableColumn<>("columnAddInfo");
*/
  /*      columnId.setCellValueFactory(new PropertyValueFactory<>("columnId"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("columnUser"));
        columnMail.setCellValueFactory(new PropertyValueFactory<>("columnMail"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("columnDate"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("columnType"));
        columnAddInfo.setCellValueFactory(new PropertyValueFactory<>("columnAddInfo"));
*/
        //table = new TableView<>();
        tableView = new TableView<>();
       // table.setItems(getValues());
        System.out.println(getValues());
        tableView.setItems(getValues());
     //  table.getColumns().addAll(columnId,columnUser,columnMail,columnDate,columnType,columnAddInfo);
      //  tableView.getColumns().addAll(columnId,columnUser,columnMail,columnDate,columnType,columnAddInfo);
        //tableView = table;
    }


    public ObservableList<ArrayList> getValues(){
        ObservableList<ArrayList> list = FXCollections.observableArrayList();
        Iterator<ArrayList<String>> it = threadClient.getLogsCheckData().iterator();
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
