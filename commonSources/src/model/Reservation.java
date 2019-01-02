package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 6493130192402686295L;
    private int idReservation;
    private int idClient;
    private ReservationStatus reservationStatus;
    private LocalDateTime creationDateTime;

    public Reservation(int idReservation, int idClient, ReservationStatus reservationStatus, LocalDateTime creationDateTime) {
        this.idReservation = idReservation;
        this.idClient = idClient;
        this.reservationStatus = reservationStatus;
        this.creationDateTime = creationDateTime;
    }
}

enum ReservationStatus {
    ACCEPTED,
    WAITING,
    CANCELED
}
