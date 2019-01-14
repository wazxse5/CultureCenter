package message;

public class ReviewMessage extends Message {
    private static final long serialVersionUID = -7708327615613562757L;
    private int idEvent;
    private int idUser;
    private int grade;
    private String opinion;



    private String type;

    public ReviewMessage(int idEvent, int idUser, int grade, String opinion,String type) {
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.grade = grade;
        this.opinion = opinion;
        this.type=type;
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
    public String getType() {
        return type;
    }

}
