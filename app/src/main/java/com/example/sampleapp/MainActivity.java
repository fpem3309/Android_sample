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

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_login;
    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    String emailOK = "123";
    String passwordOK = "456";

    String inputEmail = "";
    String inputPassword ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);

        // 이메일,비밀번호 검사
        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null){
                    Log.d("SENTI","email"+charSequence.toString());
                    inputEmail = charSequence.toString();
                    RelativeLayout_login.setEnabled(validation());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null){
                    Log.d("SENTI2","password"+charSequence.toString());
                    inputPassword = charSequence.toString();
                    RelativeLayout_login.setEnabled(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Login 클릭 감지
        //RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 값 가져오기
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                // 값 넘기기
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

    }
    public boolean validation() {
        Log.d("SENTI",inputEmail + " / " + inputPassword + " / " + inputEmail.equals(emailOK) + " / " + inputPassword.equals(passwordOK));
        return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK);
    }
}