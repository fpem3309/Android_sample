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

import org.json.JSONArray;
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
                            Log.d("Response", response);
                            JSONObject obj = new JSONObject(response);
                            int success = obj.getInt("sign");
                            Log.d("Response2", String.valueOf(success));
                            if(success == -1){
                                Toast.makeText(getApplicationContext(),"이미 있는 이메일입니다..",Toast.LENGTH_SHORT).show();
                                TextInputEditText_email.requestFocus();
                                return;
                            }

                            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                                Toast.makeText(getApplicationContext(),"올바른 이메일 형식이 아닙니다.",Toast.LENGTH_SHORT).show();
                                TextInputEditText_email.requestFocus();
                                return;
                            }
                            if(userPassword.equals("")){
                                Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                                TextInputEditText_password.requestFocus();
                                return;
                            }
                            if(userPassword_chk.equals("")){
                                Toast.makeText(getApplicationContext(),"비밀번호 확인을 입력하세요.",Toast.LENGTH_SHORT).show();
                                TextInputEditText_password_chk.requestFocus();
                                return;
                            }
                            if(!userPassword.equals(userPassword_chk)){
                                Toast.makeText(getApplicationContext(),"비밀번호를 확인하세요.",Toast.LENGTH_SHORT).show();
                                TextInputEditText_password_chk.requestFocus();
                                return;
                            }
                            if((success == 1) && (userPassword.equals(userPassword_chk)) ){

                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "회원가입을 성공하였습니다.", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getApplicationContext(),"회원가입을 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                    SignupRequest signupRequest = new SignupRequest(userEmail, userPassword, userPassword_chk, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                    queue.add(signupRequest);
            }
        });

    }
}