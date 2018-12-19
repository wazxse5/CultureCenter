package message;

public class LoginRequestMessage extends Message {
    private static final long serialVersionUID = -8511670995169064933L;
    private final String login;
    private final String password;
    private final boolean guest;

    public LoginRequestMessage(String login, String password) {
        this.login = login;
        this.password = password;
        this.guest = false;
    }

    public LoginRequestMessage(String login) {
        this.login = login;
        this.password = null;
        this.guest = true;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGuest() {
        return guest;
    }
}
