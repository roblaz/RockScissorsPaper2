package com.example.gamerockscissorspaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoreActivity extends AppCompatActivity {

    private ListView view;
    private Users users;
    public ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        view = findViewById(R.id.list);
        try {
            users = new Users(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Log.e("ScoreActivity", "size:" + users.getUsers().size());
        view.setAdapter( adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, users.getUsers()));

    }

}
