package com.example.todo4u;

import java.util.Date;

public class Todo {
    private int todoId;
    private String title;
    private Date deadline;
    private String description;
    private Reminder reminder;
    private Board board;
    private Member member;

    private Todo(int todoId, String title, Date deadline, String description, Reminder reminder, Board board, Member member)
    {
        this.todoId=todoId;
        this.title=title;
        this.deadline=deadline;
        this.description=description;
        this.reminder=reminder;
        this.board=board;
        this.member = member;

        board.addTodoToBoard(this);
    }





}
