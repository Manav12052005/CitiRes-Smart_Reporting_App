package com.example.prototype.chart;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototype.util.BaseActivity;
import com.example.prototype.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is intended to display a Bar chart depicting the current reports based on Priority.
 * It uses the MPAndroid Chart library to create the chart.
 * It utilises data from the DataHolder class which holds the updated tree.
 * It shows number of reports in every priority.
 * It extends BaseActivity to show the dashboard and is set as ChildContent.
 * Part of Feature - Data_Graphical
 * @author Manav Singh*/

public class PriorityChartActivity extends BaseActivity {

    private BarChart barChart;
    private int lowCount;
    private int middleCount;
    private int highCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setChildContentView(R.layout.activity_priority_chart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.barChart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        barChart = findViewById(R.id.barChart);

        // Retrieve_Intent
        lowCount = getIntent().getIntExtra("LOW_COUNT", 0);
        middleCount = getIntent().getIntExtra("MIDDLE_COUNT", 0);
        highCount = getIntent().getIntExtra("HIGH_COUNT", 0);

        //Error handling
        if (barChart == null) {
            throw new RuntimeException("BarChart not found. Ensure your layout has a BarChart with id 'barChart'");
        }

        setupBarChart();
        loadBarChartData();
    }

    private void setupBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // X-axis configuration
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-45);

        // Disable right Y-axis
        barChart.getAxisRight().setEnabled(false);

        // Animation
        barChart.animateY(1000);

        barChart.getLegend().setEnabled(false);
    }

    private void loadBarChartData() {
        List<BarEntry> entries = new ArrayList<>();
        List<String> priorityLevels = new ArrayList<>();
        int index = 0;

        String[] priorities = {"LOW", "MIDDLE", "HIGH"};
        int[] counts = {lowCount, middleCount, highCount};

        for (int i = 0; i < priorities.length; i++) {
            entries.add(new BarEntry(index, counts[i]));
            priorityLevels.add(priorities[i]);
            index++;
        }

        //Error handling
        if (entries.isEmpty()) {
            barChart.setNoDataText("No reports available");
            barChart.invalidate();
            return;
        }

        BarDataSet dataSet = new BarDataSet(entries, "Reports by Priority");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(dataSet);
        data.setValueTextSize(12f);
        data.setBarWidth(0.9f);

        barChart.setData(data);

        // X-axis label setup
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(priorityLevels));
        xAxis.setLabelCount(priorityLevels.size());
        xAxis.setGranularity(1f);

        barChart.setFitBars(true);
        barChart.invalidate(); // Refresh the chart
    }
}
