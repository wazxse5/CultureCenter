package server;

import exception.AuthenticationException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import message.ChangeUserDataAnswerMessage;
import message.ChangeUserDataRequestMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<Client> knownClients;
    private DBConnect dbConnect;
    private ObservableList<ObservableList>logsCheckData;
    private ObservableList<ObservableList>eventsCheckData;
    private ObservableList<ObservableList>repertuarCheckData;
    private ObservableList<ObservableList> idAndNameOfEvents;


    private TableView tableview;
    private ArrayList<ArrayList> SQLarray;
    private ArrayList<ArrayList> SQLEventsarray;
    private ArrayList<ArrayList> SQLRepertuararray;
    private ArrayList<ArrayList> SQLidAndNamesOfEventsarray;

    public DataLoader() {
        dbConnect = new DBConnect();
        try {
            knownClients = dbConnect.getAllClients();
        } catch (SQLException e) { e.printStackTrace(); }
        logsCheckData = FXCollections.observableArrayList();
        tableview = new TableView();
    }

    public List<Client> getKnownClients() {
        return knownClients;
    }

    public synchronized int register(String name, String surname, String login, String password, String mail) throws SQLException {
        String result = dbConnect.addClient(name, surname, mail, login, password);
        if (result.equals("Zarejestrowano!")){
            int id = dbConnect.getClientID(login);
            knownClients.add(new Client(id, name, surname, mail, login));
            return 0;
        }
        if (result.equals("Podany login już istnieje!")) return 1;
        else return 4;
    }

    public synchronized Client login(String login, String password) throws AuthenticationException, SQLException {
        String result = dbConnect.loginUser(login, password);
        if (result.startsWith("Zalogowano jako")) {
            for (Client client : knownClients) if (client.getLogin().equals(login)) return client;
        }
        if (result.equals("Nieistniejący login!")) throw new NoSuchUserException();
        if (result.equals("Niepoprawne hasło!")) throw new WrongPasswordException();
        return null;
    }

    public synchronized ArrayList getLogs(String login) throws SQLException{
        logsCheckData = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLarray = new ArrayList<ArrayList>();
            result = dbConnect.getLogs(login);

            ArrayList<String> tables = new ArrayList<String>();
/*
            for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++) {

                tables.add((result.getMetaData().getColumnName(i)));
            }
            SQLarray.add(tables);*/

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

    public synchronized  ArrayList getRepertuar()throws SQLException{
        repertuarCheckData = FXCollections.observableArrayList();
        ResultSet result=null;
        try{
            SQLRepertuararray = new ArrayList<ArrayList>();
            result= dbConnect.getRepertuar();

            ArrayList<String> tables = new ArrayList<String>();
            while(result.next()){
                ArrayList<String> newal = new ArrayList<String>();
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    newal.add(result.getString(i));
                }
                SQLRepertuararray.add(newal);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return SQLRepertuararray;
    }

    public synchronized ArrayList getEvents() throws SQLException{
        eventsCheckData= FXCollections.observableArrayList();
        ResultSet result = null;
        try{
            SQLEventsarray = new ArrayList<ArrayList>();
            result = dbConnect.getEvents();
            ArrayList<String> tables = new ArrayList<String>();
            while(result.next()){
                ArrayList<String> newal = new ArrayList<String>();
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    newal.add(result.getString(i));
                }
                SQLEventsarray.add(newal);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return SQLEventsarray;


    }

    public synchronized ArrayList getIdAndNameOfEvents() throws SQLException{
        idAndNameOfEvents = FXCollections.observableArrayList();
        ResultSet result = null;
        try{
            SQLidAndNamesOfEventsarray = new ArrayList<ArrayList>();
            result = dbConnect.getIdAndNameOfEvents();
            ArrayList<String> tables = new ArrayList<String>();
            while(result.next()){
                ArrayList<String> newal = new ArrayList<String>();
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    newal.add(result.getString(i));
                }
                SQLidAndNamesOfEventsarray.add(newal);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return SQLidAndNamesOfEventsarray;
    }

    public ChangeUserDataAnswerMessage changeUserData(String userLogin, ChangeUserDataRequestMessage changeMessage) {
        boolean nameChanged = false, surnameChanged = false, mailChanged = false, passwordChanged = false;
        if (changeMessage.isChangeName()) nameChanged = dbConnect.changeName(userLogin, changeMessage.getNewName());
        if (changeMessage.isChangeSurname()) surnameChanged = dbConnect.changeSurname(userLogin, changeMessage.getNewSurname());
        if (changeMessage.isChangeMail()) mailChanged = dbConnect.changeMail(userLogin, changeMessage.getNewMail());
        if (changeMessage.isChangePassword()) passwordChanged = dbConnect.changePassword(userLogin, changeMessage.getCurrentPassword(), changeMessage.getNewPassword());

        ChangeUserDataAnswerMessage answerMessage = new ChangeUserDataAnswerMessage(nameChanged, surnameChanged, mailChanged, passwordChanged);
        return answerMessage;
    }

    public boolean addEmployee(String name, String surname, String department, String login, String password, int salary) {
        try {
            String result = dbConnect.addEmployee(name, surname, department, login, password, salary);
            if (result.equals("Dodano pracownika!")) return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean addRepertoire(String imagePath, String title, String duration, String ageRestriction,String language, String releaseDate, String type){
        try{
            ResultSet result = null;
            result = dbConnect.addRepertoire(imagePath,title,duration,ageRestriction,language,releaseDate,type);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String editEvents(String idEvent, String title, String duration, String ageRestriction,String language, String releaseDate, String type,String imagePath){
        String result = null;
        try{
            result = dbConnect.editEvent(idEvent,title,duration,ageRestriction,language,releaseDate,type,imagePath).toString();
        }catch (SQLException e){
            e.printStackTrace();
            return "Błąd";
        }
        return result;
    }
}
