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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.geometry.Insets;

public class EventsViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;
    private ObservableList<Event> list;
    @FXML private TableView<Event> tableView;
    @FXML private TableColumn<Event, String> columnId;
    @FXML private TableColumn<Event,String>columnTitle;
    @FXML private TableColumn<Event,String>columnDuration;
    @FXML private TableColumn<Event,String>columnAgeRestriction;
    @FXML private TableColumn<Event,String>columnLanguage;
    @FXML private TableColumn<Event,String>columnReleaseDate;
    @FXML private TableColumn<Event,String>columnType;
    @FXML private TableColumn<Event,String>columnImagePath;

    @FXML private Button EditButton;


    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<Event, String>("idEventType"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("Title"));
        columnType.setCellValueFactory(new PropertyValueFactory<Event, String>("Type"));
        columnDuration.setCellValueFactory(new PropertyValueFactory<Event, String>("Duration"));
        columnAgeRestriction.setCellValueFactory(new PropertyValueFactory<Event, String>("AgeRestriction"));
        columnLanguage.setCellValueFactory(new PropertyValueFactory<Event, String>("Language"));
        columnReleaseDate.setCellValueFactory(new PropertyValueFactory<Event, String>("ReleaseDate"));
        columnImagePath.setCellValueFactory(new PropertyValueFactory<Event, String>("imagePath"));

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void add(){
        viewManager.setAddRepertoireScene();
    }

    public void edit(){
        viewManager.setEditEventsScene();

    }

    public void back(){
        if(threadClient.isConnected()) {

        }
        else viewManager.setInitScene();
    }

    public void refresh(){
        threadClient.sendEventsCheckRequest();
        tableView.setItems(getValues());
        tableView.refresh();
    }

    public ObservableList<Event> getValues(){
        list = FXCollections.observableArrayList();
        for(ArrayList<String> x : threadClient.getEventsCheckData()){
            list.add(new Event(x.get(0),x.get(1),x.get(2),x.get(3),x.get(4),x.get(5),x.get(6),x.get(7)));
        }
        return list;
    }

    public void getTableValues(){
        EditButton.setDisable(false);

    }






    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
