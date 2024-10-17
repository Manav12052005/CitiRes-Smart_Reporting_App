package com.example.prototype.login;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Saheb Yuvraj Singh u7781798
 * The Authenticator class is responsible for handling user authentication using a singleton class
 * It loads credentials from JSON file in test mode.
 *
 *Key Features:
 * -Singleton pattern
 * -Using json file to inject credentials using username and password.
 *-Uses Credentials a helper class to represent a pair of usernmae and password
 *chatgpt has been used to make modifications to help with testing class as the testing class does
 * not access the credentials file.
 */


public class Authenticator {
    private static Authenticator instance;


    private List<Credential> credentials;


    private boolean isTestMode = false;


    private Authenticator() {
        credentials = new ArrayList<>();
    }


    public static synchronized Authenticator getInstance(Context context) {
        if (instance == null) {
            instance = new Authenticator();
            instance.loadJson(context); // Load credentials from JSON in production
        }
        return instance;
    }

    //Returns the singleton instance
    public static synchronized Authenticator getInstance(List<Credential> testCredentials) {
        if (instance == null || instance.isTestMode) {  // Allow resetting in test mode
            instance = new Authenticator();
            instance.isTestMode = true; // Set test mode to true
            instance.credentials = testCredentials; // Use test credentials
        }
        return instance;
    }

    //Resets the singleton instance
    public static synchronized void resetInstance() {
        instance = null;
    }

    // Method to handle login logic and verifies if the provided credentials match
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
            return; // Skip loading JSON during testing (chatgpt change)
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
