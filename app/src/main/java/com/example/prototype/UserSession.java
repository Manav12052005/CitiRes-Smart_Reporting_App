package com.example.prototype;

public class UserSession {
    private static String currentUser;

    public static void setCurrentUser(String username) {
        currentUser = username;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
