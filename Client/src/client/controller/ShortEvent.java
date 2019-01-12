package client.controller;

public class ShortEvent {

    private String idEventType;
    private String title;


    public ShortEvent(String idEventType, String title){
            this.idEventType= idEventType;
            this.title=title;
        }



    public void setTitle(String title) {
        this.title = title;
    }


    public String getIdEventType() {
        return idEventType;
    }


    public String getTitle() {
        return title;
    }


}
