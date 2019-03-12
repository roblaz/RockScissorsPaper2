package com.example.gamerockscissorspaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button addName;
    EditText inputName;

    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addName = findViewById(R.id.bt_add_name);
        inputName = findViewById(R.id.et_name);
        try {
            users = new Users( getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextPage(View view) {
        saveName();
    }

    public void saveName(){
        String name = inputName.getText().toString().trim();
        int  score = 0;

        Toast.makeText(this, name , Toast.LENGTH_SHORT).show();

        try {
            users.addUser(new User(name,score));
            users.save();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
        intent.putExtra("user", name);
        startActivity(intent);
        finish();

    }

    public void clear(){
        inputName.setText("");
    }

}
