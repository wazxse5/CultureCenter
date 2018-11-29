package message;

public class LoginCheckRequestMessage extends Message {
    private static final long serialVersionUID = 577625073596981704L;


    private String login;
    private boolean state;

    public void stateFalse(){
        state=false;
    }
    public void stateTrue(){
        state=true;

    }
    public LoginCheckRequestMessage(String login) {
        this.login = login;
        this.state = false;
    }

    public String getLogin() {
        return login;
    }




}
