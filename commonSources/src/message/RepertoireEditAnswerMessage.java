package message;

public class RepertoireEditAnswerMessage extends Message {
    private static final long serialVersionUID = 577111173545981704L;

    public String getOk() {
        return ok;
    }

    private String ok;

    public RepertoireEditAnswerMessage(String ok){
        this.ok=ok;
    }


}
