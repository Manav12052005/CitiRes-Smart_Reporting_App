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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import java.util.Calendar;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;

public class ReportAdapter extends ArrayAdapter<Report> {
    private Context context;
    private List<Report> reports;
    private OnClickPassData listener;

    public ReportAdapter(Context context, List<Report> reports, OnClickPassData listener) {
        super(context, 0, reports);
        this.context = context;
        this.reports = reports;
        this.listener = listener;
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

        TextView id = listItem.findViewById(R.id.id_text);
        id.setText("id: " + report.getReportId());

        Button deleteButton = listItem.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // Set title and message for the popup
                builder.setTitle("Delete Report")
                        .setMessage("Do you want to delete this report now or schedule it for later?")
                        .setCancelable(true)
                        // Set 'Delete Now' button
                        .setPositiveButton("Delete Now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Immediate delete action
                                int reportId = report.getReportId();
                                deleteReport(reportId);
                                dialog.dismiss();
                            }
                        })
                        // Set 'Schedule Later' button
                        .setNegativeButton("Schedule Later", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Show time picker dialog to schedule the deletion
                                showTimePickerDialog(report);
                                dialog.dismiss();
                            }
                        });

                // Create and show the dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Rest of the code related to priority, category, etc.
        TextView priority = listItem.findViewById(R.id.priority);
        setPriorityBackground(priority, report.getPriority());

        TextView category = listItem.findViewById(R.id.category);
        category.setText(report.getCategory().toString());

        TextView user = listItem.findViewById(R.id.user);
        user.setText("Reported by: " + report.getUser().getName() + " at " + report.getLocalDateTime());

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

    // Delete a report immediately
    private void deleteReport(int reportId) {
        for (Report r : reports) {
            if (reportId == r.getReportId()) {
                reports.remove(r);
                notifyDataSetChanged();
                listener.onClickPassData(reportId);
                break;
            }
        }
    }

    // Schedule the deletion using AlarmManager
    private void scheduleDeletion(Report report, long scheduledTimeInMillis) {
        // Create an Intent to trigger a broadcast receiver for deletion
        Intent intent = new Intent(context, DeleteReportReceiver.class);
        intent.putExtra("reportId", report.getReportId()); // Pass the report ID

        // Create a PendingIntent for the AlarmManager
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, report.getReportId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Schedule the deletion at the selected time
        if (alarmManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                // Set exact alarm for API 23 and above
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, scheduledTimeInMillis, pendingIntent);
            } else {
                // Set exact alarm for lower API levels
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, scheduledTimeInMillis, pendingIntent);
            }
        }
    }

    // Show the time picker to schedule deletion
    private void showTimePickerDialog(Report report) {
        // Get the current time
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        // Create a TimePickerDialog to pick a time
        TimePickerDialog timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                // Get the time for deletion
                Calendar deleteTime = Calendar.getInstance();
                deleteTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                deleteTime.set(Calendar.MINUTE, selectedMinute);
                deleteTime.set(Calendar.SECOND, 0);

                // Schedule the deletion
                scheduleDeletion(report, deleteTime.getTimeInMillis());
            }
        }, hour, minute, true); // Use 24-hour time format
        timePicker.setTitle("Select Time to Delete");
        timePicker.show();
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
