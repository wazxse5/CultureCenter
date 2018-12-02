package server;

import exception.AuthenticationException;
import exception.NameIsInUseException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<Client> knownClients;
    private DBConnect dbConnect;

    public DataLoader() {
        knownClients = new ArrayList<>();
        dbConnect = new DBConnect();
        loadClients();
    }

    public List<Client> getKnownClients() {
        return knownClients;
    }

    public void loadClients() {
        knownClients.add(new Client("test", "0000", "o@.pl"));
        knownClients.add(new Client("wazxse5", "1234", "adasd@o2.pl"));
        knownClients.add(new Client("admin", "admin", "cos@o2.pl"));
    }


    public synchronized int register(String login, String password, String mail) throws SQLException {
        int result = dbConnect.addClient("name", "surname", mail, login, password);
        return result;
    }

    public synchronized Client login(String name, String password) throws AuthenticationException, SQLException {
        int result = dbConnect.loginUser(name, password);
        if (result == 0) {
            return new Client(name);
        }
        if (result == 2) throw new NoSuchUserException();
        if (result == 3) throw new WrongPasswordException();
        return null;
    }


}
