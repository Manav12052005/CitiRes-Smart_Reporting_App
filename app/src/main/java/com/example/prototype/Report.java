package com.example.prototype;


public class Report {
    private String title;
    private String description;
    private String location;
    private Priority priority;
    private User user;


    public Report(String title, String description, String location) {
        this.title = title;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(title.toUpperCase()).append("\n").append(description).append("\n").append(location);
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
