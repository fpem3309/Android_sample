package com.example.sampleapp;

import java.io.Serializable;

public class BoardData implements Serializable {
    private String board_no;
    private String board_subject;
    private String board_content;


    public BoardData(BoardData boardData) {
    }

    @Override
    public String toString() {
        return "BoardData{" +
                "board_no='" + board_no + '\'' +
                ", board_subject='" + board_subject + '\'' +
                ", board_content='" + board_content + '\'' +
                '}';
    }

    public BoardData(String board_no, String board_subject, String board_content) {
        this.board_no = board_no;
        this.board_subject = board_subject;
        this.board_content = board_content;
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

}
