package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
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
    List<Report> originalList = new ArrayList<>();  // To store original reports
    Button addReportButton;
    List<Report> loadedReports;
    TextView title;
    TextView streamText;
    User user;
    ActivityResultLauncher<Intent> register;
    Thread streamThread;
    private static final LocalTime MORNING = LocalTime.of(6, 0);
    private static final LocalTime NOON = LocalTime.of(11, 0);
    private static final LocalTime AFTERNOON = LocalTime.of(13, 0);
    private static final LocalTime EVENING = LocalTime.of(18, 0);
    private static final LocalTime NIGHT = LocalTime.of(22, 0);

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

        // Initialize the original list with the loaded data
        originalList.addAll(loadedReports);

        adapter = new ReportAdapter(this, avlTree);
        listView.setAdapter(adapter);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    Intent intent = result.getData();
                    if (intent != null && result.getResultCode() == RESULT_OK) {
                        Report addedReport = (Report) intent.getSerializableExtra("added_report", Report.class);
                        avlTree.put(addedReport.getReportId(), addedReport);
                        originalList.add(addedReport);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        addReportButton = findViewById(R.id.add_report_button);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                register.launch(intent);
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

    @Override
    protected void onStart() {
        super.onStart();
        startStreamThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void startStreamThread() {
        streamThread = new Thread(() -> {
            while (true) {
                applyTheme();
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
        Log.d("SearchReports", "Search query: " + query);

        AVLTree<Report> filteredAVLTree = new AVLTree<>();

        if (query.isEmpty()) {
            // If the query is empty, use the original data
            Log.d("SearchReports", "Query is empty, using original list.");
            for (Report report : originalList) {
                filteredAVLTree.put(report.getReportId(), report);
            }
        } else {
            // Tokenize the query and filter reports using the original list
            List<String> tokens = Tokenizer.tokenize(query);
            Log.d("SearchReports", "Tokens: " + tokens);

            List<Report> filteredReports = Parser.parse(tokens, originalList); // Filter reports based on tokens
            Log.d("SearchReports", "Filtered Reports Count: " + filteredReports.size());

            for (Report report : filteredReports) {
                Log.d("SearchReports", "Filtered Report: " + report.getDescription());
                filteredAVLTree.put(report.getReportId(), report);
            }
        }

        // Create a new adapter with the filtered AVLTree and set it to the ListView
        adapter = new ReportAdapter(this, filteredAVLTree);
        listView.setAdapter(adapter);

        // Notify adapter of the data change
        adapter.notifyDataSetChanged();
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
