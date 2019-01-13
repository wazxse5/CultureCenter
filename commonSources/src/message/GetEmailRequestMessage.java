package message;


public class GetEmailRequestMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;


    private String email;

    public GetEmailRequestMessage(String email){
        this.email=email;

    }

    public String getEmail() {
        return email;
    }

}
