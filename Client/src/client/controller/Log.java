package client.controller;


public class Log {
    private String id;
    private String user;
    private String mail;
    private String date;
    private String type;
    private String addInfo;

    public Log(String id, String user, String mail, String date, String type, String addInfo) {
        this.id = id;
        this.user = user;
        this.mail = mail;
        this.date = date;
        this.type = type;
        this.addInfo = addInfo;
    }

    public String getMail() {
        return mail;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}

