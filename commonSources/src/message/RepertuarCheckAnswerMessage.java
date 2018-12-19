package message;

import java.util.ArrayList;

public class RepertuarCheckAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;
    private ArrayList<ArrayList<String>> result;

    public ArrayList getResult(){
        return result;
    }

    public RepertuarCheckAnswerMessage(ArrayList result){
        this.result = result;

    }


}