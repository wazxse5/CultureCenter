package message;

import javafx.scene.control.TableView;

public class LogsCheckAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;
    private TableView result;

    public TableView getResult(){
        return result;
    }

    public LogsCheckAnswerMessage(TableView result){
        this.result = result;

    }


}
