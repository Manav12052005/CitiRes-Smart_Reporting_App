package com.example.prototype.chart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import java.util.Calendar;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.prototype.util.BaseActivity;
import com.example.prototype.R;
import com.example.prototype.data.DataHolder;
import com.example.prototype.entity.Report;

/**
 * ChartActivity is the activity page from where you can access 3 graph activity pages.
 * It allows you to show the graph instantly or schedule the opening of it at a specific time. You are able to browse other parts of the app while waiting.
 * It extends BaseActivity to show the dashboard.
 * It consists of a simple heading and 3 buttons for each graph routed to their specific activity.
 * Part of Feature - Data_Graphical and Interact_Scheduled.
 * @author Manav Singh - Data_Graphical
 * @author Yuvraj - Interact_Scheduled Functionality (Methods - scheduleChartDisplay and showTimePickerDialogForChart).
 * */

public class ChartActivity extends BaseActivity {

    private Button buttonPriorityChart;
    private Button buttonCategoryChart;
    private Button buttonLocationChart;

    private ReportAnalyzer reportAnalyzer = new ReportAnalyzer(); // Instantiate ReportAnalyzer

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

        buttonPriorityChart.setOnClickListener(view -> {
            // Create an AlertDialog to ask if the user wants to display the chart now or schedule it
            AlertDialog.Builder builder = new AlertDialog.Builder(ChartActivity.this);
            builder.setTitle("Display Priority Chart")
                    .setMessage("Do you want to display the priority chart now or schedule it for later?")
                    .setCancelable(true)
                    .setPositiveButton("Display Now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Immediate display action
                            displayPriorityChartNow();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Schedule Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Show time picker dialog to schedule the display
                            showTimePickerDialogForChart();
                        }
                    });

            // Show the dialog
            builder.create().show();
        });

        buttonCategoryChart.setOnClickListener(view -> {
            // Get category counts
            List<Report> reports = DataHolder.avlTree.fromSmallToLarge();
            Map<String, Integer> categoryCounts = reportAnalyzer.getCategoryCounts(reports);
            Intent intent = new Intent(ChartActivity.this, CategoryChartActivity.class);
            // Optionally, pass any required data via intent
            for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
                String key = entry.getKey() + "_COUNT";
                intent.putExtra(key, entry.getValue());
            }
            startActivity(intent);
        });

        buttonLocationChart.setOnClickListener(view -> {
            // Get location counts
            List<Report> reports = DataHolder.avlTree.fromSmallToLarge();
            Map<String, Integer> locationCounts = reportAnalyzer.getLocationCounts(reports);

            // Create an Intent to start LocationChartActivity
            Intent intent = new Intent(ChartActivity.this, LocationChartActivity.class);

            // Pass the location counts map as a Serializable extra
            intent.putExtra("LOCATION_COUNTS", (Serializable) locationCounts);

            // Start the LocationChartActivity
            startActivity(intent);
        });
    }

    private void displayPriorityChartNow() {
        // Get priority counts
        List<Report> reports = DataHolder.avlTree.fromSmallToLarge();
        Map<String, Integer> priorityCounts = reportAnalyzer.getPriorityCounts(reports);
        Intent intent = new Intent(ChartActivity.this, PriorityChartActivity.class);
        // Pass the counts via Intent extras
        intent.putExtra("LOW_COUNT", priorityCounts.get("LOW"));
        intent.putExtra("MIDDLE_COUNT", priorityCounts.get("MIDDLE"));
        intent.putExtra("HIGH_COUNT", priorityCounts.get("HIGH"));
        startActivity(intent);
    }

    private void scheduleChartDisplay(long delayInMillis) {
        // Use the Handler to schedule the display after the specified delay
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayPriorityChartNow(); // Display the chart after the delay
            }
        }, delayInMillis);

        Toast.makeText(this, "Priority Chart scheduled for later.", Toast.LENGTH_SHORT).show();
    }

    private void showTimePickerDialogForChart() {
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
                        scheduleChartDisplay(delayInMillis);
                    } else {
                        // If the selected time is in the past or immediate, display it now
                        displayPriorityChartNow();
                    }
                }, hour, minute, true); // Use 24-hour time format

        timePickerDialog.setTitle("Select Time to Display Chart");
        timePickerDialog.show();
    }
}
