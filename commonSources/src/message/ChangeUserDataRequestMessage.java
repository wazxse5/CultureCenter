package message;

public class ChangeUserDataRequestMessage extends Message {
    private static final long serialVersionUID = 4320535785882153612L;

    private boolean changeName;
    private boolean changeSurname;
    private boolean changeMail;
    private boolean changePassword;

    private String newName;
    private String newSurname;
    private String newMail;
    private String currentPassword;
    private String newPassword;

    public ChangeUserDataRequestMessage(boolean changeName, boolean changeSurname, boolean changeMail, String newName, String newSurname, String newMail) {
        this.changeName = changeName;
        this.changeSurname = changeSurname;
        this.changeMail = changeMail;
        this.newName = newName;
        this.newSurname = newSurname;
        this.newMail = newMail;
    }

    public ChangeUserDataRequestMessage(boolean changePassword, String currentPassword, String newPassword) {
        this.changePassword = changePassword;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public boolean isChangeName() {
        return changeName;
    }

    public boolean isChangeSurname() {
        return changeSurname;
    }

    public boolean isChangeMail() {
        return changeMail;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewSurname() {
        return newSurname;
    }

    public String getNewMail() {
        return newMail;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
