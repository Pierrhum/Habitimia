package com.example.habitimia.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.habitimia.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button loginButton = findViewById(R.id.login_btn);
        final Button registerButton = findViewById(R.id.register_btn);

        loginButton.setOnClickListener(view -> {
            finish(); // Go back to login
        });

        registerButton.setOnClickListener(view -> {
            Toast.makeText(this, "TODO : Register", Toast.LENGTH_SHORT).show();
        });

    }
}