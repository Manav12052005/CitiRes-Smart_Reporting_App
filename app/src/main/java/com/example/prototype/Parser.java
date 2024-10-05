package com.example.prototype;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Report> parse(List<String> tokens, List<Report> reports) {
        List<Report> results = new ArrayList<>(reports);

        // Iterate through tokens and filter based on grammar
        for (String token : tokens) {
            if (token.contains(":")) {
                // Split the grammar element into key and value
                String[] keyValue = token.split(":", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    // Filter based on the key (attribute)
                    results = filterByKeyValue(key, value, results);
                }
            } else {
                // If it's not a key-value pair, it's a general search term
                results = filterByGeneralToken(token, results);
            }
        }

        return results;
    }

    private static List<Report> filterByKeyValue(String key, String value, List<Report> reports) {
        List<Report> filteredReports = new ArrayList<>();

        for (Report report : reports) {
            switch (key) {
                case "description":
                    if (report.getDescription().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                    break;
                case "location":
                    if (report.getLocation().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                    break;
                case "priority":
                    if (report.getPriority().toString().toLowerCase().equals(value)) {
                        filteredReports.add(report);
                    }
                    break;
                case "category":
                    if (report.getCategory().toString().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                    break;
                case "user":
                    if (report.getUser().getName().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                    break;
                case "likes":
                    try {
                        int likesValue = Integer.parseInt(value);
                        if (report.getLikes() == likesValue) {
                            filteredReports.add(report);
                        }
                    } catch (NumberFormatException e) {
                        Log.d("Parser", "Invalid number for likes: " + value);
                    }
                    break;
                default:
                    Log.d("Parser", "Unknown key: " + key);
                    break;
            }
        }

        return filteredReports;
    }

    private static List<Report> filterByGeneralToken(String token, List<Report> reports) {
        List<Report> filteredReports = new ArrayList<>();

        for (Report report : reports) {
            boolean tokenMatches =
                    report.getDescription().toLowerCase().contains(token) ||
                            report.getLocation().toLowerCase().contains(token) ||
                            report.getCategory().toString().toLowerCase().contains(token) ||
                            report.getPriority().toString().toLowerCase().contains(token) ||
                            report.getUser().getName().toLowerCase().contains(token) ||
                            report.getLocalDateTime().toString().toLowerCase().contains(token) ||
                            String.valueOf(report.getLikes()).contains(token);

            if (tokenMatches) {
                filteredReports.add(report);
            }
        }

        return filteredReports;
    }
}
