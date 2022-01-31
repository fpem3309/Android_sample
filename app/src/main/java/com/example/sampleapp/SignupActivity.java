package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_Signup;
    TextInputEditText TextInputEditText_email, TextInputEditText_password, TextInputEditText_password_chk,
    TextInputEditText_name, TextInputEditText_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_Signup = findViewById(R.id.RelativeLayout_Signup);
        TextInputEditText_password_chk = findViewById(R.id.TextInputEditText_password_chk);
        TextInputEditText_name = findViewById(R.id.TextInputEditText_name);
        TextInputEditText_birth = findViewById(R.id.TextInputEditText_birth);

        // Login 클릭 감지
        RelativeLayout_Signup.setClickable(true);
        RelativeLayout_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = TextInputEditText_email.getText().toString();
                String userPassword = TextInputEditText_password.getText().toString();
                String userPassword_chk = TextInputEditText_password_chk.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            if(userPassword != userPassword_chk){
                                Toast.makeText(getApplicationContext(),"비번이 다릅니당.",Toast.LENGTH_SHORT).show();
                            }else {

                                System.out.println("Response = " + response);

                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "가입 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignupRequest signupRequest = new SignupRequest(userEmail, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(signupRequest);
            }
        });

    }
}