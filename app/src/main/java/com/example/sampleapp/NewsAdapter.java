package com.example.sampleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private String[] newsDataset;


    // 2. 그 안의 요소를 찾아가는 ViewHolder Class
    protected static class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_title;
        public TextView textView_content;
        public ImageView imageView_title;

        public NewsViewHolder(View v){
            super(v);
            // Activity가 아니라 부모로 정한 Linerlayout v 라고 정의한 v에서 findViewById로 값을 가져와야 함
            textView_title = v.findViewById(R.id.textView_title);
            textView_content = v.findViewById(R.id.textView_content);
            imageView_title = v.findViewById(R.id.imageView_title);
        }
    }
    public NewsAdapter(String[] myDataset){
        newsDataset = myDataset;
    }

    // 1. RecyclerView의 항목화면 연결은 onCreateViewHolder함수
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        // 특정한 컴포넌트 ( 여기서는 리사이클러 뷰 )의 특정 항목의 레이아웃을 바꾸는 inflate
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        NewsViewHolder vh = new NewsViewHolder(v);

        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.textView_title.setText(newsDataset[position]);
    }

    @Override
    public int getItemCount() {
        return newsDataset.length;
    }

}
