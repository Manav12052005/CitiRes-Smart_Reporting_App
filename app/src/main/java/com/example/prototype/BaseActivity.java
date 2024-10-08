package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    private ImageButton menuDashboard;
    private ImageButton menuSearch;
    private ImageButton menuNotifications;
    private ImageButton menuReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Ensure EdgeToEdge is correctly implemented
        setContentView(R.layout.activity_base);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Bottom Navigation Buttons
        menuDashboard = findViewById(R.id.menu_dashboard);
        menuSearch = findViewById(R.id.menu_search);
        menuNotifications = findViewById(R.id.menu_notifications);
        menuReports = findViewById(R.id.menu_reports);

        // Set OnClickListeners for Navigation Buttons
        menuDashboard.setOnClickListener(v -> {
            // Navigate to DashboardActivity or handle dashboard functionality
            Toast.makeText(this, "Dashboard Clicked", Toast.LENGTH_SHORT).show();
            // Create an Intent to start MainActivity
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);
        });

        menuSearch.setOnClickListener(v -> {
            // Navigate to SearchActivity
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
            // Create an Intent to start MainActivity
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);
            
        });

        menuNotifications.setOnClickListener(v -> {
            // Navigate to NotificationsActivity
            Toast.makeText(this, "Notifications Clicked", Toast.LENGTH_SHORT).show();
            // Example:
            // Intent intent = new Intent(ChartActivity.this, NotificationsActivity.class);
            // startActivity(intent);
        });

        menuReports.setOnClickListener(v -> {
            // Navigate to ReportsActivity or another ChartActivity
            Toast.makeText(this, "Reports Clicked", Toast.LENGTH_SHORT).show();
            // Create an Intent to start PriorityChartActivity
            Intent intent = new Intent(BaseActivity.this, ChartActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Method for child activities to set their own layout into the content_frame.
     * @param layoutResID The layout resource ID to be inflated.
     */
    protected void setChildContentView(int layoutResID) {
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        View childView = inflater.inflate(layoutResID, contentFrame, false);
        contentFrame.removeAllViews();
        contentFrame.addView(childView);
    }
}
