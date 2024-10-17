package com.example.prototype.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototype.R;
import com.example.prototype.chart.ChartActivity;
import com.example.prototype.report.MainActivity;

/**
 * BaseActivity serves as the foundational activity class that is extended by all other activities
 * and therefore its functionality is inherited by every activity.
 * It provides the following features -
 * 1. Dashboard for Navigation
 * 2. Standard UI
 * 3. UI- Feedback - it displays toast messages for micro interactions like clicking on buttons.
 *
 * It is done so that we don't have to design UI everytime and it decreases code duplicacy and also reduces
 * UI bloat.
 * It is the parent content view for all other activities.
 * It implements the SetChildContentView which defines how the other activities will display on-screen.
 * @author Manav Singh - u7782612
 * */


public class BaseActivity extends AppCompatActivity {

    private ImageButton menuDashboard;
    private ImageButton menuReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);

        //View insets for edge to edge functionality
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Dashboard buttons and functionality
        menuDashboard = findViewById(R.id.menu_dashboard);
        menuReports = findViewById(R.id.menu_reports);

        menuDashboard.setOnClickListener(v -> {
            Toast.makeText(this, "Dashboard Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, MainActivity.class);
            startActivity(intent);
        });

        menuReports.setOnClickListener(v -> {
            Toast.makeText(this, "Reports Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, ChartActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Method for child activities to set their own layout into the content_frame.
     * @param layoutResID The layout resource ID to be inflated.
     * @author Manav
     */
    protected void setChildContentView(int layoutResID) {
        FrameLayout contentFrame = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        View childView = inflater.inflate(layoutResID, contentFrame, false);
        contentFrame.removeAllViews();
        contentFrame.addView(childView);
    }
}
