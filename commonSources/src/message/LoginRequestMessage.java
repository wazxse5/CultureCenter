package message;

public class LoginRequestMessage extends Message {
    private static final long serialVersionUID = -8511670995169064933L;
    private final String login;
    private final String password;
    private final boolean employee;

    public LoginRequestMessage(String login, String password, boolean employee) {
        this.login = login;
        this.password = password;
        this.employee = employee;
    }

    public LoginRequestMessage(String login) {
        this.login = login;
        this.password = null;
        this.employee = true;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmployee() {
        return employee;
    }

}
