package com.example.todo4u.Models;

import java.util.ArrayList;

public class Board {
    public Member owner;
    public String boardDescription;
    public ArrayList<Todo> todos;
    public String boardName;

    public Board(){}

    public Board(String boardName, String boardDescription, Member member)
    {
        this.boardDescription=boardDescription;
        this.boardName=boardName;
        todos = new ArrayList<>();
        owner = member;
    }

    public void addTodoToBoard(Todo todo)
    {
        todos.add(todo);
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void deleteTodo(String name)
    {
        todos.remove(name);
    }



    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}
