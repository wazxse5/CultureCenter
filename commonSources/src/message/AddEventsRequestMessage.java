package message;

public class AddEventsRequestMessage extends Message {
    private static final long serialVersionUID = -7066000756945334003L;

    private byte[] image;
    private String imagePath;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;

    public AddEventsRequestMessage(byte[] image, String imagePath, String title, String duration, String ageRestriction, String language, String releaseDate, String type) {
        this.image = image;
        this.imagePath = imagePath;
        this.title = title;
        this.duration = duration;
        this.ageRestriction = ageRestriction;
        this.language = language;
        this.releaseDate = releaseDate;
        this.type = type;
    }

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

    public byte[] getImage() {
        return image;
    }

    public String getImageExtension() {
        String extension = "";
        int i = imagePath.lastIndexOf('.');
        if (i > 0) extension = imagePath.substring(i+1);
        return extension;
    }
}
