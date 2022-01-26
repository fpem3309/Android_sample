package com.example.sampleapp;

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
        private TextView board_no;
        private TextView board_subject;
        private TextView board_content;

        public BoardViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            board_no = view.findViewById(R.id.board_no);
            board_subject = view.findViewById(R.id.board_subject);
            board_content = view.findViewById(R.id.board_content);
        }

    }

    public BoardAdapter(List<BoardData> boardDataset) {
        boardDataList = boardDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BoardAdapter.BoardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        // 특정한 컴포넌트 ( 여기서는 리사이클러 뷰 )의 특정 항목의 레이아웃을 바꾸는 inflate
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.board_list, viewGroup, false);
        BoardViewHolder vh = new BoardViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BoardViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.board_no.setText(position);
        viewHolder.board_subject.setText(position);
        viewHolder.board_content.setText(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return boardDataList == null ? 0 : boardDataList.size();
    }

}
