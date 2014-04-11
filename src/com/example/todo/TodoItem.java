package com.example.todo;

public class TodoItem {
    private String todoText;
    public TodoItem(String todoText) {
        this.todoText = todoText;
    }
    public String toString() {
        return todoText;
    }
}
