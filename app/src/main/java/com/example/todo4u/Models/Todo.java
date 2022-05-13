package com.example.todo4u.Models;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.Models.Reminder;

import java.util.Date;
import java.util.GregorianCalendar;

public class Todo {
    public String title;
    public GregorianCalendar deadline;
    public String description;
    public Reminder reminder;
    public Board board;
    public Member member;

    public Todo( String title, GregorianCalendar deadline, String description, Reminder reminder, Board board, Member member)
    {
        this.title=title;
        this.deadline=deadline;
        this.description=description;
        this.reminder=reminder;
        this.board=board;
        this.member = member;

    }

    public Todo(){

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GregorianCalendar getDeadline() {
        return deadline;
    }

    public void setDeadline(GregorianCalendar deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
