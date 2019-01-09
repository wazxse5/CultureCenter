package message;

public class AddRepertuarRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;

    private String imagePath;



    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    public AddRepertuarRequestMessage(String imagePath, String title, String duration, String ageRestriction, String language, String releaseDate, String type) {
        this.imagePath=imagePath;
        this.title=title;
        this.duration=duration;
        this.ageRestriction=ageRestriction;
        this.language=language;
        this.releaseDate=releaseDate;
        this.type=type;
    }




}
