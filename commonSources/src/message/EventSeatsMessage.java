package message;

public class EventSeatsMessage extends Message {
    private static final long serialVersionUID = -4150649363609504119L;
    private final int idEvent;

    public EventSeatsMessage(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }
}
