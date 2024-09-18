package com.example.prototype;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Report> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        arrayList.add(new Report("Traffic Lights Malfunctioning at Major Intersection", "They remain stuck on red, causing significant traffic congestion ", "Main st"));
        arrayList.add(new Report("Streetlight Outage", "Several streetlights have been out for over two weeks, causing a lot of trouble.", "on campus"));
        arrayList.add(new Report("Overgrown Vegetation Obstructing Sidewalk", "It is obstructing the pathway and making it difficult for pedestrians, especially those with mobility issues, to pass through.", "East Park"));

        ListView listView = findViewById(R.id.reports_list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
    }
}