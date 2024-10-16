package com.example.prototype.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototype.util.BaseActivity;
import com.example.prototype.R;
import com.example.prototype.entity.Category;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is intended to display a pie chart depicting the current reports based on categories.
 * It uses the MPAndroid Chart library to create the chart.
 * It utilises data from the DataHolder class which holds the updated tree.
 * The pie chart is rotatable by touch and drag.
 * It shows percentage of reports in every category.
 * It extends BaseActivity to show the dashboard and is set as ChildContent.
 * Part of Feature - Data_Graphical
 * @author Manav Singh*/

public class CategoryChartActivity extends BaseActivity {
    private PieChart pieChart;
    private List<String> categories;
    private List<Integer> counts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inject the child layout into the content_frame of BaseActivity
        setChildContentView(R.layout.activity_category_chart);

        // Handle window insets for the PieChart if necessary
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chart_activity_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pieChart = findViewById(R.id.pieChart);

        if (pieChart == null) {
            throw new RuntimeException("PieChart not found. Ensure your layout has a PieChart with id 'pieChart'");
        }

        retrieveCategoryCountsFromIntent();

        // Logging
        for (int i = 0; i < categories.size(); i++) {
            Log.d("CategoryChartActivity", categories.get(i) + ": " + counts.get(i));
        }

        setupPieChart();
        loadPieChartData();
    }

    private void retrieveCategoryCountsFromIntent() {
        categories = new ArrayList<>();
        counts = new ArrayList<>();

        for (Category category : Category.values()) {
            String categoryName = category.name();
            int count = getIntent().getIntExtra(categoryName + "_COUNT", 0);
            if (count > 0) { //Add categories with non-zero counts
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

        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.setCenterText("Reports by Category");
        pieChart.setCenterTextSize(13f);
        pieChart.setCenterTextColor(Color.BLACK);

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
