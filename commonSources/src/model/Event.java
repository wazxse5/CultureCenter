package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private static final long serialVersionUID = 1583826649024172502L;
    private int idEvent;
    private String name;
    private LocalDateTime dateTime;
    private int eventTypeId;

    public Event(int idEvent, String name, LocalDateTime dateTime, int eventTypeId) {
        this.idEvent = idEvent;
        this.name = name;
        this.dateTime = dateTime;
        this.eventTypeId = eventTypeId;
    }
}
