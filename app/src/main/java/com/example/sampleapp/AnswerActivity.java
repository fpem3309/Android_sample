package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sampleapp.databinding.ActivityAnswerBinding;

public class AnswerActivity extends AppCompatActivity {
    ImageButton btn_add, btn_close;
    TextView userBoard_no, userBoard_subject, userBoard_answerNo;
    EditText userBoard_content;

    RadioGroup Rad_mood;
    RadioButton rad_good, rad_soso, rad_bad;

    TextView rad_result;
    private ActivityAnswerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        binding = ActivityAnswerBinding.inflate(getLayoutInflater());

//        btn_add = findViewById(R.id.btn_add);
//        btn_close = findViewById(R.id.btn_close);
//        userBoard_no = findViewById(R.id.userBoard_no);
//        userBoard_subject = findViewById(R.id.userBoard_subject);
//        userBoard_content = findViewById(R.id.userBoard_content);
//        userBoard_answerNo = findViewById(R.id.userBoard_answerNo);
//
//        Rad_mood = findViewById(R.id.Rad_mood);
//
//        rad_good = findViewById(R.id.rad_good);
//        rad_soso = findViewById(R.id.rad_soso);
//        rad_bad = findViewById(R.id.rad_bad);
//
//        rad_result = findViewById(R.id.rad_result);


        Intent intent = getIntent();
        String no = intent.getStringExtra("userBoard_no");
        String subject = intent.getStringExtra("userBoard_subject");
        String content = intent.getStringExtra("userBoard_content").replaceAll("(\\\\n)", "\n");
        if(content.equals("아직 답변을 안했어요") || content.equals("null")){
            content = "";
        }
        String mood = intent.getStringExtra("userBoard_mood");
        String date = intent.getStringExtra("userBoard_date");

        userBoard_no.setText(no);
        userBoard_subject.setText(subject);
        userBoard_content.setText(content);
        userBoard_answerNo.setText("# "+no+"th\t질문\t"+date);

        // radio checked
        Log.d("mood",mood);
        if(mood.equals("\uD83D\uDE04")||mood.equals("2")){
            rad_good.setChecked(true);
            rad_result.setText("2");
        }else if(mood.equals("\uD83D\uDE10")||mood.equals("1")){
            rad_soso.setChecked(true);
            rad_result.setText("1");
        }else if(mood.equals("\uD83D\uDE21")||mood.equals("0")){
            rad_bad.setChecked(true);
            rad_result.setText("0");
        }


        Rad_mood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rad_good:
                        rad_result.setText("2");
                        break;
                    case R.id.rad_soso:
                        rad_result.setText("1");
                        break;
                    case R.id.rad_bad:
                        rad_result.setText("0");
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

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
