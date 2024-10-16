package com.example.prototype.search;

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

        switch (key.toLowerCase()) {
            case "description":
                for (Report report : reports) {
                    if (report.getDescription().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                }
                break;
            case "location":
                for (Report report : reports) {
                    if (report.getLocation().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                }
                break;
            case "priority":
                for (Report report : reports) {
                    if (report.getPriority().toString().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                }
                break;
            case "category":
                for (Report report : reports) {
                    if (report.getCategory().toString().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                }
                break;
            case "user":
                for (Report report : reports) {
                    if (report.getUser().getName().toLowerCase().contains(value)) {
                        filteredReports.add(report);
                    }
                }
                break;
            case "likes":
                try {
                    int likesValue = Integer.parseInt(value);
                    for (Report report : reports) {
                        if (report.getLikes() == likesValue) {
                            filteredReports.add(report);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Return the unmodified reports list if likes value is invalid
                    return reports;
                }
                break;
            default:
                // Return the unmodified reports list if key is unknown
                return reports;
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

