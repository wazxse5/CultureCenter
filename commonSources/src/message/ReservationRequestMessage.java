package message;

import java.util.List;

public class ReservationRequestMessage extends Message {
    private static final long serialVersionUID = -4953720557900952639L;
    private int idEvent;
    private int idClient;
    private String price;
    private String type;
    private String condition;
    private List<Integer> seatNumbers;

    public ReservationRequestMessage(int idEvent, int idClient, String price, String type, String condition, List<Integer> seatNumbers) {
        this.idEvent = idEvent;
        this.idClient = idClient;
        this.price = price;
        this.type = type;
        this.condition = condition;
        this.seatNumbers = seatNumbers;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public List<Integer> getSeatNumbers() {
        return seatNumbers;
    }
}
