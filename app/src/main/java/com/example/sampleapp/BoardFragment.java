package com.example.sampleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    private TextView user_Email;

    RequestQueue requestQueue;  // 서버와 통신할 통로 req

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        //리사이클러뷰의 레이아웃 매니저 설정
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getContext());
        user_Email = view.findViewById(R.id.user_Email);

        return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        getBoard();
    }

    public void getBoard(){


        Bundle extras = getArguments();
        user_Email.setText(extras.getString("email"));

        String userEmail = user_Email.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try{
                Log.d("user_res",response);

                JSONArray jsonArray = new JSONArray(response);

                //response -> BoardData 분류
                List<BoardData> boards = new ArrayList<>();

                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject obj = jsonArray.getJSONObject(i);

                    BoardData boardData = new BoardData();
                    boardData.setBoard_no(obj.getString("board_no"));
                    boardData.setBoard_subject(obj.getString("board_title"));
                    boardData.setBoard_content(obj.getString("board_content"));
                    boardData.setBoard_date(obj.getString("board_date"));
                    boardData.setUser_email(obj.getString("userEmail"));

                    boards.add(boardData);
                }

                adapter = new BoardAdapter(boards);
                recyclerView.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    };

        BoardRequest boardRequest = new BoardRequest(userEmail, responseListener);
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(boardRequest);
    }

    }
