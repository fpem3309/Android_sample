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
//    String emailOK = "em";
//    String passwordOK = "pa";
//
//    String inputEmail = "";
//    String inputPassword ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);

//        // 이메일,비밀번호 검사
//        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence != null){
//                    Log.d("SENTI","email"+charSequence.toString());
//                    inputEmail = charSequence.toString();
//                    RelativeLayout_login.setEnabled(validation());
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

//        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence != null){
//                    Log.d("SENTI2","password"+charSequence.toString());
//                    inputPassword = charSequence.toString();
//                    RelativeLayout_login.setEnabled(validation());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

//        // Login 클릭 감지
//        //RelativeLayout_login.setClickable(true);
//        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // 값 가져오기
//                String email = TextInputEditText_email.getText().toString();
//                String password = TextInputEditText_password.getText().toString();
//
//                // 값 넘기기
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                intent.putExtra("email",email);
//                intent.putExtra("password",password);
//                startActivity(intent);
//            }
//        });

        // Login 클릭 감지
        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 값 가져오기
//                String email = TextInputEditText_email.getText().toString();
//                String password = TextInputEditText_password.getText().toString();

                String userEmail = TextInputEditText_email.getText().toString();
                String userPassword = TextInputEditText_password.getText().toString();

                //여기부터
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가

                            System.out.println("aaa" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String userEmail = jsonObject.getString("userEmail");
                                String userPassword = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("userPassword", userPassword);
                                startActivity(intent);

                                Log.d("SSS",userEmail+userPassword);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                Log.d("FFF",userEmail+userPassword);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userEmail, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

        // 여기까지 복붙

    }
//    public boolean validation() {
//        Log.d("SENTI",inputEmail + " / " + inputPassword + " / " + inputEmail.equals(emailOK) + " / " + inputPassword.equals(passwordOK));
//        return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK);
//    }
}