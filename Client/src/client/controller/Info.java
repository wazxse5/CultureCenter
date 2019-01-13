package client.controller;


public class Info {
    private String id;
    private String date;
    private String text;


    public Info(String id,String date, String text) {
        this.id = id;
        this.text=text;
        this.date = date;
    }


    public String getDate() {
        return date;
    }



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

}

