package com.example.prototype;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable {
    private int reportId;//set to be primary key, auto increment.
    private String description;
    private String pictureLink;
    private String location;
    private Priority priority;
    private User user;
    private Category category;
    private LocalDateTime localDateTime;
    private int likes;

    public Report() {
    }

    public Report(int reportId, String description, String location, Priority priority, User user, Category category, LocalDateTime localDateTime, int likes) {
        this.reportId = reportId;
        this.description = description;
        this.location = location;
        this.priority = priority;
        this.user = user;
        this.category = category;
        this.localDateTime = localDateTime;
        this.likes = likes;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPictureLink(){return pictureLink;}

    public void setPictureLink(String newPictureLink) {this.pictureLink=newPictureLink;}

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getLikes() {
        return likes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
