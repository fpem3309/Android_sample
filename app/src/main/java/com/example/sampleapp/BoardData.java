package com.example.sampleapp;

import java.io.Serializable;

public class BoardData implements Serializable {
    private String board_no;
    private String board_subject;
    private String board_content;
    private String board_hit;
    private String board_date;
    private String user_email;
    private String board_mood;
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


    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
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
        if(board_content.equals("null")){
            board_content = "아직 답변을 안했어요";
        }
        return board_content;
    }
    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }


    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }
    public String getBoard_date() { return board_date; }

    public String getBoard_mood() {
        if(board_mood.equals("2")){
            board_mood = "\uD83E\uDD70";
        }else if(board_mood.equals("1")){
            board_mood = "\uD83D\uDE42";
        }else if(board_mood.equals("0")){
            board_mood = "\uD83E\uDD75";
        }
        return board_mood;
    }

    public void setBoard_mood(String user_mood) {
        this.board_mood = user_mood;
    }
}
