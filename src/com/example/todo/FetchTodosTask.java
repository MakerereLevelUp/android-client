package com.example.todo;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FetchTodosTask extends AsyncTask<String, Void, JSONArray> {

    public TodoListScreen listScreen = null;

    @Override
    protected JSONArray doInBackground(String... urls) {

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(urls[0]);
        String responseBody = "{}";
        try {
            HttpResponse response = client.execute(get);
            responseBody = getResponseBody(response);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            return new JSONArray(responseBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray todoJsonArray) {
        List<TodoItem> todoItems = new ArrayList<TodoItem>();

        for (int i = 0; i < todoJsonArray.length(); i++) {
            try {
                JSONObject jsonObject = todoJsonArray.getJSONObject(i);
                todoItems.add(new TodoItem(jsonObject.getString("title")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listScreen.displayList(todoItems);
    }

    private String getResponseBody(HttpResponse response) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream body = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(body));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}