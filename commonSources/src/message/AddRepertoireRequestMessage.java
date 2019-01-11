package message;

public class AddRepertoireRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;

    private String title;
    private String time;
    private String date;
    private String idEvent;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public AddRepertoireRequestMessage( String title, String date, String time, String idEvent) {
        this.title=title;
        this.time=time;
        this.date=date;
        this.idEvent=idEvent;
    }
    public String getTitle() {

        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getIdEvent() {
        return idEvent;
    }


}
