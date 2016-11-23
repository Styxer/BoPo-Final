package com.example.ofir.bopofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static  TextView tvWelcomeMsg;
    private static EditText etUsername;
    private static   EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

         tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        etUsername = (EditText) findViewById(R.id.etUserName);
         etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");
    }
}
