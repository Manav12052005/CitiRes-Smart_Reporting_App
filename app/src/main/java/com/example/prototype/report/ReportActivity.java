    package com.example.prototype.report;

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Toast;
    import android.Manifest;
    import android.content.pm.PackageManager;
    import android.location.Location;

    import androidx.annotation.NonNull;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;

    import java.io.IOException;

    import com.example.prototype.R;
    import com.example.prototype.entity.Category;
    import com.example.prototype.entity.Priority;
    import com.example.prototype.entity.Report;
    import com.example.prototype.entity.User;
    import com.example.prototype.util.BaseActivity;
    import com.google.android.gms.location.FusedLocationProviderClient;
    import com.google.android.gms.location.LocationServices;
    import com.google.android.gms.tasks.OnSuccessListener;

    import android.location.Address;
    import android.location.Geocoder;

    import java.util.List;
    import java.util.Locale;

    import java.time.LocalDateTime;
    import android.app.AlertDialog;
    import android.content.DialogInterface;
    import android.widget.TimePicker;
    import android.app.TimePickerDialog;
    import java.util.Calendar;

    import android.app.AlarmManager;
    import android.app.PendingIntent;

    public class ReportActivity extends BaseActivity {
        private Spinner spinnerCategory, spinnerPriority;
        private EditText editTextDescription;
        private Button buttonSubmit;
        private FusedLocationProviderClient fusedLocationClient;
        private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
        double latitude;
        double longitude;
        String username;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setChildContentView(R.layout.activity_report2);

            // Retrieve the username from intent extras
            username = getIntent().getStringExtra("USER");
            Toast.makeText(this, "Current User: " + username, Toast.LENGTH_SHORT).show();
            // Initialize UI elements
            //spinnerLocation = findViewById(R.id.spinner_location);
            spinnerCategory = findViewById(R.id.spinner_category);
            spinnerPriority = findViewById(R.id.spinner_priority);
            editTextDescription = findViewById(R.id.edittext_description);
            //editTextTaskId = findViewById(R.id.edittext_task_id);
            //editTextPicture = findViewById(R.id.edittext_picture_link);
            buttonSubmit = findViewById(R.id.button_submit);
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            // spinner for categories
            Category[] categories = Category.values();
            ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategory.setAdapter(categoryArrayAdapter);
            spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Get the selected enum value
                    Category selectedCategory = (Category) parentView.getItemAtPosition(position);
                    // Perform the action with selectedCategory
                    Toast.makeText(getApplicationContext(), "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Handle case where nothing is selected
                }
            });

            // spinner for priorities
            Priority[] priorities = Priority.values();
            ArrayAdapter<Priority> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priorities);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPriority.setAdapter(adapter);

            spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Get the selected enum value
                    Priority selectedPriority = (Priority) parentView.getItemAtPosition(position);
                    // Perform the action with selectedPriority
                    Toast.makeText(getApplicationContext(), "Selected Priority: " + selectedPriority, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Handle case where nothing is selected
                }
            });

            //checkLocationPermissionAndSubmit();

            // for getting text from the inout boxes after submit button is clicked
            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an AlertDialog builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);

                    // Set title and message for the popup
                    builder.setTitle("Submit Report")
                            .setMessage("Do you want to submit this report now or schedule it for later?")
                            .setCancelable(true)
                            // Set 'Submit Now' button
                            .setPositiveButton("Submit Now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Immediate submit action
                                    checkLocationPermissionAndSubmit();  // Your existing method for submitting now
                                    dialog.dismiss();
                                }
                            })
                            // Set 'Schedule Later' button
                            .setNegativeButton("Let me think..", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Show time picker dialog to schedule the submission
                                    dialog.dismiss();

                                }
                            });
                    // Create and show the dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }

        private void checkLocationPermissionAndSubmit() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            } else {
                getLocationAndSubmitReport();
            }
        }

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocationAndSubmitReport();
                } else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void getLocationAndSubmitReport() {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            submitReport();
                        } else {
                            Toast.makeText(getApplicationContext(), "Unable to retrieve location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        private void submitReport() {
            String description = editTextDescription.getText().toString();
            Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
            Priority selectedPriority = (Priority) spinnerPriority.getSelectedItem();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assert addresses != null;
            Address address = addresses.get(0);
            String locationName = address.getAddressLine(0);
            String location = address.toString();

            if (description.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Report newReport = constructReport
                        (description, locationName, selectedCategory, selectedPriority);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putSerializable("added_report", newReport);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);

                Toast.makeText(getApplicationContext(), "Task saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        private Report constructReport(String description, String locationName, Category selectedCategory, Priority selectedPriority) {
            Report newReport = new Report();
            newReport.setDescription(description);
            newReport.setUser(new User(username));
            newReport.setLocation(locationName);
            newReport.setCategory(selectedCategory);
            newReport.setPriority(selectedPriority);
            newReport.setReportId(ReportCounter.getReportId());
            newReport.setLocalDateTime(LocalDateTime.now());
            ReportCounter.inc();
            return newReport;
        }
    }
