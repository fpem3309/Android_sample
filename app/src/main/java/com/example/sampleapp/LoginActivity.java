package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {

    BottomNavigationView nv;
    UserFragment fragment_user;
    BoardFragment fragment_board;
    SetFragment fragment_set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragment_user = new UserFragment();
        fragment_board = new BoardFragment();
        fragment_set = new SetFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_user).commit();

        nv = findViewById(R.id.bottomNavigationView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String userEmail = bundle.getString("userEmail");
        String userPassword = bundle.getString("userPassword");


        bundle.putString("email",userEmail);
        bundle.putString("password",userPassword);
        fragment_user.setArguments(bundle);

        nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.user_tab:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_user).commit();

                    break;

                    case R.id.board_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_board).commit();
                        bundle.putString("email",userEmail);
                        fragment_board.setArguments(bundle);
                        break;
                    case R.id.set_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment_set).commit();
                        bundle.putString("email",userEmail);
                        fragment_set.setArguments(bundle);
                        break;
                }
                return true; // true 해야 색상 변경됨..?
            }
        });
    }
}
