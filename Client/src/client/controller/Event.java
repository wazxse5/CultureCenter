package client.controller;

public class Event {

    private String idEvent;
    private String idEventType;
    private String name;
    private String date;
    private String startTime;
    private String title;
    private String duration;
    private String ageRestriction;
    private String language;
    private String releaseDate;
    private String type;

    public Event(String idEventType, String id, String name, String date, String startTime,String title, String type,String duration, String ageRestriction, String language, String releaseDate){
            this.idEvent=id;
            this.idEventType= idEventType;
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

    public void setId(String id) {
        this.idEvent = id;
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
    public void setType(String type){this.type = type;}

    public String getId() {
        return idEvent;
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

    public String getType(){return type;}
    public String getReleaseDate() {
        return releaseDate;
    }
}
