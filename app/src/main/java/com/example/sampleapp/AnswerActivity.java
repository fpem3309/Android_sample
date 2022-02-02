package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnswerActivity extends AppCompatActivity {
    ImageButton btn_del;
    ImageButton btn_add;
    TextView userBoard_no;
    TextView userBoard_subject;
    EditText userBoard_content;
    TextView userBoard_Ndate;


    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = simpleDate.format(mDate);

        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        userBoard_no = findViewById(R.id.userBoard_no);
        userBoard_subject = findViewById(R.id.userBoard_subject);
        userBoard_content = findViewById(R.id.userBoard_content);
        userBoard_Ndate = findViewById(R.id.userBoard_Ndate);

        Intent intent = getIntent();
        String no = intent.getStringExtra("userBoard_no");
        String subject = intent.getStringExtra("userBoard_subject");
        String content = intent.getStringExtra("userBoard_content").replaceAll("(\\\\n)", "\n");


        userBoard_no.setText(no);
        userBoard_subject.setText(subject);
        userBoard_content.setText(content);
        userBoard_Ndate.setText("n번째 질문"+getTime);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add_content = userBoard_content.getText().toString();
                String board_no = userBoard_no.getText().toString();
                Log.d("수정content",add_content+board_no);

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                                Log.d("response_content = " ,response);
                                finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                AnswerRequest answerRequest = new AnswerRequest(add_content, board_no, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AnswerActivity.this);
                queue.add(answerRequest);
            }

        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
