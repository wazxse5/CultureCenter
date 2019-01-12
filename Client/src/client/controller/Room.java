package client.controller;


public class Room {
    private String id;
    private String number;
    private String maxSeats;
    private String numOfRows;
    private String branchID;


    public Room(String id, String number,String maxSeats, String numOfRows, String branchID) {
        this.id = id;
        this.number=number;
        this.maxSeats=maxSeats;
        this.numOfRows=numOfRows;
        this.branchID=branchID;
    }


    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getMaxSeats() {
        return maxSeats;
    }

    public String getNumOfRows() {
        return numOfRows;
    }

    public String getBranchID() {
        return branchID;
    }


}

