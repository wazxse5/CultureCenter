package message;

public class RepertoireEditRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;
    private String title;
    private String time;
    private String date;
    private String idEventType;
    private String idEvent;



    private String idRoom;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public RepertoireEditRequestMessage(String title, String date, String time, String idEventType, String idRoom, String idEvent) {
        this.title=title;
        this.time=time;
        this.date=date;
        this.idEventType=idEventType;
        this.idRoom=idRoom;
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

    public String getIdEventType() {
        return idEventType;
    }
    public String getIdRoom() {
        return idRoom;
    }
    public String getIdEvent() {
        return idEvent;
    }

}
