package message;

public class RegisterRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;
    private final String name;
    private final String surname;
    private final String login;
    private final String password;
    private final String mail;

    public RegisterRequestMessage(String name, String surname, String login, String password, String mail) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
