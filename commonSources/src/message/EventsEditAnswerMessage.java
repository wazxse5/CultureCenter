package message;

import java.util.ArrayList;

public class EventsEditAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173545981704L;

    public String getOk() {
        return ok;
    }

    private String ok;

    public EventsEditAnswerMessage(String ok){
        this.ok=ok;
    }


}
