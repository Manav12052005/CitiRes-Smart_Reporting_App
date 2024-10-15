package com.example.prototype.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototype.report.MainActivity;
import com.example.prototype.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        // Set onClickListener for the login button
        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Use the Singleton AuthenticationManager for login
            if (Authenticator.getInstance(LoginActivity.this).login(username, password)) {
                // Store the current user in UserSession
                UserSession.setCurrentUser(username);

                // Login successful, navigate to the MainActivity (dashboard)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USER", username);
                startActivity(intent);
                finish(); // Close the login activity
            } else {
                // Login failed, show an error message
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
