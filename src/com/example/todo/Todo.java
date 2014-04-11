package com.example.todo;

public class Todo {
    private String todoText;
    public Todo(String todoText) {
        this.todoText = todoText;
    }
    public String toString() {
        return this.todoText;
    }
}
