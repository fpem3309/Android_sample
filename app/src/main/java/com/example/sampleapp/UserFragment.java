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

    TextView tv_email,tv_password,tv_mood;
    Button btn_news;
    CircleProgressBar mood_circle, answer_circle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tv_email = view.findViewById(R.id.tv_email);
        tv_password = view.findViewById(R.id.tv_password);
        btn_news = view.findViewById(R.id.btn_news);

        mood_circle = view.findViewById(R.id.mood_circle);
        tv_mood = view.findViewById(R.id.tv_mood);

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

        Mood_Circle();
        Answer_circle();
        get_moodCnt();
        return view;
    }

    public void Mood_Circle(){
        int allAnswer = 100;
        int goodMood = 10;
        int per = (goodMood*100)/allAnswer;

        mood_circle.setProgress(per);
    }

    public void Answer_circle(){
        int allAnswer = 100;
        int clearAnswer = 64;
        int per = (clearAnswer*100)/allAnswer;

        answer_circle.setProgress(per);
    }

    public void get_moodCnt(){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    Log.d("mood",response);
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject obj = jsonArray.getJSONObject(0);
                    tv_mood.setText(obj.getString("userMood"));

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
