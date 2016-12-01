package com.example.ofir.bopofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;


public class MainActivity extends AppCompatActivity {

    private static  TextView tvWelcomeMsg;
    private static EditText etUsername;
    private static   EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();

        String name = loggedInUserService.getM_name();
        String email = loggedInUserService.getM_email();
        String birthday = loggedInUserService.getM_birthday();

         tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        etUsername = (EditText) findViewById(R.id.fdetEmail);
         etAge = (EditText) findViewById(R.id.etDateOfBirth);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(email);
        etAge.setText(birthday + "");
    }
}
