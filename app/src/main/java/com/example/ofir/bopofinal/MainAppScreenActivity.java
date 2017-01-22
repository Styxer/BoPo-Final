package com.example.ofir.bopofinal;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ofir.bopofinal.Categories.SuggestCategoryActivity;
import com.example.ofir.bopofinal.CreateNewEvent.CreateNewEventActivity;
import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.Messages.CountUnreadMessagesService;
import com.example.ofir.bopofinal.Messages.ShowMessages;
import com.example.ofir.bopofinal.Profile.ProfileActivity;
import com.example.ofir.bopofinal.Search.SearchActivity;
import com.example.ofir.bopofinal.Settings.SettingsActivity;
public class MainAppScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView m_Messages;
    String UnreadMessagesNumber;
    private static ImageButton m_ibSearch;
    private static ImageButton m_ibMyEvents;
    private static ImageButton m_ibMessages;
    private static ImageButton m_ibProfile;
    private static ImageButton m_ibSuggestCategory;
    private static ImageButton m_ibAddEvent;
    private static ImageButton m_ibSettings;
    private static ImageButton m_ibMyRides;
    private static ImageButton m_ibLogout;
    public static boolean MyEventsFlag = false;
    public static boolean SearchEventsFlag = false;
    public static boolean noMessagesFlag = false;
    private static Intent m_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);
        UnreadMessagesNumber = CountUnreadMessagesService.getInstance().getM_messages_number()+"";
        m_Messages = (TextView) findViewById(R.id.tvMessages);
        if (!UnreadMessagesNumber.equals("0")) {
            m_Messages.setText("Messages " + "(" + UnreadMessagesNumber + ")");
            m_Messages.setTypeface(null, Typeface.BOLD);
            if (LoginActivity.LoginFlag) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("You have " + UnreadMessagesNumber + " unread messages").setCancelable(false);
                ;
                dlgAlert.setTitle("hello " + LoggedInUserService.getInstance().getM_name() + ",");
                dlgAlert.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dlgAlert.create().show();
            }
        }
        else if (UnreadMessagesNumber.equals("0")) {
            m_Messages.setText("  Messages");
        }
          LoginActivity.LoginFlag = false;
          m_ibMyEvents = (ImageButton) findViewById(R.id.ibMyEvents);
          m_ibProfile = (ImageButton) findViewById(R.id.ibProfile);
          m_ibMessages = (ImageButton) findViewById(R.id.ibMessages);
          m_ibAddEvent = (ImageButton)  findViewById(R.id.ibAddEvent);
          m_ibSettings = (ImageButton) findViewById(R.id.ibSettings);
          m_ibSearch = (ImageButton) findViewById(R.id.ibSearch);
          m_ibSuggestCategory = (ImageButton) findViewById(R.id.ibSuggestCategory);
          m_ibMyRides = (ImageButton) findViewById(R.id.ibMyRides);
          m_ibLogout = (ImageButton) findViewById(R.id.ibLogout);

        getSupportActionBar().setTitle("Welcome "+ LoggedInUserService.getInstance().getM_name());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ibSearch:
                m_intent = new Intent(MainAppScreenActivity.this, SearchActivity.class);
                MyEventsFlag = false;
                SearchEventsFlag = true;
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibProfile:
                m_intent = new Intent(MainAppScreenActivity.this, ProfileActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibMessages:
                new ShowMessages(this);
                break;
	    	case R.id.ibMyEvents:
                m_intent = new Intent(MainAppScreenActivity.this, ShowMyEventsActivity.class);
                MyEventsFlag = true;
                SearchEventsFlag = false;
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibSettings:
                m_intent = new Intent(MainAppScreenActivity.this, SettingsActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibSuggestCategory:
                m_intent = new Intent(MainAppScreenActivity.this, SuggestCategoryActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibAddEvent:
                m_intent = new Intent(MainAppScreenActivity.this, CreateNewEventActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
            case R.id.ibMyRides:
                break;
            case R.id.ibLogout:
                Toast.makeText(MainAppScreenActivity.this, "bye bye" +" "+ LoggedInUserService.getInstance().getM_name(),
                        Toast.LENGTH_SHORT).show();
                LogOutUser();
                m_intent = new Intent(MainAppScreenActivity.this, LoginActivity.class);
                MainAppScreenActivity.this.startActivity(m_intent);
                break;
        }
    }

    public void LogOutUser()
    {
        LoggedInUserService.getInstance().reset();
    }
}