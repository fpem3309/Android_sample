package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

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
    TextView userBoard_answerNo;

    RadioGroup Rad_mood;
    RadioButton rad_good, rad_soso, rad_bad;

    TextView rad_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        userBoard_no = findViewById(R.id.userBoard_no);
        userBoard_subject = findViewById(R.id.userBoard_subject);
        userBoard_content = findViewById(R.id.userBoard_content);
        userBoard_answerNo = findViewById(R.id.userBoard_answerNo);

        Rad_mood = findViewById(R.id.Rad_mood);

        rad_good = findViewById(R.id.rad_good);
        rad_soso = findViewById(R.id.rad_soso);
        rad_bad = findViewById(R.id.rad_bad);

        rad_result = findViewById(R.id.rad_result);


        Intent intent = getIntent();
        String no = intent.getStringExtra("userBoard_no");
        String subject = intent.getStringExtra("userBoard_subject");
        String content = intent.getStringExtra("userBoard_content").replaceAll("(\\\\n)", "\n");
        String mood = intent.getStringExtra("userBoard_mood");

        userBoard_no.setText(no);
        userBoard_subject.setText(subject);
        userBoard_content.setText(content);
        userBoard_answerNo.setText(no+"번째 질문");

        // radio checked
        Log.d("mood",mood);
        if(mood.equals("2")){
            rad_good.setChecked(true);
        }else if(mood.equals("1")){
            rad_soso.setChecked(true);
        }else if(mood.equals("0")){
            rad_bad.setChecked(true);
        }


        Rad_mood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rad_good:
                        rad_result.setText("2");
                        rad_good.setChecked(true);
                        break;
                    case R.id.rad_soso:
                        rad_result.setText("1");
                        rad_soso.setChecked(true);
                        break;
                    case R.id.rad_bad:
                        rad_result.setText("0");
                        rad_bad.setChecked(true);
                        break;
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add_content = userBoard_content.getText().toString();
                String board_no = userBoard_no.getText().toString();
                String add_mood = rad_result.getText().toString();
                Log.d("수정content",add_content+board_no);

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                                Log.d("response_content = " ,response);
                                finish();// BoardFragment의 onResume()함수가 호출, 데이터 새로고침
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                AnswerRequest answerRequest = new AnswerRequest(add_content, board_no, add_mood, responseListener);
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
