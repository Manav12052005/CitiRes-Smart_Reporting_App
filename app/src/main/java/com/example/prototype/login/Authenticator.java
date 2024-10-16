package com.example.prototype.login;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Authenticator {
    // Singleton instance
    private static Authenticator instance;

    // List to hold username and password pairs
    private List<Credential> credentials;

    // Private flag to disable loading logic for tests
    private boolean isTestMode = false;

    // Private constructor to prevent instantiation
    private Authenticator() {
        credentials = new ArrayList<>();
    }

    // Synchronized method to ensure thread-safety in production (with context)
    public static synchronized Authenticator getInstance(Context context) {
        if (instance == null) {
            instance = new Authenticator();
            instance.loadJson(context); // Load credentials from JSON in production
        }
        return instance;
    }

    // Synchronized method to allow injecting test credentials (for unit tests)
    public static synchronized Authenticator getInstance(List<Credential> testCredentials) {
        if (instance == null || instance.isTestMode) {  // Allow resetting in test mode
            instance = new Authenticator();
            instance.isTestMode = true; // Set test mode to true
            instance.credentials = testCredentials; // Use test credentials
        }
        return instance;
    }

    // Allow resetting instance for testing purposes
    public static synchronized void resetInstance() {
        instance = null;
    }

    // Method to handle login logic
    public boolean login(String username, String password) {
        for (Credential credential : credentials) {
            if (credential.getUsername().equals(username) && credential.getPassword().equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }

    // Method to load credentials from the JSON file in production
    private void loadJson(Context context) {
        if (isTestMode) {
            return; // Skip loading JSON during testing
        }

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("credentials.json"); // Ensure credentials.json is in the assets folder
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            // Clear the list before adding new credentials
            credentials.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String username = jsonObject.getString("username");
                String password = jsonObject.getString("password");

                // Add the credential pair to the list
                credentials.add(new Credential(username, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper class to store username and password pairs
    public static class Credential {
        private String username;
        private String password;

        public Credential(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
