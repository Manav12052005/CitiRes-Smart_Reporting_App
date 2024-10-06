package com.example.prototype;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends ArrayAdapter<Report> {
    private Context context;
    private AVLTree<Report> reports;
    TextView description;
    TextView location;
    TextView priority;
    TextView category;
    TextView user;
    ImageView locationIcon;

    public ReportAdapter(Context context, AVLTree<Report> reports, List<Report> list) {
        super(context, 0, list);
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

        Report report = new ArrayList<>(reports.fromLargeToSmall()).get(position);

        description = (TextView) listItem.findViewById(R.id.description);
        description.setText(report.getDescription());

        location = (TextView) listItem.findViewById(R.id.location);
        location.setText(report.getLocation());

        locationIcon = listItem.findViewById(R.id.location_icon);

        Button deleteButton = listItem.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reports.remove(report.getReportId());
                clear();
                addAll(new ArrayList<>(reports.fromLargeToSmall()));
                notifyDataSetChanged();
            }
        });

        priority = (TextView) listItem.findViewById(R.id.priority);
        setPriorityBackground(priority, report.getPriority());

        category = (TextView) listItem.findViewById(R.id.category);
        category.setText(report.getCategory().toString());

        user = (TextView) listItem.findViewById(R.id.user);
        user.setText("Reported by: " + report.getUser() + " at " + report.getLocalDateTime());

        TextView user = listItem.findViewById(R.id.user);
        user.setText("Reported by: " + report.getUser().getName()); // Assuming User has a getName() method

        TextView likeCountTextView = listItem.findViewById(R.id.like_count_text_view);
        likeCountTextView.setText(String.valueOf(report.getLikes())); // Set initial likes count

        ImageButton likeButton = listItem.findViewById(R.id.like_button);

        // Initial state based on current likes
        if (report.getLikes() > 0) {
            likeButton.setImageResource(R.drawable.liked);
        } else {
            likeButton.setImageResource(R.drawable.unliked);
        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            private boolean isLiked = report.getLikes() > 0; // Track the current like state

            @Override
            public void onClick(View v) {
                if (isLiked) {
                    report.unlike(); // Decrement likes
                    likeButton.setImageResource(R.drawable.unliked); // Change icon to unliked
                } else {
                    report.like(); // Increment likes
                    likeButton.setImageResource(R.drawable.liked); // Change icon to liked
                }
                updateLikeCountDisplay(likeCountTextView, report); // Update displayed like count
                isLiked = !isLiked; // Toggle the like state
            }
        });


        return listItem;
    }
    private void updateLikeCountDisplay(TextView likeCountTextView, Report report) {
        likeCountTextView.setText(String.valueOf(report.getLikes())); // Update TextView with new likes count
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