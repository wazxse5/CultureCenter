package client.controller;

public class Repertoire {

    private String idEvent;
    private String idSchedule;
    private String status;
    private String startDate;
    private String endDate;
    private String creationTime;
    private String idBranch;
    private String name;
    private String date;
    private String startTime;
    private String idEventType;

    public Repertoire(String idevent,String id, String status, String startDate, String endDate, String creationTime, String idBranch, String name,String date, String startTime,String idEventType){
            this.idEvent=idevent;
            this.idSchedule=id;
            this.status=status;
            this.startDate=startDate;
            this.endDate=endDate;
            this.creationTime=creationTime;
            this.idBranch=idBranch;
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

    public String getIdSchedule() {
        return idSchedule;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getIdBranch() {
        return idBranch;
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
