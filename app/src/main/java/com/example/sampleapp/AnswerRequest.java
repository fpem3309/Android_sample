package com.example.sampleapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AnswerRequest extends StringRequest {
    // 서버 URL 설정 (JSP)
    final static private String URL = "http://172.30.1.38:8081/test/Board_addContent.jsp";
    private Map<String, String> map;


    public AnswerRequest(String add_content, String board_no, String add_mood, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("add_content",add_content);
        map.put("board_no",board_no);
        map.put("add_mood",add_mood);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
