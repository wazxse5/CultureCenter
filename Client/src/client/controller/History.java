package client.controller;


public class History {
    private String idTicket;
    private String price;
    private String boughtDate;
    private String type;
    private String condition;
    private String idEvent;
    private String idSeat;
    private String idClient;
    private String name;


    public History(String idTicket, String  price,String boughtDate, String type, String condition, String idEvent, String idSeat, String idClient,String name) {
        this.idTicket = idTicket;
        this.price=price;
        this.boughtDate=boughtDate;
        this.type=type;
        this.condition=condition;
        this.idEvent=idEvent;
        this.idSeat=idSeat;
        this.idClient=idClient;
        this.name=name;

    }

    public String getIdTicket() {
        return idTicket;
    }

    public String getPrice() {
        return price;
    }

    public String getBoughtDate() {
        return boughtDate;
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getCondition() {
        return condition;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdSeat() {
        return idSeat;
    }

    public String getIdClient() {
        return idClient;
    }

}

