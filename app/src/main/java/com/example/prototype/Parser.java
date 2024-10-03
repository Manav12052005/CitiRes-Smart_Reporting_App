package com.example.prototype;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Report> parse(List<String> tokens, List<Report> reports) {
        List<Report> results = new ArrayList<>();
        for (Report report : reports) {
            boolean matches = true;
            for (String token : tokens) {
                if (!report.getDescription().toLowerCase().contains(token) &&
                        !report.getLocation().toLowerCase().contains(token) &&
                        !report.getCategory().toString().toLowerCase().contains(token)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                results.add(report);
            }
        }
        return results;
    }
}

