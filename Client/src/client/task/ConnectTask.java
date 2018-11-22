package client.task;

import message.WelcomeAnswerMessage;
import message.WelcomeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;

public class ConnectTask implements Callable<Boolean> {
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public ConnectTask(ObjectInputStream input, ObjectOutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override public Boolean call() throws Exception {
        output.writeObject(new WelcomeMessage());

        while (true) {
            Object answerObject = input.readObject();
            if (answerObject instanceof WelcomeAnswerMessage) {
                return true;
            }

            if (Thread.interrupted()) return false;
        }
    }
}
