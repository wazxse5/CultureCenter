package client;

import client.controller.EditEventsViewController;
import client.task.ConnectTask;
import client.task.ImageRequestingTask;
import client.task.ReceiveTask;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import message.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadClient {
    private BooleanProperty connected = new SimpleBooleanProperty(false);
    private StringProperty connectionState = new SimpleStringProperty("NONE");
    private BooleanProperty logged = new SimpleBooleanProperty(false);
    private BooleanProperty loggedAsEmployee = new SimpleBooleanProperty();
    private StringProperty userName = new SimpleStringProperty();
    private int userID;
    private ArrayList<ArrayList<String>> logsCheckData = new ArrayList<>();
    private ArrayList<ArrayList<String>> eventsCheckData = new ArrayList<>();
    private ArrayList<ArrayList<String>> repertoireCheckData = new ArrayList<>();
    private ArrayList<ArrayList<String>> idAndNameOfEvents = new ArrayList<>();
    private ArrayList<ArrayList<String>> infos = new ArrayList<>();
    private ArrayList<ArrayList<String>> history = new ArrayList<>();
    private ArrayList<ArrayList<String>>  idOfRooms = new ArrayList<>();
    private List<Integer> seatsID = new ArrayList<>();

    private String editEventsAnswerMsg;
    private Connection connection;
    private EditEventsViewController editEventsViewController;
    private ViewManager viewManager;
    private ExecutorService executor;
    private ReceiveTask receiveTask;
    private DataLoader dataLoader;
    private BlockingQueue<ImageEventTypeMessage> imageRequestQueue = new LinkedBlockingDeque<>();
    private Semaphore imageRequestSemaphore = new Semaphore(1);

    private String regex = String.format("[a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ,._ ]*[_a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ0-9,._ ]*");


    private String regexEmail = String.format("[a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ_@.]*[_a-zżźćńłąśóA-ZŻŹĆŃŁĄŚÓ0-9,_@.]*");

    public ThreadClient() {
        this.executor = Executors.newCachedThreadPool();
        this.dataLoader = new DataLoader(this);
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
            ImageRequestingTask imageRequestingTask = new ImageRequestingTask(this, imageRequestSemaphore, imageRequestQueue);
            executor.execute(receiveTask);
            executor.execute(imageRequestingTask);
        } else connectionState.setValue("NOT_CONNECTED");
    }

    private void handleReceivedMessage(Message message) {
        if (message instanceof FirstInfoMessage) {
            FirstInfoMessage firstInfoMessage = (FirstInfoMessage) message;
            dataLoader.setRecommendationValues(firstInfoMessage.getRecommended());
        }
        if (message instanceof LoginAnswerMessage) {
            LoginAnswerMessage loginAnswer = (LoginAnswerMessage) message;
            if (loginAnswer.isGood()) {
                logged.setValue(true);
                loggedAsEmployee.setValue(loginAnswer.isEmployee());
                userName.setValue(loginAnswer.getUserLogin());
                userID = loginAnswer.getUserID();
                connectionState.setValue("LOGGED");
                viewManager.setRecommendationsScene();
                connection.setUserData(loginAnswer.getUserName(), loginAnswer.getUserSurName(), loginAnswer.getUserMail(), loginAnswer.getUserLogin());
            } else {
                viewManager.getLoginViewController().setInfoLabel(loginAnswer.getInfoCode());
            }
        }

        if (message instanceof AddEventsAnswerMessage){
            AddEventsAnswerMessage answerMessage = (AddEventsAnswerMessage) message;

        }
        if (message instanceof RegisterAnswerMessage) {
            RegisterAnswerMessage registerAnswer = (RegisterAnswerMessage) message;
        }
        if (message instanceof LogoutAnswerMessage) {
            logged.setValue(false);
            connectionState.setValue("CONNECTED");
            loggedAsEmployee.setValue(false);
            connection.setUserData("", "", "", "");
        }
        if (message instanceof LogsCheckAnswerMessage) {
            LogsCheckAnswerMessage logsAnswer = (LogsCheckAnswerMessage) message;
            logsCheckData = logsAnswer.getResult();
            viewManager.getLogsViewController().refresh();
        }
        if (message instanceof EventsCheckAnswerMessage) {
            EventsCheckAnswerMessage eventsAnswer = (EventsCheckAnswerMessage) message;
            eventsCheckData = eventsAnswer.getResult();
            viewManager.getEventsViewController().refreshView();
        }
        if (message instanceof RepertoireCheckAnswerMessage) {
            RepertoireCheckAnswerMessage repertoireAnswer = (RepertoireCheckAnswerMessage) message;
            repertoireCheckData = repertoireAnswer.getResult();
            viewManager.getRepertuarViewController().refreshView();
        }
        if (message instanceof ChangeUserDataAnswerMessage) {
            ChangeUserDataAnswerMessage answerMessage = (ChangeUserDataAnswerMessage) message;
            viewManager.handleChangeUserDateAnswet(answerMessage);
        }
        if (message instanceof ImageEventTypeMessage) {
            ImageEventTypeMessage imageEventTypeMessage = (ImageEventTypeMessage) message;
            dataLoader.saveImageEventType(imageEventTypeMessage);
            imageRequestSemaphore.release();
        }
        if(message instanceof  EventsEditAnswerMessage){
            EventsEditAnswerMessage answerMessage = (EventsEditAnswerMessage) message;
            editEventsAnswerMsg = answerMessage.getOk();
           if(!answerMessage.equals(""))  viewManager.getEditEventsViewController().getInfoLabel().setText("Zmieniono dane");
           else viewManager.getEditEventsViewController().getInfoLabel().setText("Błąd po stronie serwera");
           sendEventsCheckRequest();
        }
        if(message instanceof GetIdAndNameOfEventsAnswerMessage){
            GetIdAndNameOfEventsAnswerMessage answerMessage = (GetIdAndNameOfEventsAnswerMessage) message;
            idAndNameOfEvents = answerMessage.getResult();
        }
        if(message instanceof GetIdOfRoomsAnswerMessage){
            GetIdOfRoomsAnswerMessage answerMessage = (GetIdOfRoomsAnswerMessage) message;
            idOfRooms = answerMessage.getResult();
        }
        if(message instanceof  RepertoireEditAnswerMessage){
            RepertoireEditAnswerMessage answerMessage = (RepertoireEditAnswerMessage) message;
          //  viewManager.getRepertuarViewController().getTableView().refresh();
        }
        if(message instanceof GetInfosAnswerMessage){
            GetInfosAnswerMessage answerMessage= (GetInfosAnswerMessage) message;
            infos = answerMessage.getResult();
            viewManager.prepareInfos();

        }
        if(message instanceof  GetEmailAnswerMessage){
            GetEmailAnswerMessage answerMessage = (GetEmailAnswerMessage) message;
            viewManager.getRestorePasswordViewController().getTextLabel().setText(answerMessage.getEmail());

        }
        if (message instanceof EventSeatsAnswerMessage) {
            EventSeatsAnswerMessage answerMessage = (EventSeatsAnswerMessage) message;
            seatsID = answerMessage.getReserved();
            viewManager.updateChoosingSeatScene(answerMessage.getReserved());
        }
        if(message instanceof HistoryCheckAnswerMessage){
            HistoryCheckAnswerMessage answerMessage  = (HistoryCheckAnswerMessage) message;
            history = answerMessage.getResult();
//            viewManager.getHistoryViewController().getValues();
            viewManager.getHistoryViewController().refreshView();
        }

    }
    public void sendAddEventsRequest(byte[] image, String imagePath, String title, String duration, String ageRestriction, String language, String releaseDate, String type){
        if(connected.get()){
            connection.send(new AddEventsRequestMessage(image, imagePath, title,duration,ageRestriction,language,releaseDate,type));
        }
    }
    public void sendAddRepertoireRequest(String title, String time, String date, String idFilm,String room){
        if(connected.get()){
            connection.send(new AddRepertoireRequestMessage(title,time,date,idFilm,room));
        }

    }
    public void sendGetIdAndNameOfEvents(){
        if(connected.get()){
            connection.send(new GetIdAndNameOfEventsRequestMessage());
        }

    }
    public void sendGetIdOfRooms(){
        if(connected.get()){
            connection.send(new GetIdOfRoomsRequestMessage());
        }

    }
    public void sendRestorePasswordRequest(String email){
        if(connected.get()){
            connection.send(new GetEmailRequestMessage(email));
        }

    }
    public void sendLoginRequest(String name, String password, boolean asEmployee) {
        if (connected.get()) {
            connection.send(new LoginRequestMessage(name, password, asEmployee));
        } else viewManager.getLoginViewController().setInfoLabel("Brak połączenia z serwerem");
    }

    public void sendRegisterRequest(String name, String surname, String login, String password, String email) {
        if (connected.get()) {
            connection.send(new RegisterRequestMessage(name, surname, login, password, email));
        } else viewManager.getLoginViewController().setInfoLabel("Brak połączenia z serwerem");
    }

    public void sendLogoutRequest() {
        if (connected.get()) {
            connection.send(new LogoutRequestMessage());
        }
    }

    public void sendLogsCheckRequest(String login) {
        if (connected.get()) {
            connection.send(new LogsCheckRequestMessage(login));
        }
    }

    public void sendEventsCheckRequest() {
        if (connected.get()) {
            connection.send(new EventsCheckRequestMessage());
        }

    }

    public void sendHistoryCheckRequest(String idClient){
        if(connected.get()){
            connection.send(new HistoryCheckRequestMessage(idClient));
        }

    }

    public void sendChangeTicketStatusRequest(String idEvent){
        if(connected.get()){
            connection.send(new ChangeTicketStatusRequestMessage(idEvent));
        }
    }
    public void sendRepertoireCheckRequest() {
        if (connected.get()) {
            connection.send(new RepertoireCheckRequestMessage());
        }
    }

    public void sendChangeUserDataRequest(ChangeUserDataRequestMessage changeMessage) {
        connection.send(changeMessage);
    }

    public void sendEditEventsRequest(String idEvent, String title, String duration, String ageRestriction, String language, String releaseDate, String type, String imagePath) {
        if (connected.get()) {
            connection.send(new EventsEditRequestMessage(idEvent, title, duration, ageRestriction, language, releaseDate, type, imagePath));
        }
    }
    public void sendEditRepertoireRequest(String title, String date, String time, String idEventType, String idRoom,String idEvent) {
        if (connected.get()) {
            connection.send(new RepertoireEditRequestMessage(title,time,date,idEventType,idRoom,idEvent));
        }
    }

    public void sendEventSeatsRequest(int idEvent) {
        if (connected.get()) connection.send(new EventSeatsMessage(idEvent));
    }


    public void sendReview(ReviewMessage reviewMessage) {
        connection.send(reviewMessage);
    }

    public void sendImageEventTypeRequestDirectly(ImageEventTypeMessage message) {
        if (connected.get()) {
            try {
                imageRequestSemaphore.acquire();
                connection.send(message);
                System.out.println("sent id=" + message.getIdEventType());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendGetInfos(){
        if(connected.get()){
            connection.send(new GetInfosRequestMessage());
        }
    }

    public void sendReservationRequest(ReservationRequestMessage message) {
        if (connected.get()) connection.send(message);
    }


    public void sendImageEventTypeRequest(int idEventType) {
        imageRequestQueue.add(new ImageEventTypeMessage(idEventType));
        System.out.println("sub id=" + idEventType);
    }

    public String getNameOfEvent(int id){
        for( ArrayList<String> x : idAndNameOfEvents){
            if(Integer.valueOf(x.get(0))==id) return x.get(1);
        }
        return "Nie znaleziono";
    }
    public void addInfo(String info){
        if(connected.get()){
            connection.send(new AddInfoRequestMessage(info));
        }

    }

    public void disconnect() {
        if (connection != null) connection.close();
        if (receiveTask != null) receiveTask.cancel(true);
        executor.shutdown();
        executor.shutdownNow();
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        viewManager.connectionStateProperty().bind(connectionState);
    }


    public BooleanProperty loggedProperty() {
        return logged;
    }

    public ArrayList<ArrayList<String>> getIdAndNameOfEvents() {
        return idAndNameOfEvents;
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

    public int getUserID() {
        return userID;
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


    public ArrayList<ArrayList<String>> getInfos() {
        return infos;
    }

    public ArrayList<ArrayList<String>> getHistory() {
        return history;
    }
    public Connection getConnection() {
        return connection;
    }

    public String getRegex() {
        return regex;
    }

    public ArrayList<ArrayList<String>> getIdOfRooms() {
        return idOfRooms;
    }

    public String getRegexEmail() {
        return regexEmail;
    }

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public List<Integer> getSeatsID() {
        return seatsID;
    }


    public boolean isLoggedAsEmployee() {
        return loggedAsEmployee.get();
    }

    public BooleanProperty loggedAsEmployeeProperty() {
        return loggedAsEmployee;
    }


}
