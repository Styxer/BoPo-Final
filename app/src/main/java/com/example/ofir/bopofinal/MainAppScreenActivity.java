package com.example.ofir.bopofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

import com.example.ofir.bopofinal.Categories.SuggestCategoryActivity;
import com.example.ofir.bopofinal.CreateNewEvent.CreateNewEventActivity;
import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.Search.SearchActivity;

public class MainAppScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton m_ibSearch;
    private static ImageButton m_ibMyEvents;
    private static ImageButton m_ibSuggestCategory;
    private static ImageButton m_ibAddEvent;

    private static Intent m_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);


          m_ibMyEvents = (ImageButton) findViewById(R.id.ibMyEvents);

       
          m_ibAddEvent = (ImageButton)  findViewById(R.id.ibAddEvent);

      //  btnAddEvent = (EditText) findViewById(R.id.etName);
          m_ibSearch = (ImageButton) findViewById(R.id.ibSearch);
     //   btnProfile = (EditText) findViewById(R.id.etPassword);
     //   btnSettings = (Button) findViewById(R.id.bRegister);
          m_ibSuggestCategory = (ImageButton) findViewById(R.id.ibSuggestCategory);
     //   btnLogout = (Button) findViewById(R.id.bBack);
         
   

        getSupportActionBar().setTitle("Welcome "+ LoggedInUserService.getInstance().getM_name());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ibSearch:
                m_intent = new Intent(MainAppScreenActivity.this, SearchActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
		case R.id.ibMyEvents:
                m_intent = new Intent(MainAppScreenActivity.this, ShowMyEventsActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibSuggestCategory:
                m_intent = new Intent(MainAppScreenActivity.this, SuggestCategoryActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibAddEvent:
                m_intent = new Intent(MainAppScreenActivity.this, CreateNewEventActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
        }
    }
}
