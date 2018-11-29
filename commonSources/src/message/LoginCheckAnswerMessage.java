package message;

public class LoginCheckAnswerMessage extends Message {
    private static final long serialVersionUID = 577625073596981704L;
    private boolean state;

    public void stateFalse(){
        state=false;
    }
    public void stateTrue(){
        state=true;

    }
    public LoginCheckAnswerMessage(boolean state) {

        this.state = false;
    }






}
