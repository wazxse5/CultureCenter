package message;

import java.util.ArrayList;

public class FirstInfoMessage extends Message {
    private static final long serialVersionUID = -6523241810590497636L;
    private ArrayList<Integer> recommended;

    public FirstInfoMessage(ArrayList<Integer> recommended) {
        this.recommended = recommended;
    }

    public ArrayList<Integer> getRecommended() {
        return recommended;
    }
}
