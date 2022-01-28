package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<BoardData> boardDataList;

    BoardData boardData;
    String url = "http://172.30.1.38:8081/test/Board.jsp";
    RequestQueue requestQueue;  // 서버와 통신할 통로

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        //리사이클러뷰의 레이아웃 매니저 설정
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getContext());


        getBoard();
        return view;
        }

    public void getBoard() {

        String url = "http://172.30.1.38:8081/test/Board.jsp";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("board",response);

                        try {

                            JSONObject jsonObj = new JSONObject(response);

                            //response -> NewsData에 분류
                            List<BoardData> boards = new ArrayList<>();

                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","error!!!!!!!");
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError{
                Map params = new HashMap();
                params.put("User-Agent", "Mozilla/5.0");

                return params;
            }
        };


        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

    }
    }
