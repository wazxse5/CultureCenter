package client.controller;

public class Event {

    private String idEventType;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;
    private String imagePath;

    public String getIdEventType() {
        return idEventType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Event(String idEventType, String title, String type, String duration, String ageRestriction, String language, String releaseDate, String imagePath){
            this.imagePath=imagePath;
            this.idEventType= idEventType;
            this.title=title;
            this.duration=duration;
            this.ageRestriction=ageRestriction;
            this.language=language;
            this.releaseDate=releaseDate;
            this.type=type;

        }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setType(String type){this.type = type;}

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

    public String getType(){return type;}
    public String getReleaseDate() {
        return releaseDate;
    }
}
