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
    private SeatStatus seatStatus;

    public Seat(int idSeat, int row, int number, String type, int idRoom, int idTicket, SeatStatus seatStatus) {
        this.idSeat = idSeat;
        this.row = row;
        this.number = number;
        this.type = type;
        this.idRoom = idRoom;
        this.idTicket = idTicket;
        this.seatStatus = seatStatus;
    }
}

enum SeatStatus {
    FREE,
    TAKEN
}
