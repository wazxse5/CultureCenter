package message;

public class LoginAnswerMessage extends Message {
    private static final long serialVersionUID = 6846450951913670969L;
    private final boolean good;
    private final int infoCode;

    private int userID;
    private String userLogin;
    private String userName;
    private String userSurName;
    private String userMail;
    private boolean employee;

    public LoginAnswerMessage(boolean good) {
        this.good = good;
        this.infoCode = 0;
    }

    public LoginAnswerMessage(boolean good, int infoCode) {
        this.good = good;
        this.infoCode = infoCode;
    }

    public LoginAnswerMessage(boolean good, int userID, String userLogin, String userName, String userSurName, String userMail, boolean employee) {
        this.good = good;
        this.infoCode = 0;
        this.userID = userID;
        this.userLogin = userLogin;
        this.userName = userName;
        this.userSurName = userSurName;
        this.userMail = userMail;
        this.employee = employee;
    }

    public boolean isGood() {
        return good;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public String getUserMail() {
        return userMail;
    }

    public boolean isEmployee() {
        return employee;
    }
}
