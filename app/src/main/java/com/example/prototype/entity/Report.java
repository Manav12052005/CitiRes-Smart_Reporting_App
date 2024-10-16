package com.example.prototype.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuan Shi u7787385
 */
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
        this.likes = 0;
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
    public void like() {
        this.likes++;
    }

    // Method to unlike the report
    public void unlike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId == report.reportId && likes == report.likes && Objects.equals(description, report.description) && Objects.equals(pictureLink, report.pictureLink) && Objects.equals(location, report.location) && priority == report.priority && Objects.equals(user, report.user) && category == report.category && Objects.equals(localDateTime, report.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, description, pictureLink, location, priority, user, category, localDateTime, likes);
    }
}
