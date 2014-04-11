package com.example.todo;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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

    @Override
    protected JSONArray doInBackground(String... urls) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(urls[0]);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new JSONArray(builder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TodoListReady delegate = null;

    @Override
    protected void onPostExecute(JSONArray todoJsonArray) {
        List<TodoItem> todoItems = new ArrayList<TodoItem>();

        for (int i = 0; i < todoJsonArray.length(); i++) {
            try {
                JSONObject jsonObject = todoJsonArray.getJSONObject(i);
                todoItems.add(new TodoItem(jsonObject.getString("description")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        delegate.displayList(todoItems);
    }
}
