package com.example.sampleapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest {

    // 서버 URL 설정 (JSP)
    final static private String URL = "http://172.30.1.38:8081/test/Signup.jsp";
    private Map<String, String> map;


    public SignupRequest(String userEmail, String userPassword, String userPassword_chk, String userName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail",userEmail);
        map.put("userPassword", userPassword);
        map.put("userPassword_chk",userPassword_chk);
        map.put("userName",userName);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
