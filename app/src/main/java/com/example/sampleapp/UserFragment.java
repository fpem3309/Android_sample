package com.example.sampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    TextView tv_email;
    TextView tv_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tv_email = view.findViewById(R.id.tv_email);
        tv_password = view.findViewById(R.id.tv_password);


        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        tv_email.setText(email);
        tv_password.setText(password);

        return view;
    }
}
