package client;

import client.task.ConnectTask;
import client.task.ReceiveTask;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import message.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadClient {
    private BooleanProperty connected = new SimpleBooleanProperty(false);
    private StringProperty connectionState = new SimpleStringProperty("NONE");
    private BooleanProperty logged = new SimpleBooleanProperty(false);
    private StringProperty userName = new SimpleStringProperty();

    private ArrayList<ArrayList<String>> logsCheckData = new ArrayList<>();
    private ArrayList<ArrayList<String>> eventsCheckData = new ArrayList<>();
    private ArrayList<ArrayList<String>> repertoireCheckData = new ArrayList<>();
    private Connection connection;

    private ViewManager viewManager;
    private ExecutorService executor;
    private ReceiveTask receiveTask;



    private String regex= String.format("[a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ,._ ]*[_a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ0-9,._ ]*");



    private String regexEmail=String.format("[a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ_@.]*[_a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ0-9,_@.]*");

    public ThreadClient() {
        this.executor = Executors.newCachedThreadPool();
    }

    public void connect(String host, int port) {
        connectionState.setValue("CONNECTING");
        try {
            ConnectTask connectTask = new ConnectTask(host, port);
            connectTask.setOnSucceeded(event -> handleNewConnection((Connection) event.getSource().getValue()));
            connectTask.setOnFailed(event -> connectionState.setValue("NOT_CONNECTED"));
            executor.execute(connectTask);
        } catch (Exception e) {
            connectionState.setValue("NOT_CONNECTED");
        }
    }

    private void handleNewConnection(Connection newConnection) {
        if (newConnection != null) {
            connected.setValue(true);
            connectionState.setValue("CONNECTED");
            connection = newConnection;

            receiveTask = new ReceiveTask(newConnection.getInput());
            receiveTask.valueProperty().addListener((observable, oldValue, newValue) -> handleReceivedMessage(newValue));
            executor.execute(receiveTask);
        } else connectionState.setValue("NOT_CONNECTED");
    }

    private void handleReceivedMessage(Message message) {
        if (message instanceof LoginAnswerMessage) {
            LoginAnswerMessage loginAnswer = (LoginAnswerMessage) message;
            if (loginAnswer.isGood()) {
                logged.setValue(true);
                viewManager.setRecommendationsScene();
                connection.setUserData(loginAnswer.getUserName(), loginAnswer.getUserSurName(), loginAnswer.getUserMail(), loginAnswer.getUserLogin());
            } else {
                viewManager.getLoginViewController().setInfoLabel(loginAnswer.getInfoCode());
            }
        }

        if (message instanceof AddRepertuarAnswerMessage){
            AddRepertuarAnswerMessage answerMessage = (AddRepertuarAnswerMessage) message;

        }
        if (message instanceof RegisterAnswerMessage) {
            RegisterAnswerMessage registerAnswer = (RegisterAnswerMessage) message;
        }
        if (message instanceof LogoutAnswerMessage) {
            logged.setValue(false);
            connection.setUserData("","","","");
        }
        if(message instanceof LogsCheckAnswerMessage){
            LogsCheckAnswerMessage logsAnswer = (LogsCheckAnswerMessage) message;
            logsCheckData = logsAnswer.getResult();
        }
        if(message instanceof EventsCheckAnswerMessage){
            EventsCheckAnswerMessage eventsAnswer = (EventsCheckAnswerMessage) message;
            eventsCheckData = eventsAnswer.getResult();
        }
        if(message instanceof RepertoireCheckAnswerMessage){
            RepertoireCheckAnswerMessage repertoireAnswer = (RepertoireCheckAnswerMessage) message;
            repertoireCheckData = repertoireAnswer.getResult();
        }
        if (message instanceof ChangeUserDataAnswerMessage) {
            ChangeUserDataAnswerMessage answerMessage = (ChangeUserDataAnswerMessage) message;
            viewManager.handleChangeUserDateAnswet(answerMessage);
        }

    }
    public void sendAddRepertuarRequest(String imagePath,String title, String duration, String ageRestriction, String language, String releaseDate, String type){
        if(connected.get()){
            connection.send(new AddRepertuarRequestMessage(imagePath,title,duration,ageRestriction,language,releaseDate,type));
        }
    }
    public void sendLoginRequest(String name, String password) {
        if (connected.get()) {
            connection.send(new LoginRequestMessage(name, password));
            this.userName.setValue(name);
        } else viewManager.getLoginViewController().setInfoLabel("Brak połączenia z serwerem");
    }

    public void sendRegisterRequest(String name, String surname, String login, String password, String email) {
        if(connected.get()){
            connection.send(new RegisterRequestMessage(name, surname, login,password,email));
        } else viewManager.getLoginViewController().setInfoLabel("Brak połączenia z serwerem");
    }

    public void sendLogoutRequest() {
        if (connected.get()) {
            connection.send(new LogoutRequestMessage());
        }
    }

    public void sendLogsCheckRequest(String login){
        if(connected.get()){
            connection.send(new LogsCheckRequestMessage(login));
        }
    }
    public void sendEventsCheckRequest(){
        if(connected.get()){
            connection.send(new EventsCheckRequestMessage());
        }

    }
    public void sendRepertoireCheckRequest(){
        if(connected.get()){
            connection.send(new RepertoireCheckRequestMessage());
        }
    }

    public void sendChangeUserDataRequest(ChangeUserDataRequestMessage changeMessage) {
        connection.send(changeMessage);
    }
    public void sendEditEventsRequest(String idEvent,String title,String duration,String ageRestriction,String language,String releaseDate,String type,String imagePath){
        if(connected.get()) {
            connection.send(new EventsEditRequestMessage(idEvent, title, duration, ageRestriction, language, releaseDate, type, imagePath));
        }
    }

    public void disconnect() {
        if (connection != null) {
            connection.close();
        }
        if (receiveTask != null) receiveTask.cancel(true);
        executor.shutdown();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        viewManager.connectionStateProperty().bind(connectionState);
    }

    public BooleanProperty loggedProperty() {
        return logged;
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }

    public boolean isConnected() {
        return connected.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getUserName() {
        return userName.get();
    }

    public ArrayList<ArrayList<String>> getLogsCheckData() {
        return logsCheckData;
    }
    public ArrayList<ArrayList<String>> getRepertoireCheckData() {
        return repertoireCheckData;
    }

    public ArrayList<ArrayList<String>> getEventsCheckData() {
        return eventsCheckData;
    }

    public Connection getConnection() {
        return connection;
    }
    public String getRegex() {
        return regex;
    }
    public String getRegexEmail() {
        return regexEmail;
    }

}
