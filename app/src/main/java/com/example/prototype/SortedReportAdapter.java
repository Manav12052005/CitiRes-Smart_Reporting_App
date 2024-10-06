package com.example.prototype;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class SortedReportAdapter extends ArrayAdapter<Report> {
    private Context context;
    private List<Report> reports;

    public SortedReportAdapter(Context context, List<Report> reports) {
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

        Report report = reports.get(position);

        TextView description = listItem.findViewById(R.id.description);
        description.setText(report.getDescription());

        TextView location = listItem.findViewById(R.id.location);
        location.setText(report.getLocation());

        ImageView locationIcon = listItem.findViewById(R.id.location_icon);

        Button deleteButton = listItem.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reports.remove(report);
                notifyDataSetChanged();
            }
        });

        TextView priority = listItem.findViewById(R.id.priority);
        setPriorityBackground(priority, report.getPriority());

        TextView category = listItem.findViewById(R.id.category);
        category.setText(report.getCategory().toString());

        TextView user = listItem.findViewById(R.id.user);
        user.setText("Reported by: " + report.getUser().getName() + " at " + report.getLocalDateTime());

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
