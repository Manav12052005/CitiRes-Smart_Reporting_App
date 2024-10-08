package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class MainActivity extends BaseActivity implements OnClickPassData {
    ReportAdapterSort adapterSort;
    ListView listView;
    Spinner sortSpinner;
    ImageButton menuDashboard;
    ImageButton menuSearch;
    SearchView searchView;
    List<Report> reportList = new ArrayList<>();  // To store original reports
    Button addReportButton;
    List<Report> loadedReports;
    TextView title;
    TextView streamText;
    User user;
    ActivityResultLauncher<Intent> register;
    Thread streamThread;
    ImageButton menuNotifications;
    // Declare the reports button
    ImageButton menuReports;


    private static final LocalTime MORNING = LocalTime.of(6, 0);
    private static final LocalTime NOON = LocalTime.of(11, 0);
    private static final LocalTime AFTERNOON = LocalTime.of(13, 0);
    private static final LocalTime EVENING = LocalTime.of(18, 0);
    private static final LocalTime NIGHT = LocalTime.of(22, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);

        listView = findViewById(R.id.reports_list);
        searchView = findViewById(R.id.search_view);
        sortSpinner = findViewById(R.id.sortSpinner);

        loadedReports = loadData("reports_dataset.json");

        reportText = findViewById(R.id.reportText);

        for (Report report : loadedReports) {
            DataHolder.avlTree.put(report.getReportId(), report);
        }

        reportList.addAll(loadedReports);

        adapterSort = new ReportAdapter(this, reportList, this);
        listView.setAdapter(adapterSort);

        // Setup Spinner for sorting
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Default", "Sort by Date (newest First)", "Sort by Date (oldest First)", "Sort by Priority (High to Low)",
                        "Sort by Priority (Low to High)", "Sort by Likes (most liked first)"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                sortReports(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null) {
                    Intent intent = result.getData();
                    if (intent != null && result.getResultCode() == RESULT_OK) {
                        Report addedReport = (Report) intent.getSerializableExtra("added_report", Report.class);
                        reportList.add(0, addedReport);
                        loadedReports.add(0,addedReport);
                        adapterSort = new ReportAdapterSort(MainActivity.this, new ArrayList<>(loadedReports), MainActivity.this);
                        DataHolder.avlTree.put(addedReport.getReportId(), addedReport);
                        adapterSort.notifyDataSetChanged();
                        listView.setAdapter(adapterSort);
                    }
                }
            }
        });
        menuNotifications = findViewById(R.id.menu_notifications);
        menuNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the NotificationActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
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

        // Initialize the reports button
        menuReports = findViewById(R.id.menu_reports);

        // Set an OnClickListener to navigate to PriorityChartActivity
        menuReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start PriorityChartActivity
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);

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
                if (newText.isEmpty()) {
                    // If search query is empty, reset the adapter to the original list
                    adapterSort = new ReportAdapter(MainActivity.this, new ArrayList<>(loadedReports), MainActivity.this);
                    listView.setAdapter(adapterSort);
                    adapterSort.notifyDataSetChanged();
                } else {
                    // Perform search and set the filtered adapter
                    searchReports(newText);
                }
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
                try {
                    Thread.sleep(10 * 1000); // Initial delay
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                applyTheme();
            }
        });
        streamThread.start();
    }

    private void applyTheme() {
        LocalTime currentTime = LocalDateTime.now().toLocalTime();
        streamText = findViewById(R.id.streamText);
        runOnUiThread(() -> {
            reportText.setText("There are " + avlTree.size() + " reports in total");
        });

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

        List<Report> filteredList = new ArrayList<>();

        if (!query.isEmpty()) {

            List<String> tokens = Tokenizer.tokenize(query);

            filteredList = Parser.parseWithGrammar(tokens, reportList);

        }


        adapterSort = new ReportAdapter(this, filteredList, this);
        listView.setAdapter(adapterSort);

        adapterSort.notifyDataSetChanged();
    }

    private void sortReports(int position) {
        switch (position) {
            case 0: // Default
                // Reset the adapter to use the original list
                adapterSort = new ReportAdapter(MainActivity.this, new ArrayList<>(reportList), MainActivity.this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            case 1: // Sort by Date (newest First)
                List<Report> sortedListNewest = new ArrayList<>(reportList);
                sortedListNewest.sort((report1, report2) -> report2.getLocalDateTime().compareTo(report1.getLocalDateTime()));
                adapterSort = new ReportAdapter(this, sortedListNewest, this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            case 2: // Sort by Date (oldest First)
                List<Report> sortedListOldest = new ArrayList<>(reportList);
                sortedListOldest.sort((report1, report2) -> report1.getLocalDateTime().compareTo(report2.getLocalDateTime()));
                adapterSort = new ReportAdapter(this, sortedListOldest, this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            case 3: // Sort by Priority (High to Low)
                List<Report> sortedListHighToLow = new ArrayList<>(reportList);
                sortedListHighToLow.sort((report1, report2) -> comparePriority(report2.getPriority(), report1.getPriority()));
                adapterSort = new ReportAdapter(this, sortedListHighToLow, this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            case 4: // Sort by Priority (Low to High)
                List<Report> sortedListLowToHigh = new ArrayList<>(reportList);
                sortedListLowToHigh.sort((report1, report2) -> comparePriority(report1.getPriority(), report2.getPriority()));
                adapterSort = new ReportAdapter(this, sortedListLowToHigh, this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            case 5: // Sort by Likes (most liked first)
                List<Report> sortedListMostLiked = new ArrayList<>(reportList);
                sortedListMostLiked.sort((report1, report2) -> Integer.compare(report2.getLikes(), report1.getLikes()));
                adapterSort = new ReportAdapter(this, sortedListMostLiked, this);
                listView.setAdapter(adapterSort);
                adapterSort.notifyDataSetChanged();
                break;
            default:
                return;
        }
    }

    // Custom priority comparator function
    private int comparePriority(Priority p1, Priority p2) {
        // Define the custom order: LOW < MIDDLE < HIGH
        int priorityValue1 = getPriorityValue(p1);
        int priorityValue2 = getPriorityValue(p2);
        return Integer.compare(priorityValue1, priorityValue2);
    }

    // Helper function to assign custom values to priorities
    private int getPriorityValue(Priority priority) {
        switch (priority) {
            case LOW:
                return 1;
            case MIDDLE:
                return 2;
            case HIGH:
                return 3;
            default:
                return Integer.MAX_VALUE; // Default case for safety
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
    }


}


