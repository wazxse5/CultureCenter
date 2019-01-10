package message;

public class EventsEditRequestMessage extends Message {
    private static final long serialVersionUID = -7066000712345334003L;

    private String idEvent;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;
    private String imagePath;

    public EventsEditRequestMessage(String idEvent,String title,String duration,String ageRestriction,String language,String releaseDate,String type,String imagePath) {
        this.idEvent=idEvent;
        this.title=title;
        this.duration=duration;
        this.ageRestriction=ageRestriction;
        this.language=language;
        this.releaseDate=releaseDate;
        this.type=type;
        this.imagePath=imagePath;
    }



    public String getIdEvent() {
        return idEvent;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }


}
