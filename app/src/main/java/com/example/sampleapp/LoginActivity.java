package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    BottomNavigationView nv;
    UserFragment fragment_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragment_user = new UserFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_user).commit();

        nv = findViewById(R.id.bottomNavigationView);

        //받아온 user 정보
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        //fragment로 보낼 user 정보
        bundle.putString("email",email);
        bundle.putString("password",password);
        fragment_user.setArguments(bundle);
    }
}
