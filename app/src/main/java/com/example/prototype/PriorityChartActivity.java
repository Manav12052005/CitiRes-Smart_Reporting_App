package com.example.prototype;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PriorityChartActivity extends BaseActivity {

    private BarChart barChart;
    private int lowCount;
    private int middleCount;
    private int highCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject the child layout into the content_frame of ChartActivity
        setChildContentView(R.layout.activity_priority_chart);

        // Handle window insets for the BarChart if necessary
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.barChart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        barChart = findViewById(R.id.barChart);

        // Retrieve counts from Intent
        lowCount = getIntent().getIntExtra("LOW_COUNT", 0);
        middleCount = getIntent().getIntExtra("MIDDLE_COUNT", 0);
        highCount = getIntent().getIntExtra("HIGH_COUNT", 0);

        // Check if barChart is null
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

        // Disable legend (optional)
        barChart.getLegend().setEnabled(false);
    }

    private void loadBarChartData() {
        // Prepare data entries
        List<BarEntry> entries = new ArrayList<>();
        List<String> priorityLevels = new ArrayList<>();
        int index = 0;

        // Ensure priorities are ordered as LOW, MIDDLE, HIGH
        String[] priorities = {"LOW", "MIDDLE", "HIGH"};
        int[] counts = {lowCount, middleCount, highCount};

        for (int i = 0; i < priorities.length; i++) {
            entries.add(new BarEntry(index, counts[i]));
            priorityLevels.add(priorities[i]);
            index++;
        }

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
