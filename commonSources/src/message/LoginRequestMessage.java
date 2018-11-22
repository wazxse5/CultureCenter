package message;

public class LoginRequestMessage extends Message {
    private static final long serialVersionUID = -8511670995169064933L;
    private final String name;
    private final String password;
    private final boolean guest;

    public LoginRequestMessage(String name, String password) {
        this.name = name;
        this.password = password;
        this.guest = false;
    }

    public LoginRequestMessage(String name) {
        this.name = name;
        this.password = null;
        this.guest = true;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGuest() {
        return guest;
    }
}
