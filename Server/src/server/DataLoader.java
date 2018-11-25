package server;

import exception.AuthenticationException;
import exception.NameIsInUseException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<Client> knownClients;

    public DataLoader() {
        knownClients = new ArrayList<>();
        loadClients();
    }

    public void loadClients() {
        knownClients.add(new Client("test"));
        knownClients.add(new Client("wazxse5"));
        knownClients.add(new Client("admin"));
    }

    public synchronized Client register(String name, String password) throws AuthenticationException {
        for (Client c : knownClients) {
            if (c.getName().equals(name)) {
                throw new NameIsInUseException();
            }
        }
        Client client = new Client(name);
        knownClients.add(client);
        return client;
    }

    public synchronized Client login(String name, String password) throws AuthenticationException {
        for (Client client : knownClients) {
            if (client.getName().equals(name)) {
                // TODO: Dodać sprawdzanie czy klient nie został już zaologowany
                if (checkPassword(client, password)) {
                    return client;
                } else throw new WrongPasswordException();
            }
        }
        throw new NoSuchUserException();
    }


    private boolean checkPassword(Client client, String password) {
        // TODO: sprawdzanie hasła z pliku
        if (client.getName().equals("wazxse5") && password.equals("1234")) return true;
        else if (client.getName().equals("test") && password.equals("0000")) return true;
        else if (client.getName().equals("admin") && password.equals("admin")) return true;
        else return false;
    }


}
