package com.example.prototype;

import androidx.annotation.NonNull;

public class User {
    private String name;
    private String password;

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
}
