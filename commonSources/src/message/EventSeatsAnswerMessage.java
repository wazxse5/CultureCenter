package message;

import java.util.List;

public class EventSeatsAnswerMessage extends Message {
    private static final long serialVersionUID = 7575333486878111987L;
    private List<Integer> reserved;

    public EventSeatsAnswerMessage(List<Integer> reserved) {
        this.reserved = reserved;
    }

    public List<Integer> getReserved() {
        return reserved;
    }
}
