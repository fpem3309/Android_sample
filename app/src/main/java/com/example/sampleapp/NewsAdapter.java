package com.example.sampleapp;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private String[] Dataset;

    private static class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public NewsViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }
    public NewsAdapter(String[] newsDataset){
        Dataset = newsDataset;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        NewsViewHolder vh = new NewsViewHolder(v);
        retrun vh;
    }

}
