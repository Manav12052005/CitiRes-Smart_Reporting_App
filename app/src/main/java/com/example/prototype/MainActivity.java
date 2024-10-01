package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Report> arrayList;
    ArrayList<Report> originalList;
    ReportAdapter adapter;
    ImageButton menuDashboard;
    ImageButton menuSearch;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        originalList = new ArrayList<>();

        ListView listView = findViewById(R.id.reports_list);
        adapter = new ReportAdapter(this, arrayList);
        listView.setAdapter(adapter);

        //TODO load data from data set
        arrayList.add(new Report("The school needs to be improved", "XX Middle School", Priority.LOW, new User("james"), Category.EDUCATION, LocalDateTime.now()));
        arrayList.add(new Report("The water outage currently", "My home", Priority.HIGH, new User("john"), Category.WELLBEING, LocalDateTime.now()));
        arrayList.add(new Report("The rent fee is too high", "My home", Priority.MIDDLE, new User("nancy"), Category.GOVERNANCE, LocalDateTime.now()));
        //

        // Copy all original reports to originalList (so the list is populated when the activity is created)
        originalList.addAll(arrayList);

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

        // Handle SearchView functionality
        searchView = findViewById(R.id.search_view);
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

    // search reports using Tokenizer and Parser
    private void searchReports(String query) {
        if (query.isEmpty()) {
            arrayList.clear();
            arrayList.addAll(originalList);
        } else {

            List<String> tokens = Tokenizer.tokenize(query);
            List<Report> filteredReports = Parser.parse(tokens, originalList);

            arrayList.clear();
            arrayList.addAll(filteredReports);
        }

        adapter.notifyDataSetChanged();
    }
}
