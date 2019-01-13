package server;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String mail;
    private String login;

    public Client(int id, String name, String surname, String mail, String login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }
}
