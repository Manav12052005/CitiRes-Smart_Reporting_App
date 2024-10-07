package com.example.prototype;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CategoryChartActivity extends ChartActivity {

    private PieChart pieChart;
    private List<String> categories;
    private List<Integer> counts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inject the child layout into the content_frame of ChartActivity
        setChildContentView(R.layout.activity_category_chart);

        // Handle window insets for the PieChart if necessary
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pieChart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pieChart = findViewById(R.id.pieChart);

        // Check if pieChart is null
        if (pieChart == null) {
            throw new RuntimeException("PieChart not found. Ensure your layout has a PieChart with id 'pieChart'");
        }

        // Retrieve category counts from Intent
        retrieveCategoryCountsFromIntent();

        // Set up and load the PieChart
        setupPieChart();
        loadPieChartData();
    }

    private void retrieveCategoryCountsFromIntent() {
        categories = new ArrayList<>();
        counts = new ArrayList<>();

        for (Category category : Category.values()) {
            String categoryName = category.toString();
            int count = getIntent().getIntExtra(categoryName + "_COUNT", 0);
            if (count > 0) {
                categories.add(categoryName);
                counts.add(count);
            }
        }
    }

    private void setupPieChart() {
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        // Enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // Set the center text
        pieChart.setCenterText("Reports by Category");
        pieChart.setCenterTextSize(18f);
        pieChart.setCenterTextColor(Color.BLACK);

        // Customize legend
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
    }

    private void loadPieChartData() {
        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            entries.add(new PieEntry(counts.get(i), categories.get(i)));
        }

        if (entries.isEmpty()) {
            pieChart.setNoDataText("No reports available");
            pieChart.invalidate();
            return;
        }

        PieDataSet dataSet = new PieDataSet(entries, "Categories");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // Add colors
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);

        // Refresh the chart
        pieChart.invalidate();
        pieChart.animateY(1000);
    }
}
