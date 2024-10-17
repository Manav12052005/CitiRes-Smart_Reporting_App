package com.example.prototype.report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.prototype.sort.ReportSorter;
import com.example.prototype.sort.ReportSorterFactory;
import com.example.prototype.util.BaseActivity;
import com.example.prototype.util.JsonDeserialiser;
import com.example.prototype.R;
import com.example.prototype.data.DataHolder;
import com.example.prototype.entity.Report;
import com.example.prototype.search.Parser;
import com.example.prototype.search.Tokenizer;
import com.example.prototype.util.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main activity of the application. It is the primary interface of the application.
 * It allows you to access the following functionalities -
 * 1. Search
 * 2. Load and Show Data
 * 3. Button for Adding Report
 * 4. Displaying individual reports
 * 5. Sorting reports
 * 6. Deleting Reports
 * <p>
 * It extends BaseActivity to show the dashboard and is set as ChildContent.
 * <p>
 * Part of Features - [Search], [Search-Filter], [DataStream], [LoadShowData], [Data-Deletion]
 *
 * @author Yuan u7787385 - ListView, ReportAdapter for viewing existing and newly added reports.
 * - DataStream Thread. Load_Show_Data.
 * - Passing data to and from another activity. I reference android studio's official documentation through
 *      following link: https://developer.android.com/training/basics/intents/result.
 * @author Harry - Search Button and Functionality, Sort and Filter Functionality.
 * @author Manav - Passing Username intent to ReportActivity.
 * @author Amogh - Helped with various functions across the file.
 */

public class MainActivity extends BaseActivity implements Observer {
    private ReportAdapter adapterSort;
    private ListView listView;
    private Spinner sortSpinner;
    private SearchView searchView;
    private List<Report> reportList = new ArrayList<>();  // Main data source
    private ImageButton addReportButton;
    private String username;
    private ActivityResultLauncher<Intent> register;
    private Thread streamThread;
    private TextView reportCount;

    private List<Report> currentDisplayedList;    // Displayed after search and sort
    private String currentSearchQuery = "";
    private int currentSortOption = 0; // 0 = Default sort option

    private boolean isRunning = false;
    private List<Report> streamReports = new ArrayList<>();
    private int currentIndex = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);

        // Initialize views
        listView = findViewById(R.id.reports_list);
        searchView = findViewById(R.id.search_view);
        sortSpinner = findViewById(R.id.sortSpinner);
        reportCount = findViewById(R.id.report_count);
        addReportButton = findViewById(R.id.add_report_button);

        // Initialize data
        initializeData();

        // Set up adapter with the current displayed list
        currentDisplayedList = new ArrayList<>(reportList);
        adapterSort = new ReportAdapter(this, currentDisplayedList, this);
        listView.setAdapter(adapterSort);

        // Update report count
        updateReportCount();

        // Get username from intent
        username = getIntent().getStringExtra("USER");

        // Set up add report button
        addReportButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            intent.putExtra("USER", username);
            register.launch(intent);
        });

        // Set up result launcher for adding new reports
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null) {
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == RESULT_OK) {
                    Report addedReport = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        addedReport = (Report) intent.getSerializableExtra("added_report", Report.class);
                    }
                    if (addedReport != null) {
                        // Add new report to data sources
                        reportList.add(0, addedReport);
                        DataHolder.avlTree.put(addedReport.getReportId(), addedReport);
                        // Refresh displayed list
                        refreshDisplayedList();
                        // Update report count
                        updateReportCount();
                    }
                }
            }
        });
        // Set up spinner for sorting
        setupSortSpinner();

        // Set up search view
        setupSearchView();
    }

    private void initializeData() {
        if (DataHolder.avlTree.isEmpty()) {
            // Load reports from JSON and populate AVL tree
            List<Report> loadedReports = loadData("reports_dataset.json");
            for (Report report : loadedReports) {
                DataHolder.avlTree.put(report.getReportId(), report);
            }
        }
        // Populate reportList from AVL tree
        reportList.addAll(DataHolder.avlTree.fromLargeToSmall());
    }

    private void updateReportCount() {
        reportCount.setText("There are " + DataHolder.avlTree.size() + " posts in total, " +
                TimeUtil.getPostsToday() + " new posts today");
    }

    private void setupSortSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Default", "Sort by Date (Newest First)", "Sort by Date (Oldest First)",
                        "Sort by Priority (High to Low)", "Sort by Priority (Low to High)", "Sort by Likes (Most Liked First)"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                currentSortOption = position;

                // Update the stream state based on current search and sort options
                updateStreamState();

                // Refresh displayed list
                refreshDisplayedList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No action needed
            }
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentSearchQuery = query;
                updateStreamState();
                refreshDisplayedList();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearchQuery = newText;
                updateStreamState();
                refreshDisplayedList();
                return true;
            }
        });
    }

    private void updateStreamState() {
        if (currentSearchQuery.isEmpty() && currentSortOption == 0) {
            startStreamThread(10);
        } else {
            stopStreamThread();
        }
    }

    private void refreshDisplayedList() {
        // Filter based on search query
        List<Report> filteredList;
        if (currentSearchQuery.isEmpty()) {
            filteredList = new ArrayList<>(reportList);
        } else {
            List<String> tokens = Tokenizer.tokenize(currentSearchQuery);
            filteredList = Parser.parseWithGrammar(tokens, reportList);
        }

        // Sort the filtered list
        currentDisplayedList = sortReports(filteredList, currentSortOption);

        // Update the adapter
        adapterSort = new ReportAdapter(this, currentDisplayedList, this);
        listView.setAdapter(adapterSort);
        adapterSort.notifyDataSetChanged();
    }

    private List<Report> sortReports(List<Report> listToSort, int position) {
        ReportSorter sorter = ReportSorterFactory.createSorter(position);
        return sorter.sort(listToSort);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Update stream state based on current search and sort options
        updateStreamState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopStreamThread();
    }

    @SuppressLint("SetTextI18n")
    private void startStreamThread(int intervalSeconds) {
        isRunning = true;

        streamThread = new Thread(() -> {
            if (streamReports.isEmpty()) {
                streamReports = loadData("streams_dataset.json");
            }

            for (int i = currentIndex; i < streamReports.size(); i++) {
                if (!isRunning) {
                    currentIndex = i;
                    break;
                }
                try {
                    Thread.sleep(intervalSeconds * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    currentIndex = i;
                    break;
                }
                Report report = streamReports.get(i);
                int reportId = report.getReportId();
                if (DataHolder.avlTree.get(reportId) == null) {
                    report.setLocalDateTime(LocalDateTime.now());
                    // Add new report to data sources
                    reportList.add(0, report);
                    DataHolder.avlTree.put(report.getReportId(), report);

                    runOnUiThread(() -> {
                        // Update report count and refresh displayed list
                        updateReportCount();
                        refreshDisplayedList();
                    });
                }
            }
        });
        streamThread.start();
    }

    private void stopStreamThread() {
        isRunning = false;  // The thread should stop running
        if (streamThread != null && streamThread.isAlive()) {
            streamThread.interrupt();  // Interrupt the thread
        }
    }

    public List<Report> loadData(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Report.class, new JsonDeserialiser());
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

    @Override
    public void onClickPassData(int reportId) {
        // Remove from AVL Tree
        DataHolder.avlTree.remove(reportId);

        // Remove from reportList
        reportList.removeIf(report -> report.getReportId() == reportId);

        // Refresh the displayed list
        refreshDisplayedList();

        // Update report count
        updateReportCount();
    }
}


