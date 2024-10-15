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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import java.util.Calendar;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

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

        // Set OnClickListeners for all buttons with scheduling functionality
        buttonPriorityChart.setOnClickListener(view -> handleChartButton("Priority", this::displayPriorityChartNow));
        buttonCategoryChart.setOnClickListener(view -> handleChartButton("Category", this::displayCategoryChartNow));
        buttonLocationChart.setOnClickListener(view -> handleChartButton("Location", this::displayLocationChartNow));
    }

    private void handleChartButton(String chartType, Runnable displayChartAction) {
        // Create an AlertDialog to ask if the user wants to display the chart now or schedule it
        AlertDialog.Builder builder = new AlertDialog.Builder(ChartActivity.this);
        builder.setTitle("Display " + chartType + " Chart")
                .setMessage("Do you want to display the " + chartType + " chart now or schedule it for later?")
                .setCancelable(true)
                .setPositiveButton("Display Now", (dialog, which) -> {
                    // Immediate display action
                    displayChartAction.run();
                    dialog.dismiss();
                })
                .setNegativeButton("Schedule Later", (dialog, which) -> {
                    // Show time picker dialog to schedule the display
                    showTimePickerDialogForChart(displayChartAction);
                });

        // Show the dialog
        builder.create().show();
    }

    private void displayPriorityChartNow() {
        // Get priority counts
        Map<String, Integer> priorityCounts = getPriorityCounts();
        Intent intent = new Intent(ChartActivity.this, PriorityChartActivity.class);
        // Pass the counts via Intent extras
        intent.putExtra("LOW_COUNT", priorityCounts.get("LOW"));
        intent.putExtra("MIDDLE_COUNT", priorityCounts.get("MIDDLE"));
        intent.putExtra("HIGH_COUNT", priorityCounts.get("HIGH"));
        startActivity(intent);
    }

    private void displayCategoryChartNow() {
        // Get category counts
        Map<String, Integer> categoryCounts = getCategoryCounts();
        Intent intent = new Intent(ChartActivity.this, CategoryChartActivity.class);
        // Pass the category counts via Intent extras
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            String key = entry.getKey() + "_COUNT";
            intent.putExtra(key, entry.getValue());
        }
        startActivity(intent);
    }

    private void displayLocationChartNow() {
        // Get location counts
        Map<String, Integer> locationCounts = getLocationCounts();
        Intent intent = new Intent(ChartActivity.this, LocationChartActivity.class);
        // Pass the location counts via Intent extras
        intent.putExtra("LOCATION_COUNTS", (Serializable) locationCounts);
        startActivity(intent);
    }

    private void scheduleChartDisplay(long delayInMillis, Runnable displayChartAction) {
        // Use the Handler to schedule the display after the specified delay
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(displayChartAction, delayInMillis);

        Toast.makeText(this, "Chart scheduled for later.", Toast.LENGTH_SHORT).show();
    }

    private void showTimePickerDialogForChart(Runnable displayChartAction) {
        // Get the current time
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        // Create a TimePickerDialog to pick a time
        TimePickerDialog timePickerDialog = new TimePickerDialog(ChartActivity.this,
                (view, selectedHour, selectedMinute) -> {
                    // Get the current time
                    Calendar now = Calendar.getInstance();
                    Calendar displayTime = Calendar.getInstance();
                    displayTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    displayTime.set(Calendar.MINUTE, selectedMinute);
                    displayTime.set(Calendar.SECOND, 0);

                    // Calculate the delay in milliseconds
                    long delayInMillis = displayTime.getTimeInMillis() - now.getTimeInMillis();
                    if (delayInMillis > 0) {
                        // Schedule the chart display with the calculated delay
                        scheduleChartDisplay(delayInMillis, displayChartAction);
                    } else {
                        // If the selected time is in the past or immediate, display it now
                        displayChartAction.run();
                    }
                }, hour, minute, true); // Use 24-hour time format

        timePickerDialog.setTitle("Select Time to Display Chart");
        timePickerDialog.show();
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
