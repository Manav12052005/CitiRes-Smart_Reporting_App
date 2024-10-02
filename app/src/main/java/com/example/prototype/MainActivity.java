package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

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
    Listitemadapter adapter;
    ImageButton menuDashboard;
    ImageButton menuSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.reports_list);

        List<Report> reports = loadData("reports_dataset.json");
        for (Report report : reports) {
            avlTree.put(report.getReportId(), report);
        }

        List<Report> reportList = new ArrayList<>(avlTree.fromLargeToSmall());
        adapter = new Listitemadapter(this, reportList);
        listView.setAdapter(adapter);

        Button addReportButton = findViewById(R.id.add_report_button);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        menuDashboard = (ImageButton) findViewById(R.id.menu_dashboard);
        menuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        menuSearch = (ImageButton) findViewById(R.id.menu_search);
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
