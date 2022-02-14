package com.example.sampleapp;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<BoardData> boardDataList;

    public class BoardViewHolder extends RecyclerView.ViewHolder {

        public TextView board_no, board_subject, board_content, board_date, board_mood;
        public RelativeLayout relativeLayout_expandable;
        public CardView cardView_content;
        private ImageButton btn_answer;

        public BoardViewHolder(View view) {
            super(view);

            board_no = view.findViewById(R.id.board_no);
            board_subject = view.findViewById(R.id.board_subject);
            board_content = view.findViewById(R.id.board_content);
            cardView_content = view.findViewById(R.id.cardView_content);
            board_date = view.findViewById(R.id.board_date);
            btn_answer = view.findViewById(R.id.btn_answer);
            board_mood = view.findViewById(R.id.board_mood);

            relativeLayout_expandable = view.findViewById(R.id.relativeLayout_expandable);

            cardView_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BoardData boardData = boardDataList.get(getBindingAdapterPosition());
                    boardData.setExpandable(!boardData.isExpandable());
                    notifyItemChanged(getBindingAdapterPosition());
                    Log.d("pandable", String.valueOf(boardData.isExpandable()));
                }
            });


            btn_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BoardData boardData = boardDataList.get(getBindingAdapterPosition());
                    Intent intent = new Intent(view.getContext(),AnswerActivity.class);
                    intent.putExtra("userBoard_no", boardData.getBoard_no());
                    intent.putExtra("userBoard_subject", boardData.getBoard_subject());
                    intent.putExtra("userBoard_content", boardData.getBoard_content());
                    intent.putExtra("userBoard_mood", boardData.getBoard_mood());
                    intent.putExtra("userBoard_date", boardData.getBoard_date());
                    view.getContext().startActivity(intent);
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
        viewHolder.board_no.setText("#"+boardData.getBoard_no());
        viewHolder.board_subject.setText(boardData.getBoard_subject());
        viewHolder.board_content.setText(boardData.getBoard_content().replaceAll("(\\\\n)", "\n"));
        viewHolder.board_date.setText(boardData.getBoard_date());
        viewHolder.board_mood.setText(boardData.getBoard_mood());
        if(boardData.getBoard_content().equals("아직 답변을 안했어요")){
            viewHolder.board_no.setTextColor(0xAA1e6de0);
        }

        boolean isExpandable = boardDataList.get(position).isExpandable();
        viewHolder.relativeLayout_expandable.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return boardDataList == null ? 0 : boardDataList.size();
    }

}
