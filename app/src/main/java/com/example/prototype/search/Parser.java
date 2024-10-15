package com.example.prototype.search;

import android.util.Log;

import com.example.prototype.entity.Report;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Report> parseWithGrammar(List<String> tokens, List<Report> reports) {
        List<Report> results = new ArrayList<>(reports);

        // Separate key-value pairs from general tokens
        List<String> keyValueTokens = new ArrayList<>();
        List<String> generalTokens = new ArrayList<>();

        for (String token : tokens) {
            if (token.contains(":")) {
                keyValueTokens.add(token);
            } else {
                generalTokens.add(token);
            }
        }

        // Apply key-value pair filters first
        for (String keyValueToken : keyValueTokens) {
            String[] keyValue = keyValueToken.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();


                results = filterByKeyValue(key, value, results);
            }
        }

        // Apply general tokens filter
        for (String token : generalTokens) {
            results = filterByGeneralToken(token, results);
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
                    if (report.getPriority().toString().toLowerCase().contains(value)) {
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
