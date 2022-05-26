package com.example.habitimia.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.habitimia.R;
import com.example.habitimia.data.model.Avatar;
import com.example.habitimia.data.model.User;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.util.Server;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Email;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button loginButton = findViewById(R.id.login_btn);
        final Button registerButton = findViewById(R.id.register_btn);

        loginButton.setOnClickListener(view -> {
            finish(); // Go back to login
        });
        Username = (EditText) findViewById(R.id.register_username);
        Email = (EditText) findViewById(R.id.register_email);
        Password = (EditText) findViewById(R.id.register_password);
        String username = Username.getText().toString();

        registerButton.setOnClickListener(view -> {
            User user = Server.register(Username.getText().toString(),
                            Email.getText().toString(),
                            Password.getText().toString(),
                            Avatar.MAGICIAN.toString());
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.putExtra("user",  user);
            startActivity(intent);
            finish();
        });

    }
}