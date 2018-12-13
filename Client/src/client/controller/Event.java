package client.controller;

public class Event {

    private String id;
    private String name;
    private String date;
    private String startTime;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;

    public Event(String id, String name, String date, String startTime,String title,String duration, String ageRestriction, String language, String releaseDate){
            this.id=id;
            this.name=name;
            this.date=date;
            this.startTime=startTime;
            this.title=title;
            this.duration=duration;
            this.ageRestriction=ageRestriction;
            this.language=language;
            this.releaseDate=releaseDate;
        }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getId() {
        return id;
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
}
