package com.example.prototype.report;

import static com.example.prototype.util.PriorityUtil.comparePriority;
import static com.example.prototype.util.TimeUtil.isToday;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.prototype.util.BaseActivity;
import com.example.prototype.util.JsonDeserialiser;
import com.example.prototype.R;
import com.example.prototype.data.DataHolder;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.search.Parser;
import com.example.prototype.search.Tokenizer;
import com.example.prototype.util.PriorityUtil;
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
import java.util.Comparator;
import java.util.List;

public class MainActivity extends BaseActivity implements Observer {
    ReportAdapter adapterSort;
    ListView listView;
    Spinner sortSpinner;
    SearchView searchView;
    List<Report> reportList = new ArrayList<>();
    ImageButton addReportButton;
    List<Report> loadedReports;
    String username;
    ActivityResultLauncher<Intent> register;
    Thread streamThread;
    TextView reportCount;
    private List<Report> currentDisplayedList;
    private String currentSearchQuery = "";
    private int currentSortOption = 0; // 0 = Default


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        currentDisplayedList = new ArrayList<>(reportList);


        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);

        listView = findViewById(R.id.reports_list);
        searchView = findViewById(R.id.search_view);
        sortSpinner = findViewById(R.id.sortSpinner);

        loadedReports = loadData("reports_dataset.json");

        if (DataHolder.avlTree.isEmpty()) {
            List<Report> loadedReports = loadData("reports_dataset.json");
            for (Report report : loadedReports) {
                DataHolder.avlTree.put(report.getReportId(), report);
            }
        }

        reportList.addAll(DataHolder.avlTree.fromLargeToSmall());

        adapterSort = new ReportAdapter(this, reportList, this);
        listView.setAdapter(adapterSort);

        reportCount = findViewById(R.id.report_count);
        reportCount.setText("There are " + DataHolder.avlTree.size() + " posts in total, " + TimeUtil.getPostsToday() + " new posts today");

        username = getIntent().getStringExtra("USER");

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    Intent intent = result.getData();
                    if (intent != null && result.getResultCode() == RESULT_OK) {
                        Report addedReport = (Report) intent.getSerializableExtra("added_report", Report.class);
                        reportList.add(0, addedReport);
                        loadedReports.add(0, addedReport);
                        adapterSort = new ReportAdapter(MainActivity.this, new ArrayList<>(loadedReports), MainActivity.this);
                        DataHolder.avlTree.put(addedReport.getReportId(), addedReport);
                        adapterSort.notifyDataSetChanged();
                        listView.setAdapter(adapterSort);
                    }
                }
            }
        });

        reportCount = findViewById(R.id.report_count);

        addReportButton = findViewById(R.id.add_report_button);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                intent.putExtra("USER", username);
                register.launch(intent);
            }
        });

        // Setup Spinner for sorting
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Default", "Sort by Date (Newest First)", "Sort by Date (Oldest First)", "Sort by Priority (High to Low)",
                        "Sort by Priority (Low to High)", "Sort by Likes (Most Liked First)"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                currentSortOption = position;

                // Update the stream state based on current search and sort options
                updateStreamState();

                refreshDisplayedList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Handle SearchView functionality
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
        switch (position) {
            case 0: // Default (no sorting)
                // Optionally, you can reset to the original order if needed
                break;
            case 1: // Sort by Date (Newest First)
                listToSort.sort((report1, report2) -> report2.getLocalDateTime().compareTo(report1.getLocalDateTime()));
                break;
            case 2: // Sort by Date (Oldest First)
                listToSort.sort(Comparator.comparing(Report::getLocalDateTime));
                break;
            case 3: // Sort by Priority (High to Low)
                listToSort.sort((report1, report2) -> PriorityUtil.comparePriority(report2.getPriority(), report1.getPriority()));
                break;
            case 4: // Sort by Priority (Low to High)
                listToSort.sort((report1, report2) -> PriorityUtil.comparePriority(report1.getPriority(), report2.getPriority()));
                break;
            case 5: // Sort by Likes (Most Liked First)
                listToSort.sort((report1, report2) -> Integer.compare(report2.getLikes(), report1.getLikes()));
                break;
            default:
                // Handle default case if needed
                break;
        }
        return listToSort;
    }

    //flag for whether the thread is running
    private boolean isRunning = false;

    @Override
    protected void onStart() {
        super.onStart();
        startStreamThread(10);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopStreamThread();
    }

    //the list for data used for streaming
    public List<Report> streamReports = new ArrayList<>();
    int currentIndex = 0;

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
                    streamThread.interrupt();
                    currentIndex = i;
                    break;
                }
                Report report = streamReports.get(i);
                int reportId = report.getReportId();
                if (DataHolder.avlTree.get(reportId) == null) {
                    report.setLocalDateTime(LocalDateTime.now());
                    reportList.add(0, report);
                    loadedReports.add(0, report);
                    adapterSort = new ReportAdapter(MainActivity.this, new ArrayList<>(reportList), MainActivity.this);
                    DataHolder.avlTree.put(report.getReportId(), report);
                    runOnUiThread(() -> {
                        reportCount.setText("There are " + DataHolder.avlTree.size() + " posts in total, " + TimeUtil.getPostsToday() + " new posts today");
                        adapterSort.notifyDataSetChanged();
                        listView.setAdapter(adapterSort);
                    });
                }
            }
        });
        streamThread.start();
    }

    private void stopStreamThread() {
        isRunning = false;  //the thread should stop running
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

        // Remove from loadedReports
        loadedReports.removeIf(report -> report.getReportId() == reportId);

        // Notify adapter to update view
        adapterSort.notifyDataSetChanged();

        // Refresh the displayed list
        refreshDisplayedList();
    }
}


