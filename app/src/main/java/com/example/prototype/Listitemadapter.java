package com.example.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Listitemadapter extends ArrayAdapter<Report> {
    private final Context context;
    private final List<Report> reports;

    public Listitemadapter(Context context, List<Report> reports) {
        super(context, R.layout.list_item_report, reports);
        this.context = context;
        this.reports = reports;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_report, parent, false);
        }

        // Get the report item
        Report report = reports.get(position);

        // Set up your views here
        TextView reportTitle = convertView.findViewById(R.id.report_title);
        reportTitle.setText(report.getDescription()); // Displaying report description

        // Add other TextViews for priority, category, address, etc.
        TextView reportPriority = convertView.findViewById(R.id.report_priority);
        reportPriority.setText("Priority: " + report.getPriority()); // Assuming getPriority() method exists

        TextView reportCategory = convertView.findViewById(R.id.report_category);
        reportCategory.setText("Category: " + report.getCategory()); // Assuming getCategory() method exists

        TextView reportAddress = convertView.findViewById(R.id.report_address);
        reportAddress.setText("Address: " + report.getLocation()); // Assuming getAddress() method exists


        // Set up like button
        ImageButton likeButton = convertView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the like action
                Toast.makeText(context, "Liked: " + report.getDescription(), Toast.LENGTH_SHORT).show();
                // Here, you can update the report object to reflect the liked state
                // report.setLiked(true);  // You may want to implement this in your Report class
            }
        });

        return convertView;
    }
}
