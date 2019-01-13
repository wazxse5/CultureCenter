package message;

import model.Seat;

import java.util.List;

public class EventSeatsMessage extends Message {
    private static final long serialVersionUID = -4150649363609504119L;
    private final int idEvent;
    private List<Seat> seats;

    public EventSeatsMessage(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
