package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.dinuscxj.progressbar.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserFragment extends Fragment {

    TextView tv_email,tv_password,tv_mood, tv_allCnt;
    Button btn_news;
    CircleProgressBar answer_circle, good_circle, soso_circle, bad_circle;

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
        tv_allCnt = view.findViewById(R.id.tv_allCnt);
        answer_circle = view.findViewById(R.id.answer_circle);


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
                        Log.d("mood_obj", String.valueOf(obj));

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
                    tv_mood.setText(String.valueOf(jsonArray.length()));

                    Log.d("mood_cnt", String.valueOf(jsonArray.length())); // 총 개수

                    // Mood별 퍼센트 구하기
                    int allAnswer = jsonArray.length();

                    int good_per = (good_cnt*100)/allAnswer;
                    good_circle.setProgress(good_per);

                    int soso_per = (soso_cnt*100)/allAnswer;
                    soso_circle.setProgress(soso_per);

                    int bad_per = (bad_cnt*100)/allAnswer;
                    bad_circle.setProgress(bad_per);


                    int complete_per = (complete_cnt*100)/allAnswer;
                    answer_circle.setProgress(complete_per);
                    if(complete_per != 100) {
                        tv_allCnt.setText("아직 답변하지 않은 질문이" + (allAnswer - complete_cnt) + "개 남았어요~");
                    }else{
                        tv_allCnt.setText("답변하지 않은 질문이 없어요~");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        };

        MoodRequest moodRequest = new MoodRequest(responseListener);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(moodRequest);
    }


}
