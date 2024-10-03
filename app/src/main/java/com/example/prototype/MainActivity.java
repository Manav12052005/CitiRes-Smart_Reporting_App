package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AVLTree<Report> avlTree = new AVLTree<>();
    ReportAdapter adapter;
    ListView listView;
    ImageButton menuDashboard;
    ImageButton menuSearch;
    Button addReportButton;
    List<Report> loadedReports;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.reports_list);

        loadedReports = loadData("reports.json");

        for (Report report : loadedReports) {
            avlTree.put(report.getReportId(), report);
        }

        adapter = new ReportAdapter(this, avlTree);
        listView.setAdapter(adapter);

        addReportButton = findViewById(R.id.add_report_button);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
        menuDashboard = findViewById(R.id.menu_dashboard);

        menuDashboard = (ImageButton) findViewById(R.id.menu_dashboard);

        menuSearch = (ImageButton) findViewById(R.id.menu_search);

        title = findViewById(R.id.dashboard_title);
    }

    Thread streamThread;
    private static final LocalTime DAY_START = LocalTime.of(6, 0);
    private static final LocalTime NIGHT_START = LocalTime.of(18, 0);

    @Override
    protected void onStart() {
        super.onStart();
        startStreamThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopStreamThread();
    }

    private void stopStreamThread() {
        super.onStop();
        streamThread.interrupt();
    }

    private void startStreamThread() {
        streamThread = new Thread(() -> {
            while (true) {
                applyTheme();
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {

                }
            }
        });
        streamThread.start();
    }

    private void applyTheme() {
        LocalTime currentTime = LocalDateTime.now().toLocalTime();

        if (currentTime.isAfter(DAY_START) && currentTime.isBefore(NIGHT_START)) {
            runOnUiThread(() -> {
                setTheme(R.style.AppTheme_Day);
            });
        } else {
            runOnUiThread(() -> {
                setTheme(R.style.AppTheme_Night);
            });
        }
    }

    public List<Report> loadData(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Report.class, new ReportAdapterJson());
        Gson gson = gsonBuilder.create();

        List<Report> reportList = new ArrayList<>();
        final Type CUS_LIST_TYPE = new TypeToken<List<Report>>() {
        }.getType();

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
