package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.w3c.dom.Text;

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
    SearchView searchView;
    List<Report> reportList = new ArrayList<>();
    List<Report> originalList = new ArrayList<>();  // To store original reports
    Button addReportButton;
    List<Report> loadedReports;
    TextView title;
    TextView streamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.reports_list);
        searchView = findViewById(R.id.search_view);

        loadedReports = loadData("reports_dataset.json");

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

        menuDashboard = (ImageButton) findViewById(R.id.menu_dashboard);

        menuSearch = (ImageButton) findViewById(R.id.menu_search);

        title = findViewById(R.id.dashboard_title);

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

    Thread streamThread;
    private static final LocalTime MORNING = LocalTime.of(6, 0);
    private static final LocalTime NOON = LocalTime.of(11, 0);
    private static final LocalTime AFTERNOON = LocalTime.of(13, 0);
    private static final LocalTime EVENING = LocalTime.of(18, 0);
    private static final LocalTime NIGHT = LocalTime.of(22, 0);

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
        streamText = findViewById(R.id.streamText);

        if (currentTime.isAfter(MORNING) && currentTime.isBefore(NOON)) {
            runOnUiThread(() -> {
                streamText.setText(R.string.morning);
            });
        } else if (currentTime.isAfter(NOON) && currentTime.isBefore(AFTERNOON)) {
            runOnUiThread(() -> {
                streamText.setText(R.string.noon);
            });
        } else if (currentTime.isAfter(AFTERNOON) && currentTime.isBefore(EVENING)) {
            runOnUiThread(() -> {
                streamText.setText(R.string.afternoon);
            });
        } else if (currentTime.isAfter(EVENING) && currentTime.isBefore(NIGHT)) {
            runOnUiThread(() -> {
                streamText.setText(R.string.evening);
            });
        } else {
            runOnUiThread(() -> {
                streamText.setText(R.string.night);
            });
        }
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
