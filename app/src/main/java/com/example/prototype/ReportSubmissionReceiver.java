package com.example.prototype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import android.location.Location;
import java.util.Date;
import java.time.LocalDateTime;

public class ReportSubmissionReceiver extends BroadcastReceiver {
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onReceive(final Context context, Intent intent) {
        // Retrieve report data from the intent
        final String description = intent.getStringExtra("description");
        final String categoryString = intent.getStringExtra("category");
        final String priorityString = intent.getStringExtra("priority");
        final String locationName = intent.getStringExtra("location");
        final String username = intent.getStringExtra("username");

        if (description != null && categoryString != null && priorityString != null && locationName != null) {
            final Category category = Category.valueOf(categoryString);
            final Priority priority = Priority.valueOf(priorityString);

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

            // Check location permission
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            // Call the method to submit the report
                            submitReport(context, description, category, priority, locationName, username);
                        } else {
                            Toast.makeText(context, "Unable to retrieve location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(context, "Location permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void submitReport(Context context, String description, Category category, Priority priority, String locationName, String username) {
        if (description.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            Report newReport = new Report();
            newReport.setDescription(description);
            newReport.setUser(new User(username));
            newReport.setLocation(locationName);
            newReport.setCategory(category);
            newReport.setPriority(priority);
            newReport.setReportId(ReportCounter.getReportId());
            newReport.setLocalDateTime(LocalDateTime.now());
            ReportCounter.inc();

            // Optionally, notify the user or update the UI (this is just an example)
            Toast.makeText(context, "Scheduled Report Submitted!", Toast.LENGTH_LONG).show();
        }
    }
}
