package client.controller;

public class Repertoire {

    private String idEvent;
    private String name;
    private String date;
    private String startTime;
    private String idEventType;
    private String idRoom;
    private String maxSeats;
    private String numOfRows;

    public Repertoire(String idevent, String name,String date, String startTime,String idEventType,String idRoom,String maxSeats, String numOfRows){
            this.idEvent=idevent;
            this.name=name;
            this.date=date;
            this.startTime=startTime;
            this.idEventType=idEventType;
            this.idRoom=idRoom;
            this.maxSeats=maxSeats;
            this.numOfRows=numOfRows;

        }

    public void setId(String id) {
        this.idEvent = id;
    }



    public String getId() {
        return idEvent;
    }


    public String getIdRoom() {
        return idRoom;
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

    public String getMaxSeats() {
        return maxSeats;
    }

    public String getNumOfRows() {
        return numOfRows;
    }
}
