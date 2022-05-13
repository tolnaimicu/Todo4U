package com.example.todo4u.Models;

import com.example.todo4u.Models.Board;
import com.example.todo4u.Models.Member;
import com.example.todo4u.Models.Reminder;

import java.util.Date;

public class Todo {
    private int todoId;
    private String title;
    private Date deadline;
    private String description;
    private Reminder reminder;
    private Board board;
    private Member member;

    public Todo(int todoId, String title, Date deadline, String description, Reminder reminder, Board board, Member member)
    {
        this.todoId=todoId;
        this.title=title;
        this.deadline=deadline;
        this.description=description;
        this.reminder=reminder;
        this.board=board;
        this.member = member;

        //board.addTodoToBoard(this);
    }

    public Todo(){

    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
