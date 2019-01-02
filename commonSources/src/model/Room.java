package model;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = -8007040951508251883L;
    private int idRoom;
    private int number;
    private int maxSeats;
    private int numberOfRows;
    private int idBranch;

    public Room(int idRoom, int number, int maxSeats, int numberOfRows, int idBranch) {
        this.idRoom = idRoom;
        this.number = number;
        this.maxSeats = maxSeats;
        this.numberOfRows = numberOfRows;
        this.idBranch = idBranch;
    }
}
