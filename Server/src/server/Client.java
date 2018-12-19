package server;

public class Client {
    private String name;
    private String surname;
    private String mail;
    private String login;

    public Client(String name, String surname, String mail, String login) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
