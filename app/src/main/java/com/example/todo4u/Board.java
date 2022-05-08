package com.example.todo4u;

import java.util.ArrayList;

public class Board {
    private String boardName;
    private String boardDescription;
    private ArrayList<Todo> todos;

    public Board(String boardName, String boardDescription)
    {
        this.boardDescription=boardDescription;
        this.boardName=boardName;
        todos = new ArrayList<>();
    }

    public void addTodoToBoard(Todo todo)
    {
        todos.add(todo);
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void deleteTodo(int todoId)
    {
        todos.remove(todoId);
    }

    public int getTodoCount()
    {
        return todos.size();
    }
}
