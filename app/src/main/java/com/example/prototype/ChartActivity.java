package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

public class ChartActivity extends BaseActivity {

    private Button buttonPriorityChart;
    private Button buttonCategoryChart;
    private Button buttonLocationChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Ensure EdgeToEdge is correctly implemented
        setChildContentView(R.layout.activity_chart);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        buttonPriorityChart = findViewById(R.id.button_priority_chart);
        buttonCategoryChart = findViewById(R.id.button_category_chart);
        buttonLocationChart = findViewById(R.id.button_location_chart);

        // Set OnClickListeners
        buttonPriorityChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get priority counts
                Map<String, Integer> priorityCounts = getPriorityCounts();
                Intent intent = new Intent(ChartActivity.this, PriorityChartActivity.class);
                // Pass the counts via Intent extras
                intent.putExtra("LOW_COUNT", priorityCounts.get("LOW"));
                intent.putExtra("MIDDLE_COUNT", priorityCounts.get("MIDDLE"));
                intent.putExtra("HIGH_COUNT", priorityCounts.get("HIGH"));
                startActivity(intent);
            }
        });

        buttonCategoryChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get category counts
                Map<String, Integer> categoryCounts = getCategoryCounts();
                Intent intent = new Intent(ChartActivity.this, CategoryChartActivity.class);
                // Optionally, pass any required data via intent
                for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
                    String key = entry.getKey() + "_COUNT";
                    intent.putExtra(key, entry.getValue());
                }
                startActivity(intent);
            }
        });

        buttonLocationChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get location counts
                Map<String, Integer> locationCounts = getLocationCounts();

                // Create an Intent to start LocationChartActivity
                Intent intent = new Intent(ChartActivity.this, LocationChartActivity.class);

                // Pass the location counts map as a Serializable extra
                intent.putExtra("LOCATION_COUNTS", (Serializable) locationCounts);

                // Start the LocationChartActivity
                startActivity(intent);
            }
        });

    }
    private Map<String, Integer> getPriorityCounts() {
        Map<String, Integer> priorityCounts = new HashMap<>();
        priorityCounts.put("LOW", 0);
        priorityCounts.put("MIDDLE", 0);
        priorityCounts.put("HIGH", 0);

        List<Report> reports = DataHolder.avlTree.fromSmallToLarge(); // Get all reports from the AVL tree

        for (Report report : reports) {
            String priority = report.getPriority().toString();
            int count = priorityCounts.getOrDefault(priority, 0);
            priorityCounts.put(priority, count + 1);
        }

        return priorityCounts;
    }

    private Map<String, Integer> getCategoryCounts() {
        Map<String, Integer> categoryCounts = new HashMap<>();

        // Initialize all categories with 0 count
        for (Category category : Category.values()) {
            categoryCounts.put(category.toString(), 0);
        }

        // Retrieve all reports from the AVL tree
        List<Report> reports = DataHolder.avlTree.fromSmallToLarge();

        // Count the number of reports in each category
        for (Report report : reports) {
            String category = report.getCategory().toString();
            // Increment the count for the corresponding category
            categoryCounts.put(category, categoryCounts.get(category) + 1);
        }
        return categoryCounts;
    }

    private Map<String, Integer> getLocationCounts() {
        Map<String, Integer> locationCounts = new HashMap<>();

        // Retrieve all reports from the AVL tree
        List<Report> reports = DataHolder.avlTree.fromSmallToLarge();

        // Use a HashSet to collect unique locations
        HashSet<String> uniqueLocations = new HashSet<>();

        for (Report report : reports) {
            String location = report.getLocation(); // Assuming getLocation() returns a String
            if (location != null && !location.isEmpty()) {
                uniqueLocations.add(location);
            }
        }

        // Initialize counts for each unique location
        for (String location : uniqueLocations) {
            locationCounts.put(location, 0);
        }

        // Count the number of reports per location
        for (Report report : reports) {
            String location = report.getLocation();
            if (location != null && locationCounts.containsKey(location)) {
                locationCounts.put(location, locationCounts.get(location) + 1);
            }
        }

        return locationCounts;
    }

}