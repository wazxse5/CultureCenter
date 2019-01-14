package message;

public class HistoryCheckRequestMessage extends Message {
    private static final long serialVersionUID = -7066000712345334003L;



    private String idClient;


    public HistoryCheckRequestMessage(String idClient) {
        this.idClient=idClient;

    }


    public String getIdClient() {
        return idClient;
    }



}
