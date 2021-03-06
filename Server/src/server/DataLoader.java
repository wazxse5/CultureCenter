package server;

import exception.AuthenticationException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import message.AddEventsRequestMessage;
import message.ChangeUserDataAnswerMessage;
import message.ChangeUserDataRequestMessage;
import message.ImageEventTypeMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private File eventTypeImagesDir;
    private ArrayList<Integer> recommended;

    private List<Client> knownClients;
    private List<Employee> knownEmployees;
    private DBConnect dbConnect;
    private ObservableList<ObservableList> logsCheckData;
    private ObservableList<ObservableList> eventsCheckData;
    private ObservableList<ObservableList> repertuarCheckData;
    private ObservableList<ObservableList> idAndNameOfEvents;
    private ObservableList<ObservableList> idOfRooms;
    private ObservableList<ObservableList> infos;
    private ObservableList<ObservableList> history;

    private TableView tableview;
    private ArrayList<ArrayList> SQLarray;
    private ArrayList<ArrayList> SQLEventsarray;
    private ArrayList<ArrayList> SQLRepertuararray;
    private ArrayList<ArrayList> SQLidAndNamesOfEventsarray;
    private ArrayList<ArrayList> SQLidOfRoomsarray;
    private ArrayList<ArrayList> SQLinfosarray;
    private ArrayList<ArrayList> SQLhistoryarray;

    public DataLoader() {
        dbConnect = new DBConnect();
        try {
            knownClients = dbConnect.getAllClients();
            knownEmployees = dbConnect.getAllEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logsCheckData = FXCollections.observableArrayList();
        tableview = new TableView();
        recommended = new ArrayList<>();
    }

    public List<Client> getKnownClients() {
        return knownClients;
    }

    public synchronized int register(String name, String surname, String login, String password, String mail) throws SQLException {
        String result = dbConnect.addClient(name, surname, mail, login, password);
        if (result.equals("Zarejestrowano!")) {
            int id = dbConnect.getClientID(login);
            knownClients.add(new Client(id, name, surname, mail, login));
            return 0;
        }
        if (result.equals("Podany login już istnieje!")) return 1;
        else return 4;
    }

    public synchronized Client login(String login, String password, boolean employee) throws AuthenticationException, SQLException {
        String result = dbConnect.loginUser(login, password, employee);
        if (result.startsWith("Zalogowano jako")) {
            if (employee) {
                for (Employee employees : knownEmployees) if (employees.getLogin().equals(login)) return employees;
            }
            else {
                for (Client client : knownClients) if (client.getLogin().equals(login)) return client;
            }
        }
        if (result.equals("Nieistniejący login!")) throw new NoSuchUserException();
        if (result.equals("Niepoprawne hasło!")) throw new WrongPasswordException();
        return null;
    }

    public synchronized ArrayList getLogs(String login) throws SQLException {
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

            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLarray;
    }

    public synchronized ArrayList getRepertuar() throws SQLException {
        repertuarCheckData = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLRepertuararray = new ArrayList<ArrayList>();
            result = dbConnect.getRepertuar();

            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLRepertuararray.add(newal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLRepertuararray;
    }

    public synchronized ArrayList getEvents() throws SQLException {
        eventsCheckData = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLEventsarray = new ArrayList<ArrayList>();
            result = dbConnect.getEvents();
            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLEventsarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLEventsarray;


    }

    public synchronized ArrayList getHistory(String idClient) throws SQLException{
        history = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLhistoryarray = new ArrayList<ArrayList>();
            result = dbConnect.getHistory(idClient);
            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLhistoryarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLhistoryarray;

    }


    public synchronized ArrayList getIdAndNameOfEvents() throws SQLException {
        idAndNameOfEvents = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLidAndNamesOfEventsarray = new ArrayList<ArrayList>();
            result = dbConnect.getIdAndNameOfEvents();
            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLidAndNamesOfEventsarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLidAndNamesOfEventsarray;
    }

    public synchronized ArrayList getInfos() throws SQLException {
        infos = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLinfosarray = new ArrayList<ArrayList>();
            result = dbConnect.getInfos();
            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLinfosarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLinfosarray;
    }


    public synchronized ArrayList getIdOfRooms() throws SQLException {
        idOfRooms = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            SQLidOfRoomsarray = new ArrayList<ArrayList>();
            result = dbConnect.getIdOfRooms();
            ArrayList<String> tables = new ArrayList<String>();
            while (result.next()) {
                ArrayList<String> newal = new ArrayList<String>();
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    newal.add(result.getString(i));
                }
                SQLidOfRoomsarray.add(newal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLidOfRoomsarray;

    }

    public ChangeUserDataAnswerMessage changeUserData(String userLogin, ChangeUserDataRequestMessage changeMessage) {
        Client changingClient = null;
        for (Client c : knownClients) if (c.getLogin().equals(userLogin)) changingClient = c;
        if (changingClient == null) for (Employee e : knownEmployees) if (e.getLogin().equals(userLogin)) changingClient = e;

        boolean nameChanged = false, surnameChanged = false, mailChanged = false, passwordChanged = false;
        if (changeMessage.isChangeName()) {
            nameChanged = dbConnect.changeName(userLogin, changeMessage.getNewName());
            changingClient.setName(changeMessage.getNewName());
        }
        if (changeMessage.isChangeSurname()) {
            surnameChanged = dbConnect.changeSurname(userLogin, changeMessage.getNewSurname());
            changingClient.setSurname(changeMessage.getNewSurname());
        }
        if (changeMessage.isChangeMail()) {
            mailChanged = dbConnect.changeMail(userLogin, changeMessage.getNewMail());
            changingClient.setMail(changeMessage.getNewMail());
        }
        if (changeMessage.isChangePassword()) {
            passwordChanged = dbConnect.changePassword(userLogin, changeMessage.getCurrentPassword(), changeMessage.getNewPassword());
        }

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

    public boolean addRoom(Integer number, Integer seats, Integer rows, Integer branchId) {
        try {
            String result = dbConnect.addRoom(number, seats, rows, branchId);
            if (result.equals("Dodano salę!")) return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean addRepertoire(AddEventsRequestMessage addEventsRequestMessage) {
        String imagePath = addEventsRequestMessage.getImagePath();
        byte[] image = addEventsRequestMessage.getImage();
        String imageExtension = addEventsRequestMessage.getImageExtension();
        String title = addEventsRequestMessage.getTitle();
        String duration = addEventsRequestMessage.getDuration();
        String ageRestriction = addEventsRequestMessage.getAgeRestriction();
        String language = addEventsRequestMessage.getLanguage();
        String releaseDate = addEventsRequestMessage.getReleaseDate();
        String type = addEventsRequestMessage.getType();

        try {
            ResultSet result = null;
            result = dbConnect.addRepertoire(imagePath, title, duration, ageRestriction, language, releaseDate, type);
            if (result.getString(1).equals("Dodano rodzaj wydarzenia!")) {
                int idEventType = result.getInt(2);
                saveImageEventType(idEventType, imageExtension, image);

                for (int i = recommended.size()-1; i > 0; i--) {
                    recommended.set(i, recommended.get(i-1));
                }
                recommended.set(0, idEventType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void addReservation(int idClient, int idEvent, List<Integer> seats, String type, String price, String condition) throws SQLException {
        float fPrice = Float.parseFloat(price);
        dbConnect.addReservation(idClient, idEvent, seats, type, fPrice, condition);
    }

    public boolean addEvent(String name, String time, String date, String idEvent, String idRoom) {
        try {
            ResultSet result = null;

            result = dbConnect.addEvent(name, time, date, idEvent, idRoom);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public void addReview(int idEvent, int idUser, int grade, String opinion,String type)throws  SQLException{
        try {
            dbConnect.addReview(idEvent, idUser, grade, opinion, type);
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public String editEvents(String idEvent, String title, String duration, String ageRestriction, String language, String releaseDate, String type, String imagePath) {
        String result = null;
        try {
            result = dbConnect.editEvent(idEvent, title, duration, ageRestriction, language, releaseDate, type, imagePath).toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Błąd";
        }
        return result;
    }
    public String changeTicketStatus(String idTicket){
        String result ="";
        try{
         result=   dbConnect.changeTicketStatus(idTicket);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public String editRepertoire(String title, String date, String time, String idEventType, String idRoom, String idEvent) {
        String result = "";
        try {
            result = dbConnect.editRepertoire(title, date, time, idEventType, idRoom, idEvent).toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Błąd";
        }

        return result;
    }

    public byte[] getEventTypeImage(int idEventType) {
        File image = new File(eventTypeImagesDir, idEventType + ".jpg");
        if (!image.exists()) image = new File(eventTypeImagesDir, idEventType + ".png");
        try {
            return Files.readAllBytes(Paths.get(image.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveImageEventType(int eventTypeId, String extension, byte[] image) {
        String fullName = eventTypeId + "." + extension;
        File fileImage = new File(eventTypeImagesDir, fullName);

        try (FileOutputStream stream = new FileOutputStream(fileImage)) {
            stream.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail(String email) {
        String result = "";
        try {
            result = dbConnect.getEmail(email);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String addInfo(String info) {
        String result = "";
        try {
            result = dbConnect.addInfo(info);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<Integer> getSeatsId(int idEvent) throws SQLException {
        return dbConnect.getSeatsId(idEvent);
    }


    private void loadRecommended() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        File recommended = new File(eventTypeImagesDir, "recommended.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(recommended));
            String line;
            while ((line = br.readLine()) != null) {
                int r = Integer.parseInt(line);
                arrayList.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.recommended = arrayList;
    }


    public ArrayList<Integer> getRecommended() {
        return recommended;
    }

    public void setEventTypeImagesDir(File eventTypeImagesDir) {
        this.eventTypeImagesDir = eventTypeImagesDir;
        loadRecommended();
    }
}
