package com.example.ofir.bopofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ofir.bopofinal.Categories.SuggestCategoryActivity;
import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;

public class MainAppScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton ibMyEvents;
    private static ImageButton ibSuggestCategoty;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);

          ibMyEvents = (ImageButton) findViewById(R.id.ibMyEvents);
      //  btnAddEvent = (EditText) findViewById(R.id.etName);
      //  btnSearch = (EditText) findViewById(R.id.etUserName);
     //   btnProfile = (EditText) findViewById(R.id.etPassword);
     //   btnSettings = (Button) findViewById(R.id.bRegister);
          ibSuggestCategoty = (ImageButton) findViewById(R.id.ibSuggestCategory);
     //   btnLogout = (Button) findViewById(R.id.bBack);

        getSupportActionBar().setTitle("Welcome "+ LoggedInUserService.getInstance().getM_name());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibMyEvents:
                intent = new Intent(MainAppScreenActivity.this, ShowMyEventsActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
            case R.id.ibSuggestCategory:
                intent = new Intent(MainAppScreenActivity.this, SuggestCategoryActivity.class);
                MainAppScreenActivity.this.startActivity(intent);
                break;
        }
    }
}
