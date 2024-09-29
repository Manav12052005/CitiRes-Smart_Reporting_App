package com.example.prototype;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class ReportAdapter extends ArrayAdapter<Report> {
    private Context context;
    private List<Report> reports;
    TextView description;
    TextView location;
    TextView priority;
    TextView category;
    TextView user;


    public ReportAdapter(Context context, List<Report> reports) {
        super(context, 0, reports);
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.report, parent, false);
        }

        Report currentReport = reports.get(position);

        description = (TextView) listItem.findViewById(R.id.description);
        description.setText(currentReport.getDescription());

        location = (TextView) listItem.findViewById(R.id.location);
        location.setText(currentReport.getLocation());

        priority = (TextView) listItem.findViewById(R.id.priority);
        setPriorityBackground(priority, currentReport.getPriority());

        category = (TextView) listItem.findViewById(R.id.category);
        category.setText(currentReport.getCategory().toString());

        user = (TextView) listItem.findViewById(R.id.user);
        user.setText("Reported by: " + currentReport.getUser().getName() + " at " + currentReport.getLocalDateTime());

        return listItem;
    }

    private void setPriorityBackground(TextView priorityView, Priority priority) {
        int color;
        String priorityText;

        switch (priority) {
            case LOW:
                color = ContextCompat.getColor(context, R.color.priority_low);
                priorityText = "Low";
                break;
            case MIDDLE:
                color = ContextCompat.getColor(context, R.color.priority_middle);
                priorityText = "MIDDLE";
                break;
            case HIGH:
                color = ContextCompat.getColor(context, R.color.priority_high);
                priorityText = "High";
                break;
            default:
                color = Color.parseColor("#4CAF50"); // Default to Green
                priorityText = "Unknown";
        }
        priorityView.setText(priorityText);
        priorityView.setBackgroundColor(color);
    }
}