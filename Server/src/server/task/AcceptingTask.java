package server.task;

import javafx.concurrent.Task;
import server.Connection;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AcceptingTask extends Task<Connection> {
    private final ServerSocket serverSocket;
    private final ExecutorService executor;


    public AcceptingTask(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.executor = Executors.newCachedThreadPool();
    }

    @Override protected Connection call() throws Exception {
        serverSocket.setSoTimeout(100);
        while (!isCancelled()) {
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                updateValue(connection);
            } catch (SocketTimeoutException ignored) {
            }
        }

        executor.shutdownNow();
        serverSocket.close();
        executor.shutdown();
        return null;
    }
}
