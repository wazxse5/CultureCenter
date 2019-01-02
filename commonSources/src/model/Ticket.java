package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    private static final long serialVersionUID = -1611791304057516103L;
    private int idTicket;
    private int price;
    // int bo łatwiej będzie przechowywać ceny pomnożone przez 100,
    // nie pojawią się nieścisłości
    private LocalDateTime boughtDateTime;
    private TicketType ticketType;
    private int idReservation;
    private int idEvent;
    private int idSeat;

    public Ticket(int idTicket, int price, LocalDateTime boughtDateTime, TicketType ticketType, int idReservation, int idEvent, int idSeat) {
        this.idTicket = idTicket;
        this.price = price;
        this.boughtDateTime = boughtDateTime;
        this.ticketType = ticketType;
        this.idReservation = idReservation;
        this.idEvent = idEvent;
        this.idSeat = idSeat;
    }
}

enum TicketType {
    REGULAR,
    STUDENT,
    REDUCED
}
