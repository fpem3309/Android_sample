package com.example.sampleapp;

import android.content.Intent;
import android.net.Uri;
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

public class SetFragment extends Fragment {

    Button go_question;
    TextView tv_name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_set, container, false);
        go_question = view.findViewById(R.id.go_question);
        tv_name = view.findViewById(R.id.tv_name);


        go_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fpem3309"));
                startActivity(intent);
            }
        });

        Bundle extras = getArguments();
        tv_name.setText(extras.getString("email")+"님 오늘 하루는 어떠셨나요?");

        return view;
    }
}
