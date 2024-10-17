package com.example.prototype.login;

/**
 * @AUTHOR Saheb Yuvraj Singh u7781798
 * The UserSession class stores and manages the current users session.
 * It allows setting and retrieving the username of the logged-in user.
 */
public class UserSession {
    private static String currentUser;

    public static void setCurrentUser(String username) {
        currentUser = username;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
