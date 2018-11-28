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
        knownClients.add(new Client("test","0000","o@.pl"));
        knownClients.add(new Client("wazxse5","1234","adasd@o2.pl"));
        knownClients.add(new Client("admin","admin","cos@o2.pl"));
    }

    public void addClient(String name,String password, String email){
        knownClients.add(new Client (name, password, email));
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
                if (checkPassword(client, password, name)) {
                    return client;
                } else throw new WrongPasswordException();
            }
        }
        throw new NoSuchUserException();
    }


    private boolean checkPassword(Client client, String password, String login) {
        // TODO: sprawdzanie hasła z pliku
        if (client.getName().equals(login) && password.equals(client.getPassword())) return true;

        else return false;
    }


}
