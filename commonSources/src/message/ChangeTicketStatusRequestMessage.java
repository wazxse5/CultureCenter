package message;


public class ChangeTicketStatusRequestMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;


    private String idEvent;

    public ChangeTicketStatusRequestMessage(String email){
        this.idEvent=email;
    }

    public String getIdEvent() {
        return idEvent;
    }

}
