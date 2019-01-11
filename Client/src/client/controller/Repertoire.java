package client.controller;

public class Repertoire {

    private String idEvent;
    private String name;
    private String date;
    private String startTime;
    private String idEventType;

    public Repertoire(String idevent, String name,String date, String startTime,String idEventType){
            this.idEvent=idevent;

            this.name=name;
            this.date=date;
            this.startTime=startTime;
            this.idEventType=idEventType;

        }

    public void setId(String id) {
        this.idEvent = id;
    }



    public String getId() {
        return idEvent;
    }



    public String getIdEvent() {
        return idEvent;
    }
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getIdEventType() {
        return idEventType;
    }
}
