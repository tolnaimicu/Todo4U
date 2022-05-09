package com.example.todo4u;

public class User {

    public String name;
    public String Id;

    public User(String name, String Id) {
        this.name = name;
        this.Id = Id;
    }

    public User ()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
