package com.example.prototype;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
    private final String name;
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
