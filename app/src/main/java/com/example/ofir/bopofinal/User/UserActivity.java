package com.example.ofir.bopofinal.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.example.ofir.bopofinal.PeopleInEvent.UsersInEventActivity;
import com.example.ofir.bopofinal.R;

public class UserActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        textView1 = (TextView) findViewById(R.id.tvUserTitle);

        Intent intent = getIntent();
        String name = intent.getStringExtra(UsersInEventActivity.EXTRA_TITLE);


        textView1.setText(name);
    }
}
