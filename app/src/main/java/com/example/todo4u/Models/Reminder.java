package com.example.todo4u.Models;

import java.util.ArrayList;

public class Reminder {

    public String type;

    public Reminder(){}

    public Reminder(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
