package client.controller;

public class Repertoire {

    private String idSchedule;
    private String status;
    private String startDate;
    private String endDate;
    private String creationTime;
    private String idBranch;
    private String idEvent;

    public Repertoire(String id, String status, String startDate, String endDate, String creationTime, String idBranch, String idEvent ){
            this.idSchedule=id;
            this.status=status;
            this.startDate=startDate;
            this.endDate=endDate;
            this.creationTime=creationTime;
            this.idBranch=idBranch;
            this.idEvent=idEvent;
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
}
