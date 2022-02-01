package com.example.sampleapp;

import java.io.Serializable;

public class BoardData implements Serializable {
    private String board_no;
    private String board_subject;
    private String board_content;
    private String board_hit;
    private String board_date;
    private boolean expandable;

    public BoardData() {

    }

//    @Override
//    public String toString() {
//        return "BoardData{" +
//                "board_no='" + board_no + '\'' +
//                ", board_subject='" + board_subject + '\'' +
//                ", board_content='" + board_content + '\'' +
//                '}';
//    }

    public BoardData(String board_no, String board_subject, String board_content) {
        this.board_no = board_no;
        this.board_subject = board_subject;
        this.board_content = board_content;
        this.expandable = true;
    }
    public BoardData(BoardData boardData) {
        this.expandable = false;
    }
    public boolean isExpandable() {
        return expandable;
    }
    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }



    public String getBoard_no() {
        return board_no;
    }
    public void setBoard_no(String board_no) {
        this.board_no = board_no;
    }


    public String getBoard_subject() {
        return board_subject;
    }
    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }


    public String getBoard_content() {
        return board_content;
    }
    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }


    public String getBoard_hit() {
        return board_hit;
    }
    public void setBoard_hit(String board_hit) {
        this.board_hit = board_hit;
    }


    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }
    public String getBoard_date() { return board_date; }
}
