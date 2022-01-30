package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    TextView tv_email;
    TextView tv_password;
    Button btn_news;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tv_email = view.findViewById(R.id.tv_email);
        tv_password = view.findViewById(R.id.tv_password);
        btn_news = view.findViewById(R.id.btn_news);


        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        tv_email.setText(email);
        tv_password.setText(password);

        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                Toast.makeText(getActivity().getApplicationContext(),"News를 보자",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        return view;
    }
}
