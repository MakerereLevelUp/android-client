package com.example.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class TodoList extends Activity implements TodoListReady {

    FetchTodosTask fetchTodosTask = new FetchTodosTask();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fetchTodosTask.delegate = this;
        fetchTodosTask.execute("https://todo-list-app-dan.herokuapp.com/api/tasks");
    }

    @Override
    public void displayList(List<TodoItem> todoItems) {
        ArrayAdapter<TodoItem> adapter = new ArrayAdapter<TodoItem>(this, android.R.layout.simple_list_item_1, todoItems);
        ListView listView = (ListView)findViewById(R.id.todoItems);
        listView.setAdapter(adapter);
        makeProgressBarDisappear();
    }

    private void makeProgressBarDisappear() {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }
}