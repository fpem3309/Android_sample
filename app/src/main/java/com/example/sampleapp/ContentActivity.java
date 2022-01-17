package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {
    TextView tv_AllContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        tv_AllContent = findViewById(R.id.tv_AllContent);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String AllContent = bundle.getString("AllContent");
        tv_AllContent.setText(AllContent);

    }


}
