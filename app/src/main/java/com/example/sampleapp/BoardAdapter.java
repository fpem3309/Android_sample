package com.example.sampleapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    RequestQueue requestQueue;  // 서버와 통신할 통로
    private List<BoardData> boardDataList;

    public class BoardViewHolder extends RecyclerView.ViewHolder {

        public TextView board_no;
        public TextView board_subject;
        public TextView board_content;
        public TextView board_hit;
        public TextView board_date;
        public RelativeLayout relativeLayout_expandable;
        public CardView cardView_content;

        public BoardViewHolder(View view) {
            super(view);

            board_no = view.findViewById(R.id.board_no);
            board_subject = view.findViewById(R.id.board_subject);
            board_content = view.findViewById(R.id.board_content);
            cardView_content = view.findViewById(R.id.cardView_content);
            board_hit = view.findViewById(R.id.board_hit);
            board_date = view.findViewById(R.id.board_date);

            relativeLayout_expandable = view.findViewById(R.id.relativeLayout_expandable);

            cardView_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"click",Toast.LENGTH_SHORT).show();
                    BoardData boardData = boardDataList.get(getAbsoluteAdapterPosition());
                    boardData.setExpandable(!boardData.isExpandable());
                    Log.d("pandable", String.valueOf(boardData.isExpandable()));
                    if(boardData.isExpandable()==true){
                        relativeLayout_expandable.setVisibility(View.VISIBLE);
                    }
                }
            });
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
        viewHolder.board_no.setText(boardData.getBoard_no());
        viewHolder.board_subject.setText(boardData.getBoard_subject());
        viewHolder.board_content.setText(boardData.getBoard_content());
        viewHolder.board_date.setText(boardData.getBoard_date());
        viewHolder.board_hit.setText(boardData.getBoard_hit());

        boolean isExpandable = boardDataList.get(position).isExpandable();
        viewHolder.relativeLayout_expandable.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return boardDataList == null ? 0 : boardDataList.size();
    }

}
