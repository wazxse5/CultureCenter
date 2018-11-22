package client.task;

import exception.NameIsInUseException;
import exception.NoSuchUserException;
import exception.WrongPasswordException;
import message.LoginAnswerMessage;
import message.LoginRequestMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;

public class LoginTask implements Callable<Boolean> {

    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final String name;
    private final String password;

    public LoginTask(ObjectInputStream input, ObjectOutputStream output, String name, String password) {
        this.input = input;
        this.output = output;
        this.name = name;
        this.password = password;
    }

    @Override public Boolean call() throws Exception {
        output.writeObject(new LoginRequestMessage(name, password));

        while (true) {
            Object answerObject = input.readObject();
            if (answerObject instanceof LoginAnswerMessage) {
                LoginAnswerMessage loginAnswer = (LoginAnswerMessage) answerObject;
                if (loginAnswer.isGood()) return true;
                else {
                    if (loginAnswer.getInfoCode() == 1) throw new NameIsInUseException();
                    if (loginAnswer.getInfoCode() == 2) throw new NoSuchUserException();
                    if (loginAnswer.getInfoCode() == 3) throw new WrongPasswordException();
                    else return false;
                }
            }

            if (Thread.interrupted()) return false;
        }
    }
}
