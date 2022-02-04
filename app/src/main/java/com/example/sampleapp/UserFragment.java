package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

public class UserFragment extends Fragment {

    TextView tv_email;
    TextView tv_password;
    Button btn_news;
    RelativeLayout recent_answer;
    private List<BoardData> boardDataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tv_email = view.findViewById(R.id.tv_email);
        tv_password = view.findViewById(R.id.tv_password);
        btn_news = view.findViewById(R.id.btn_news);
        recent_answer = view.findViewById(R.id.recent_answer);

        Bundle bundle = getArguments();


        tv_email.setText(bundle.getString("email"));
        tv_password.setText(bundle.getString("password"));

        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                Toast.makeText(getActivity().getApplicationContext(),"News를 보자",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        recent_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"최근 게시물",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), AnswerActivity.class);
//                startActivity(intent);
            }
        });

        return view;
    }
}
