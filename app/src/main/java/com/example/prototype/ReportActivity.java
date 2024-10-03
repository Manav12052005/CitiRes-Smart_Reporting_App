package com.example.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ReportActivity extends Activity {
    private Spinner spinnerLocation, spinnerCategory, spinnerPriority;
    private EditText editTextDescription, editTextTaskId;
    private Button buttonAddPicture, buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Initialize UI elements
        //spinnerLocation = findViewById(R.id.spinner_location);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerPriority = findViewById(R.id.spinner_priority);
        editTextDescription = findViewById(R.id.edittext_description);
        editTextTaskId = findViewById(R.id.edittext_task_id);
        //buttonAddPicture = findViewById(R.id.button_add_picture);
        buttonSubmit = findViewById(R.id.button_submit);

        // TODO: Set up Spinners with adapters and button actions

    }
}
