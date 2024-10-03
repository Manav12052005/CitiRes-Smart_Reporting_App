package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AVLTree<Report> avlTree = new AVLTree<>();
    ReportAdapter adapter;
    ImageButton menuDashboard;
    ImageButton menuSearch;
    SearchView searchView;

    List<Report> reportList = new ArrayList<>();
    List<Report> originalList = new ArrayList<>();  // To store original reports

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.reports_list);
        searchView = findViewById(R.id.search_view);

        List<Report> reports = loadData("reports_dataset.json");
        for (Report report : reports) {
            avlTree.put(report.getReportId(), report);
        }

        originalList.addAll(avlTree.fromLargeToSmall());
        reportList.addAll(originalList);

        adapter = new ReportAdapter(this, reportList);
        listView.setAdapter(adapter);

        Button addReportButton = findViewById(R.id.add_report_button);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        menuDashboard = findViewById(R.id.menu_dashboard);
        menuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        menuSearch = findViewById(R.id.menu_search);

        // Handle SearchView functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchReports(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchReports(newText);
                return true;
            }
        });
    }

    // Function to filter the reports based on the search query
    private void searchReports(String query) {
        if (query.isEmpty()) {
            reportList.clear();
            reportList.addAll(originalList);  // Reset to original list if query is empty
        } else {
            List<String> tokens = Tokenizer.tokenize(query);  // Tokenize the query
            List<Report> filteredReports = Parser.parse(tokens, originalList);  // Filter reports using Parser

            reportList.clear();
            reportList.addAll(filteredReports);  // Update the filtered list
        }

        adapter.notifyDataSetChanged();  // Notify the adapter about data changes
    }

    public List<Report> loadData(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Report.class, new ReportAdapterJson());
        Gson gson = gsonBuilder.create();

        List<Report> reportList = new ArrayList<>();
        final Type CUS_LIST_TYPE = new TypeToken<List<Report>>() {}.getType();

        try (InputStream inputStream = this.getAssets().open(fileName);
             InputStreamReader reader = new InputStreamReader(inputStream);
             JsonReader jsonReader = new JsonReader(reader)) {
            reportList = gson.fromJson(jsonReader, CUS_LIST_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportList;
    }
}
