package server;

import exception.AuthenticationException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<Client> knownClients;
    private DBConnect dbConnect;
    private ObservableList<ObservableList>logsCheckData;
    private TableView tableview;
    private ArrayList<ArrayList> SQLarray;

    public DataLoader() {
        knownClients = new ArrayList<>();
        dbConnect = new DBConnect();
        logsCheckData = FXCollections.observableArrayList();
        tableview = new TableView();
        SQLarray = new ArrayList<ArrayList>();

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

    public synchronized ArrayList getLogs(String login) throws SQLException{
        logsCheckData = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            result = dbConnect.getLogs(login);
            int counter = 0;
            ArrayList<String> tables = new ArrayList<String>();

            for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++) {

                tables.add((result.getMetaData().getColumnName(i)));
            }
            SQLarray.add(tables);

            while(result.next()){
                ArrayList<String> newal = new ArrayList<String>();
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    newal.add(result.getString(i));
                }
                SQLarray.add(newal);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return SQLarray;
    }


}
