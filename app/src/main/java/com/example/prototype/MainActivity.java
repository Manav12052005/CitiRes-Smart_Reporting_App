package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment; // Import Fragment class
import androidx.fragment.app.FragmentTransaction; // Import FragmentTransaction class
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
    ImageButton menuNotifications; // Declare menuNotifications as ImageButton
    SearchView searchView;
    List<Report> reportList = new ArrayList<>();
    List<Report> originalList = new ArrayList<>();  // To store original reports
    Button addReportButton;
    List<Report> loadedReports;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
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

        menuDashboard = findViewById(R.id.menu_dashboard);
        menuSearch = findViewById(R.id.menu_search);
        menuNotifications = findViewById(R.id.menu_notifications); // Find the ImageButton for notifications

        menuNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the HomeFragment
                openHomeFragment();
            }
        });

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

    private void openHomeFragment() {
        // Create a new instance of HomeFragment
        Fragment homeFragment = new HomeFragment();

        // Replace the current view with the HomeFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, homeFragment); // Make sure R.id.main is the container for your fragments
        transaction.addToBackStack(null); // Allows users to navigate back
        transaction.commit();
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
        if (streamThread != null) {
            streamThread.interrupt();
        }
    }

    private void startStreamThread() {
        streamThread = new Thread(() -> {
            while (true) {
                applyTheme();
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    // Handle interruption
                }
            }
        });
        streamThread.start();
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

    private void applyTheme() {
        LocalTime currentTime = LocalDateTime.now().toLocalTime();

        if (currentTime.isAfter(DAY_START) && currentTime.isBefore(NIGHT_START)) {
            runOnUiThread(() -> setTheme(R.style.AppTheme_Day));
        } else {
            runOnUiThread(() -> setTheme(R.style.AppTheme_Night));
        }
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
