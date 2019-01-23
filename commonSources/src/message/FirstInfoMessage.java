package message;

import java.util.List;

public class FirstInfoMessage extends Message {
    private static final long serialVersionUID = -6523241810590497636L;
    private List<Integer> recommended;

    public FirstInfoMessage(List<Integer> recommended) {
        this.recommended = recommended;
    }

    public List<Integer> getRecommended() {
        return recommended;
    }
}
