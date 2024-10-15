package com.example.prototype.report;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeleteReportReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int reportId = intent.getIntExtra("reportId", -1);

        if (reportId != -1) {
            // Implement your deletion logic here
            Toast.makeText(context, "Report deleted (ID: " + reportId + ")", Toast.LENGTH_SHORT).show();
        }
    }
}
