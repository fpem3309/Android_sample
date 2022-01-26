package com.example.sampleapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private List<BoardData> boardDataList;

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        public TextView board_no;
        public TextView board_subject;
        public TextView board_content;

        public BoardViewHolder(View view) {
            super(view);

            board_no = view.findViewById(R.id.board_no);
            board_subject = view.findViewById(R.id.board_subject);
            board_content = view.findViewById(R.id.board_content);
        }

    }

    public BoardAdapter(List<BoardData> boardDataset) {
        boardDataList = boardDataset;
    }

    @Override
    public BoardAdapter.BoardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.board_list, viewGroup, false);
        BoardViewHolder vh = new BoardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BoardViewHolder viewHolder, int position) {

        BoardData boardData = boardDataList.get(position);
        Log.d("boarddata", String.valueOf(boardData));
        viewHolder.board_no.setText(boardData.getBoard_no());
        viewHolder.board_subject.setText(boardData.getBoard_subject());
        viewHolder.board_content.setText(boardData.getBoard_content());

    }

    @Override
    public int getItemCount() {
        return boardDataList == null ? 0 : boardDataList.size();
    }

}
