package com.example.prototype.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototype.R;
import com.example.prototype.util.BaseActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationChartActivity extends BaseActivity {

    private RadarChart radarChart;
    private List<String> locations;
    private List<Float> counts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Ensure EdgeToEdge is correctly implemented

        // Inject the child layout into the content_frame of BaseActivity
        setChildContentView(R.layout.activity_location_chart);

        // Handle window insets for the RadarChart if necessary
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.location_chart_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radarChart = findViewById(R.id.radarChart);

        // Check if radarChart is null
        if (radarChart == null) {
            throw new RuntimeException("RadarChart not found. Ensure your layout has a RadarChart with id 'radarChart'");
        }

        // Retrieve location counts from Intent
        retrieveLocationCountsFromIntent();

        // Log the retrieved counts for debugging
        for (int i = 0; i < locations.size(); i++) {
            Log.d("LocationChartActivity", locations.get(i) + ": " + counts.get(i));
        }

        // Set up and load the RadarChart
        setupRadarChart();
        loadRadarChartData();
    }

    private void retrieveLocationCountsFromIntent() {
        // Retrieve the Serializable extra containing the location counts map
        Serializable extra = getIntent().getSerializableExtra("LOCATION_COUNTS");
        if (extra instanceof Map) {
            Map<String, Integer> locationCounts = (Map<String, Integer>) extra;
            locations = new ArrayList<>();
            counts = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : locationCounts.entrySet()) {
                locations.add(entry.getKey());
                counts.add(entry.getValue().floatValue());
            }
        } else {
            // Handle the case where the extra is not present or not a Map
            Log.e("LocationChartActivity", "No location counts data found in Intent extras.");
            locations = new ArrayList<>();
            counts = new ArrayList<>();
        }
    }

    private void setupRadarChart() {
        radarChart.setBackgroundColor(Color.WHITE);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.LTGRAY);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.LTGRAY);
        radarChart.setWebAlpha(100);

        radarChart.getDescription().setEnabled(false);
        radarChart.setRotationEnabled(false);

        // Set XAxis properties
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(locations));
        xAxis.setTextColor(Color.BLACK);

        // Set YAxis properties
        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(getMaxCount() + 5); // Add buffer
        yAxis.setDrawLabels(false); // Hide Y labels
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(true);
        yAxis.setGridColor(Color.LTGRAY);
        yAxis.setGridLineWidth(1f);

        // Customize Legend
        Legend legend = radarChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(false); // Disable if not needed
    }

    private float getMaxCount() {
        float max = 0f;
        for (Float count : counts) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private void loadRadarChartData() {
        List<RadarEntry> entries = new ArrayList<>();

        for (Float count : counts) {
            entries.add(new RadarEntry(count));
        }

        if (entries.isEmpty()) {
            radarChart.setNoDataText("No reports available");
            radarChart.invalidate();
            return;
        }

        RadarDataSet dataSet = new RadarDataSet(entries, "Reports by Location");
        dataSet.setColor(Color.BLUE);
        dataSet.setFillColor(Color.BLUE);
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(180);
        dataSet.setLineWidth(2f);
        dataSet.setDrawHighlightCircleEnabled(true);
        dataSet.setDrawHighlightIndicators(false);

        RadarData data = new RadarData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        radarChart.setData(data);

        // Refresh the chart
        radarChart.invalidate();
        radarChart.animateXY(1400, 1400);
    }
}
