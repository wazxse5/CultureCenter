package message;


import java.util.ArrayList;

public class GetIdOfRoomsAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;
    private ArrayList<ArrayList<String>> result;

    public ArrayList getResult(){
        return result;
    }

    public GetIdOfRoomsAnswerMessage(ArrayList result){
        this.result=result;
    }


}
