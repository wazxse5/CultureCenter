package server;

public class Client {
    private String name;
    private String password;
    private String email;

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, String password, String email) {

        this.name = name;
        this.password = password;
        this.email = email;

    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

}
