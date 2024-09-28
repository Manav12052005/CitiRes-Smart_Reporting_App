package com.example.prototype;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Report {
    private String description;
    private String location;
    private Priority priority;
    private User user;
    private Category category;
    private LocalDateTime localDateTime;

    public Report(String description, String location, Priority priority, User user, Category category) {
        this.description = description;
        this.location = location;
        this.priority = priority;
        this.user = user;
        this.category = category;
    }

    public Report(String description, String location, Priority priority, User user, Category category, LocalDateTime localDateTime) {
        this.description = description;
        this.location = location;
        this.priority = priority;
        this.user = user;
        this.category = category;
        this.localDateTime = localDateTime;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
