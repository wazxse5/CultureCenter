package model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventType implements Serializable {
    private static final long serialVersionUID = -3402765646119138016L;
    private int idEventType;
    private String title;
    private String type;
    private LocalTime duration;
    private int ageRestriction;
    private String language;
    private LocalDate releaseDate;
    private Image image;

    public EventType(int idEventType, String title, String type, LocalTime duration, int ageRestriction, String language, LocalDate releaseDate, Image image) {
        this.idEventType = idEventType;
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.ageRestriction = ageRestriction;
        this.language = language;
        this.releaseDate = releaseDate;
        this.image = image;
    }
}
