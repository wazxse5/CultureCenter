package message;

import java.util.ArrayList;

public class EventsEditAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173545981704L;
    private boolean ok;

    public EventsEditAnswerMessage(boolean ok){
        this.ok=ok;
    }


}
