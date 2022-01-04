package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
TextView get_email;
TextView get_password;
Button go_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        get_email = findViewById(R.id.get_email);
        get_password = findViewById(R.id.get_password);
        go_news = findViewById(R.id.go_news);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        get_email.setText(email);
        get_password.setText(password);
    }
}
