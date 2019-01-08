package message;

public class AddRepertuarRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;

    private String name;
    private String date;
    private String startTime;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
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

    public AddRepertuarRequestMessage(String name, String date, String startTime, String title, String duration, String ageRestriction, String language, String releaseDate, String type) {
        this.name=name;
        this.date=date;
        this.startTime=startTime;
        this.title=title;
        this.duration=duration;
        this.ageRestriction=ageRestriction;
        this.language=language;
        this.releaseDate=releaseDate;
        this.type=type;
    }




}
