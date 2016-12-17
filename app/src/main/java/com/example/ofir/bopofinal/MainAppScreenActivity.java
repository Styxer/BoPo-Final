package com.example.ofir.bopofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ofir.bopofinal.Categories.SuggestCategoryActivity;
import com.example.ofir.bopofinal.CreateNewEvent.CreateNewEventActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;

public class MainAppScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton btnMyEvents;
    private static ImageButton btnSuggestCategoty;
    private static ImageButton ibAddEvent;
    private static Intent intent;
    private static LoggedInUserService loggedInUserService;
    private static EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);

          btnMyEvents = (ImageButton) findViewById(R.id.ibMyEvents);
          ibAddEvent = (ImageButton)  findViewById(R.id.ibAddEvent);
      //  btnAddEvent = (EditText) findViewById(R.id.etName);
      //  btnSearch = (EditText) findViewById(R.id.etUserName);
     //   btnProfile = (EditText) findViewById(R.id.etPassword);
     //   btnSettings = (Button) findViewById(R.id.bRegister);
          btnSuggestCategoty = (ImageButton) findViewById(R.id.ibSuggestCategory);
     //   btnLogout = (Button) findViewById(R.id.bBack);
          etUserName = (EditText) findViewById(R.id.etUserName);

         loggedInUserService = LoggedInUserService.getInstance();
         String name = loggedInUserService.getM_name();
         etUserName.setText("Welcome " + name );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibMyEvents:
                intent = new Intent(MainAppScreenActivity.this, LoginActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
            case R.id.ibSuggestCategory:
                intent = new Intent(MainAppScreenActivity.this, SuggestCategoryActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
            case R.id.ibAddEvent:
                intent = new Intent(MainAppScreenActivity.this, CreateNewEventActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
        }
    }
}
