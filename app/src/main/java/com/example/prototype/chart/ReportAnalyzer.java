package com.example.prototype.chart;

import com.example.prototype.entity.Category;
import com.example.prototype.entity.Report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

/**
 * This class contains methods that retrieve data from the tree and pass it on as intent to the chart activities.
 * It utilises data from the DataHolder class which holds the updated tree.
 * It makes 3 maps, one for each chart.
 * Part of Feature - Data_Graphical
 * These are tested in ReportAnalyzerTest.java
 * @author Manav Singh*/

public class ReportAnalyzer {

    public Map<String, Integer> getPriorityCounts(List<Report> reports) {
        Map<String, Integer> priorityCounts = new HashMap<>();
        priorityCounts.put("LOW", 0);
        priorityCounts.put("MIDDLE", 0);
        priorityCounts.put("HIGH", 0);

        for (Report report : reports) {
            String priority = report.getPriority().toString();
            int count = priorityCounts.getOrDefault(priority, 0);
            priorityCounts.put(priority, count + 1);
        }

        return priorityCounts;
    }

    public Map<String, Integer> getCategoryCounts(List<Report> reports) {
        Map<String, Integer> categoryCounts = new HashMap<>();
        for (Category category : Category.values()) {
            categoryCounts.put(category.toString(), 0);
        }

        for (Report report : reports) {
            String category = report.getCategory().toString();
            categoryCounts.put(category, categoryCounts.get(category) + 1);
        }
        return categoryCounts;
    }

    public Map<String, Integer> getLocationCounts(List<Report> reports) {
        Map<String, Integer> locationCounts = new HashMap<>();
        HashSet<String> uniqueLocations = new HashSet<>();

        for (Report report : reports) {
            String location = report.getLocation();
            if (location != null && !location.isEmpty()) {
                uniqueLocations.add(location);
            }
        }

        for (String location : uniqueLocations) {
            locationCounts.put(location, 0);
        }

        for (Report report : reports) {
            String location = report.getLocation();
            if (location != null && locationCounts.containsKey(location)) {
                locationCounts.put(location, locationCounts.get(location) + 1);
            }
        }

        return locationCounts;
    }
}
