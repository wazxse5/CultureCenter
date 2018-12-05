package client;

import client.task.ConnectTask;
import client.task.ReceiveTask;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableView;
import message.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadClient {
    private BooleanProperty connected = new SimpleBooleanProperty(false);
    private StringProperty connectionState = new SimpleStringProperty("NONE");
    private BooleanProperty logged = new SimpleBooleanProperty(false);
    private StringProperty userName = new SimpleStringProperty();



    private TableView logsCheckData = new TableView();

    private Connection connection;

    private ViewManager viewManager;
    private ExecutorService executor;
    private ReceiveTask receiveTask;


    public ThreadClient() {
        this.executor = Executors.newCachedThreadPool();
    }

    public BooleanProperty getConnected(){
        return connected;
    }

    public void connect(String host, int port) {
        connectionState.setValue("CONNECTING");
        try {
            ConnectTask connectTask = new ConnectTask(host, port);
            connectTask.setOnSucceeded(event -> handleConnection((Connection) event.getSource().getValue()));
            connectTask.setOnFailed(event -> connectionState.setValue("NOT_CONNECTED"));
            executor.execute(connectTask);
        } catch (Exception e) {
            connectionState.setValue("NOT_CONNECTED");
        }
    }

    private void handleConnection(Connection connection) {
        if (connection != null) {
            connected.setValue(true);
            connectionState.setValue("CONNECTED");
            this.connection = connection;

            receiveTask = new ReceiveTask(connection.getInput());
            receiveTask.valueProperty().addListener((observable, oldValue, newValue) -> handleReceivedMessage(newValue));
            executor.execute(receiveTask);

        } else connectionState.setValue("NOT_CONNECTED");

    }

    private void handleReceivedMessage(Message message) {
        if (message instanceof LoginAnswerMessage) {
            LoginAnswerMessage loginAnswer = (LoginAnswerMessage) message;
            if (loginAnswer.isGood()) {
                viewManager.setRecommendationsScene();
                logged.setValue(true);
            } else {
                viewManager.getLoginViewController().setInfoLabel(loginAnswer.getInfoCode());
            }
        }
        if (message instanceof RegisterAnswerMessage) {
            RegisterAnswerMessage registerAnswer = (RegisterAnswerMessage) message;
        }
        if (message instanceof LogoutAnswerMessage) {
            logged.setValue(false);
        }
        if(message instanceof LogsCheckAnswerMessage){
            LogsCheckAnswerMessage logsAnswer = (LogsCheckAnswerMessage) message;
            logsCheckData = logsAnswer.getResult();
            System.out.println(logsCheckData);
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

    public void disconnect() {
        if (connection != null) {
            connection.send(new GoodbyeMessage());
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

    public StringProperty userNameProperty() {
        return userName;
    }
    public StringProperty getUserName(){
        return  userName;
    }
    public TableView getLogsCheckData() {
        return logsCheckData;
    }
}
