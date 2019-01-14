package client.controller;

import client.ThreadClient;
import client.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class HistoryViewController {
    private ViewManager viewManager;

    private ThreadClient threadClient;
    private ObservableList<History> list;
    private History rowData;

    @FXML private TableView<History> tableView;
    @FXML private TableColumn<History, String> columnIdTicket;
    @FXML private TableColumn<History, String> columnPrice;
    @FXML private TableColumn<History, String> columnBoughtDate;
    @FXML private TableColumn<History, String> columnType;
    @FXML private TableColumn<History, String> columnCondition;
    @FXML private TableColumn<History, String> columnIdEvent;
    @FXML private TableColumn<History, String> columnIdSeat;
    @FXML private TableColumn<History, String> columnIdClient;
    @FXML private TableColumn<History, String> columnName;
    @FXML private Button MarkButton;
    @FXML private Button regretBT;
    @FXML private Label textLabel;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();
    private Date date2 = new Date();
   private  Date datenow = new Date();

    public void initialize() {

        columnIdTicket.setCellValueFactory(new PropertyValueFactory<History, String>("idTicket"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<History, String>("price"));
        columnBoughtDate.setCellValueFactory(new PropertyValueFactory<History, String>("boughtDate"));
        columnType.setCellValueFactory(new PropertyValueFactory<History, String>("type"));
        columnCondition.setCellValueFactory(new PropertyValueFactory<History, String>("condition"));
        columnIdEvent.setCellValueFactory(new PropertyValueFactory<History, String>("idEvent"));
        columnIdSeat.setCellValueFactory(new PropertyValueFactory<History, String>("idSeat"));
        columnIdClient.setCellValueFactory(new PropertyValueFactory<History, String>("idClient"));
        columnName.setCellValueFactory(new PropertyValueFactory<History, String>("name"));


        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setRowFactory(tv -> {
            TableRow<History> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    if(MarkButton.isDisabled())MarkButton.setDisable(false);
                    rowData = row.getItem();
                    if(rowData.getCondition().equals("Zarezerwowany")) regretBT.setDisable(false);
                    else regretBT.setDisable(true);
                }
            });
            return row;
        });

    }

    public void mark() {
        try {
            date = dateFormat.parse(rowData.getBoughtDate());
            date2 = dateFormat.parse(dateFormat.format(datenow));
        } catch (ParseException e){
            e.printStackTrace();
        }
        if(getRowData().getCondition()!="Wycofany") {
            if (date2.after(date)) {
                viewManager.setReviewScene();
                MarkButton.setDisable(true);
            } else textLabel.setText("Proszę wybrać wydarzenie które się już odbyło");
        }else textLabel.setText("Proszę wybrać wydarzenie w którym brano udział");
    }

    public void back(){

    }

    public void regret(){
        threadClient.sendChangeTicketStatusRequest(rowData.getIdEvent());
    }

    public ObservableList<History> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getHistory()){
            list.add(new History(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7),x.get(8)));
        }
        return list;
    }
    public void refresh(){
        threadClient.sendHistoryCheckRequest(String.valueOf(threadClient.getUserID()));
        tableView.setItems(getValues());
        tableView.refresh();
    }

    public void refreshView() {
        tableView.setItems(getValues());
        tableView.refresh();
    }

    public History getRowData() {
        return rowData;
    }


    public TableView<History> getTableView() {
        return tableView;
    }



    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
