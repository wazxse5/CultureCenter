package server;

public class Client {
    private String name;
    private Connection connection;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
