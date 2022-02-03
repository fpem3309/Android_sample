package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_login;
    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


        // Login 클릭 감지
        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = TextInputEditText_email.getText().toString();
                String userPassword = TextInputEditText_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            Log.d("login_response",response);
                            String res = response;
                            String res2 = "success";
                            Log.d("login_response2", String.valueOf(res.equals(res2)));
                            if(response.equals("success")){
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("userPassword", userPassword);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "로그인에 실패.", Toast.LENGTH_SHORT).show();
                                return;
                            }

//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//                            if (success) { // 로그인에 성공한 경우
//                                String userEmail = jsonObject.getString("userEmail");
//                                String userPassword = jsonObject.getString("userPassword");
//
//                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("userEmail", userEmail);
//                                intent.putExtra("userPassword", userPassword);
//                                startActivity(intent);
//
//                                Log.d("Success",userEmail+userPassword);
//                            } else { // 로그인에 실패한 경우
//                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
//                                Log.d("Failed",userEmail+userPassword);
//                                return;
//                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userEmail, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}