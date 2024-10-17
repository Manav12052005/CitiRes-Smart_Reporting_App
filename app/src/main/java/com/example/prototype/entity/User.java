package com.example.prototype.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity for User
 * @author Yuan Shi u7787835
 */
public class User implements Serializable {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
