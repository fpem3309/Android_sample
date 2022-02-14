package com.example.sampleapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.dinuscxj.progressbar.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserFragment extends Fragment {

    TextView tv_email,tv_password,tv_mood, tv_allCnt, tv_goodMood ,tv_sosoMood, tv_badMood, tv_recentTitle, tv_recentDate, tv_recentAnswer;
    Button btn_news;
    CircleProgressBar answer_circle, good_circle, soso_circle, bad_circle;
    ConstraintLayout lay_recentBoard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        tv_email = view.findViewById(R.id.tv_email);
        tv_password = view.findViewById(R.id.tv_password);
        btn_news = view.findViewById(R.id.btn_news);

        good_circle = view.findViewById(R.id.good_circle);
        soso_circle = view.findViewById(R.id.soso_circle);
        bad_circle = view.findViewById(R.id.bad_circle);

        tv_mood = view.findViewById(R.id.tv_mood);
        tv_goodMood = view.findViewById(R.id.tv_goodMood);
        tv_sosoMood = view.findViewById(R.id.tv_sosoMood);
        tv_badMood = view.findViewById(R.id.tv_badMood);
        tv_allCnt = view.findViewById(R.id.tv_allCnt);
        tv_recentTitle = view.findViewById(R.id.tv_recentTitle);
        tv_recentDate = view.findViewById(R.id.tv_recentDate);
        tv_recentAnswer = view.findViewById(R.id.tv_recentAnswer);
        answer_circle = view.findViewById(R.id.answer_circle);

        lay_recentBoard = view.findViewById(R.id.lay_recentBoard);


        Bundle bundle = getArguments();
        tv_email.setText(bundle.getString("email"));
        tv_password.setText(bundle.getString("password"));


        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                Toast.makeText(getActivity().getApplicationContext(), "News를 보자", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        get_recentAnswer();
        get_moodCnt();
        return view;
    }

    public void get_moodCnt(){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int complete_cnt = 0;
                int good_cnt = 0;
                int soso_cnt = 0;
                int bad_cnt = 0;

                try {
                    Log.d("mood", response);

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        if (obj.getString("userMood").equals("0")) {
                            bad_cnt = bad_cnt+1;
                        }
                        if (obj.getString("userMood").equals("1")) {
                            soso_cnt = soso_cnt+1;
                        }
                        if (obj.getString("userMood").equals("2")) {
                            good_cnt = good_cnt+1;
                        }
                        if(!obj.getString("userMood").equals("null")){
                            complete_cnt = complete_cnt+1;
                        }
                }

                    Log.d("bad_cnt", String.valueOf(bad_cnt));
                    Log.d("soso_cnt", String.valueOf(soso_cnt));
                    Log.d("good_cnt", String.valueOf(good_cnt));
                    tv_mood.setText("내 답변 "+complete_cnt+" / "+jsonArray.length()+"개");

                    Log.d("mood_cnt", String.valueOf(jsonArray.length())); // 총 개수

                    // Mood별 퍼센트 구하기
                    int allAnswer = jsonArray.length();

                    tv_recentAnswer.setText(allAnswer+"th 질문");

                    int good_per = (good_cnt*100)/allAnswer;
                    good_circle.setProgress(good_per);
                    tv_goodMood.setText("좋음 "+good_cnt+"개");

                    int soso_per = (soso_cnt*100)/allAnswer;
                    soso_circle.setProgress(soso_per);
                    tv_sosoMood.setText("보통 "+soso_cnt+"개");

                    int bad_per = (bad_cnt*100)/allAnswer;
                    bad_circle.setProgress(bad_per);
                    tv_badMood.setText("나쁨 "+bad_cnt+"개");


                    int complete_per = (complete_cnt*100)/allAnswer;
                    answer_circle.setProgress(complete_per);
                    if(complete_per != 100) {
                        tv_allCnt.setText("답변하지 않은 질문이 " + (allAnswer - complete_cnt) + "개 남았어요");
                    }else{
                        tv_allCnt.setText("답변하지 않은 질문이 없어요");
                        tv_allCnt.setTextColor(Color.parseColor("#FF00D8FF"));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        };
        Bundle bundle = getArguments();
        String userEmail = bundle.getString("email");
        BoardRequest boardRequest = new BoardRequest(userEmail, responseListener);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(boardRequest);
    }

    String recent_title="";
    String recent_date="";
    String recent_boardNo="";
    String recent_boardContent="";
    String recent_boardMood="";

    public void get_recentAnswer(){

        Response.Listener<String>listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("mood2", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("mood3", String.valueOf(jsonArray));

                    JSONObject obj = jsonArray.getJSONObject(0);
                    Log.d("mood4", String.valueOf(obj));

                    recent_title =  obj.getString("board_title");
                    recent_date = obj.getString("board_date").substring(0, obj.getString("board_date").indexOf(" "));
                    recent_boardNo = obj.getString("board_no");
                    recent_boardContent = obj.getString("board_content");
                    recent_boardMood = obj.getString("userMood");


                }catch (JSONException e){
                    e.printStackTrace();
                }
                // 최근 게시물 하나만 setText
                tv_recentTitle.setText(recent_title);
                tv_recentDate.setText(recent_date);

                lay_recentBoard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),AnswerActivity.class);
                        intent.putExtra("userBoard_no", recent_boardNo);
                        intent.putExtra("userBoard_subject", recent_title);
                        intent.putExtra("userBoard_content", recent_boardContent);
                        intent.putExtra("userBoard_mood", recent_boardMood);
                        intent.putExtra("userBoard_date",recent_date);
                        view.getContext().startActivity(intent);
                    }
                });

            }
        };
        MoodRequest moodRequest = new MoodRequest(listener);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(moodRequest);
    }


}
