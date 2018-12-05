package server;

import exception.AuthenticationException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<Client> knownClients;
    private DBConnect dbConnect;
    private ObservableList<ObservableList>logsCheckData;
    private TableView tableview;

    public DataLoader() {
        knownClients = new ArrayList<>();
        dbConnect = new DBConnect();
        logsCheckData = FXCollections.observableArrayList();
        tableview = new TableView();
    }

    public List<Client> getKnownClients() {
        return knownClients;
    }

    public void loadClients() {
        knownClients.add(new Client("test", "0000", "o@.pl"));
        knownClients.add(new Client("wazxse5", "1234", "adasd@o2.pl"));
        knownClients.add(new Client("admin", "admin", "cos@o2.pl"));
    }


    public synchronized int register(String name, String surname, String login, String password, String mail) throws SQLException {
        int result = dbConnect.addClient(name, surname, mail, login, password);
        return result;
    }

    public synchronized Client login(String name, String password) throws AuthenticationException, SQLException {
        int result = dbConnect.loginUser(name, password);
        if (result == 0) {
            return new Client(name);
        }
        if (result == 2) throw new NoSuchUserException();
        if (result == 3) throw new WrongPasswordException();
        return null;
    }

    public synchronized TableView getLogs(String login) throws SQLException{
        logsCheckData = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
             result = dbConnect.getLogs(login);

             //table columns
            for(int i=0 ; i<result.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(result.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            while(result.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(result.getString(i));
                }
                System.out.println("Row [1] added "+row );
                logsCheckData.add(row);

            }
            tableview.setItems(logsCheckData);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return tableview;
    }


}
