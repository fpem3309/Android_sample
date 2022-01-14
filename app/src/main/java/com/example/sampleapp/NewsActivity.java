package com.example.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] myDataset = {"1", "2", "3"};
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerview = findViewById(R.id.recyclerview);

        recyclerview.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        queue = Volley.newRequestQueue(this);
        getNews();
    }

    public void getNews() {

        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=28d52abfe26f4f0488faff173822d17c";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("News",response);

                        try {

                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticles = jsonObj.getJSONArray("articles"); // articles라는 이름의 배열을 가져오기

                            //response -> NewsData에 분류
                            List<NewsData> news = new ArrayList<>();

                            for(int i = 0, j = arrayArticles.length(); i < j; i++){
                               JSONObject obj =  arrayArticles.getJSONObject(i);

                                Log.d("News",obj.toString());

                                NewsData newsdata = new NewsData();
                                newsdata.setTitle( obj.getString("title"));
                                newsdata.setUrlToImage( obj.getString("urlToImage"));
                                newsdata.setContent(obj.getString("content"));

                                news.add(newsdata);
                            }

                            adapter = new NewsAdapter(news, NewsActivity.this);
                            recyclerview.setAdapter(adapter);

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
        queue.add(stringRequest);

    }

}
