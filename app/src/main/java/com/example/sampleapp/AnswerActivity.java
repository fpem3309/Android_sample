package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {
    ImageButton btn_del;
    TextView userBoard_subject;
    EditText userBoard_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        btn_del = findViewById(R.id.btn_del);
        userBoard_subject = findViewById(R.id.userBoard_subject);
        userBoard_content = findViewById(R.id.userBoard_content);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("userBoard_subject");
        String content = intent.getStringExtra("userBoard_content");

        userBoard_subject.setText(subject);
        userBoard_content.setText(content);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
