package message;

public class RegisterAnswerMessage extends Message {
    private static final long serialVersionUID = 577625073596981704L;
    private final boolean ok;
    private final int infoCode;

    public RegisterAnswerMessage(boolean ok) {
        this.ok = ok;
        this.infoCode = 0;
    }

    public RegisterAnswerMessage(boolean ok, int infoCode) {
        this.ok = ok;
        this.infoCode = infoCode;
    }

    public boolean isGood() {
        return ok;
    }

    public int getInfoCode() {
        return infoCode;
    }
}
