package model;

import java.io.Serializable;

public class Seat implements Serializable {
    private static final long serialVersionUID = -9090666279176811531L;
    private int idSeat;
    private int row;
    private int number;
    private String type;
    private int idRoom;
    private int idTicket;
    private int idEvent;
    private int seatStatus;

    public Seat(int idSeat, int row, int number, String type, int idRoom, int idTicket, int seatStatus) {
        this.idSeat = idSeat;
        this.row = row;
        this.number = number;
        this.type = type;
        this.idRoom = idRoom;
        this.idTicket = idTicket;
        this.seatStatus = seatStatus;
    }


    public int getIdSeat() {
        return idSeat;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getSeatStatus() {
        return seatStatus;
    }
}
