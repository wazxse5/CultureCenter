package message;

public class ReviewMessage extends Message {
    private static final long serialVersionUID = -7708327615613562757L;
    private int idEvent;
    private int idUser;
    private int grade;
    private String opinion;

    public ReviewMessage(int idEvent, int idUser, int grade, String opinion) {
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.grade = grade;
        this.opinion = opinion;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getGrade() {
        return grade;
    }

    public String getOpinion() {
        return opinion;
    }
}
