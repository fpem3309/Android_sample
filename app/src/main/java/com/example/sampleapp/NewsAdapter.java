package com.example.sampleapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsData> newsDataset;
    private static View.OnClickListener onClickListener;


    // 2. 그 안의 요소를 찾아가는 ViewHolder Class
    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_title;
        public TextView textView_content;
        public SimpleDraweeView imageView_title;
        public View rootView;

        public NewsViewHolder(View v){
            super(v);
            // Activity가 아니라 부모로 정한 Linerlayout v 라고 정의한 v에서 findViewById로 값을 가져와야 함
            textView_title = v.findViewById(R.id.textView_title);
            textView_content = v.findViewById(R.id.textView_content);
            imageView_title = v.findViewById(R.id.imageView_title);
            rootView = v;

            v.setClickable(true); // 누를 수 있다
            v.setEnabled(true); // 활성화 상태
            v.setOnClickListener(onClickListener);
        }
    }
    public NewsAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick){
        newsDataset = myDataset;
        onClickListener = onClick;
        Fresco.initialize(context);
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

        NewsData news = newsDataset.get(position);
        holder.textView_title.setText(news.getTitle());
        holder.textView_content.setText(news.getContent());


        Uri uri = Uri.parse(news.getUrlToImage());
        holder.imageView_title.setImageURI(uri);

        //tag
        holder.rootView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return newsDataset == null ? 0 : newsDataset.size();
    }

    public NewsData getNews(int position){
        return newsDataset == null? null : newsDataset.get(position);
    }

}
