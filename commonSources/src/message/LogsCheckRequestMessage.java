package message;

public class LogsCheckRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;

    private final String login;


    public LogsCheckRequestMessage(String login) {

        this.login = login;

    }
    public LogsCheckRequestMessage() {
        this.login = "";
    }


    public String getLogin() {
        return login;
    }


}
