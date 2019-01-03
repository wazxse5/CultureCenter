package message;

public class ChangeUserDataAnswerMessage extends Message {
    private static final long serialVersionUID = -3877897214195644276L;
    private boolean nameChanged;
    private boolean surnameChanged;
    private boolean mailChanged;
    private boolean passwordChanged;

    public ChangeUserDataAnswerMessage(boolean nameChanged, boolean surnameChanged, boolean mailChanged, boolean passwordChanged) {
        this.nameChanged = nameChanged;
        this.surnameChanged = surnameChanged;
        this.mailChanged = mailChanged;
        this.passwordChanged = passwordChanged;
    }

    public boolean isNameChanged() {
        return nameChanged;
    }

    public boolean isSurnameChanged() {
        return surnameChanged;
    }

    public boolean isMailChanged() {
        return mailChanged;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public boolean isAnythingChanged() {
        return nameChanged || surnameChanged || mailChanged || passwordChanged;
    }
}
