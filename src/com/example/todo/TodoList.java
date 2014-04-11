package com.example.todo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoList extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        List<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("Buy milk"));
        todos.add(new Todo("Boil water"));

        ListView todoList = (ListView)findViewById(R.id.todos);

        ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(this, android.R.layout.simple_list_item_1, todos);
        todoList.setAdapter(adapter);
    }
}
