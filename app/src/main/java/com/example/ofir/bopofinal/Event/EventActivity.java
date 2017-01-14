package com.example.ofir.bopofinal.Event;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.CreateNewEvent.CreateNewEventActivity;
import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.PeopleInEvent.UsersInEventActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView tvEventName, tvEventDescription, tvTime, tvDate, tvLocation, tvCategory,
                          tvACK, tvRole, tvMaxPeople, tvCurrentUsers, tvViewPeople;

    private static Button mBedeleteEvent,mBeditEvent, mbJoinEvent;
    Button[] mButtons;

    private String title ,description, time, date, location, category, ACK, role, maxPeople, currentPeople;
    Bundle bundle;
    String eventId;
    int userId;

    private ProgressDialog mProgressDialog;
    RelativeLayout mRelativeLayout;

    private final int FETCH_EVENT_DETAILS  = 1;
    private final int DELETE_EVENT  = 2;
    private final int EDIT_EVENT  = 3;
    private final int JOIN_EVENT  = 4;




    public EventActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        bundle = getIntent().getExtras();
        eventId = bundle.getString("str");



        getSupportActionBar().setTitle("Event details");

        tvEventName = (TextView) findViewById(R.id.tvEventName);
        tvEventDescription = (TextView) findViewById(R.id.tvEventDescription);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvACK = (TextView) findViewById(R.id.tvACK);
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvMaxPeople = (TextView) findViewById(R.id.tvMaxPeople);
        tvCurrentUsers = (TextView) findViewById(R.id.tvCurrentUsers);
        tvViewPeople = (TextView) findViewById(R.id.tvViewPeople);

        mBedeleteEvent = (Button) findViewById(R.id.bMdeleteEvent);
        mBeditEvent = (Button) findViewById(R.id.bMeditEvent);
        mbJoinEvent = (Button) findViewById(R.id.bJoinEvent);

        tvViewPeople.setOnClickListener(this);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayout);

        mButtons = new Button[]{mBedeleteEvent, mBeditEvent, mbJoinEvent};

        for (Button buttons : mButtons) {
            buttons.setOnClickListener(this);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStart() {
        super.onStart();
        userId = LoggedInUserService.getInstance().getM_id();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setInverseBackgroundForced(false);

        mRelativeLayout.setAlpha(0);
        mProgressDialog.show();
        Response.Listener<String> getEventReStringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.i("response",response.toString());

                    if (success) {
                        mProgressDialog.hide();
                        title  = "<b>" + jsonResponse.getString("event_name") + "</b>";
                        description = "<b>" + jsonResponse.getString("event_description") + "</b>" ;
                        time = "<b>" + jsonResponse.getString("event_time") + "</b>";
                        date = "<b>" +jsonResponse.getString("event_date") + "</b>";
                        location = "<b>" + jsonResponse.getString("event_location") + "</b>";
                        category = "<b>" + jsonResponse.getString("category_name") + "</b>";
                        ACK = "<b>" + jsonResponse.getString("ack_needed") + "</b>";
                        role = "<b>" + jsonResponse.getString("role") + "</b>";
                        maxPeople = "<b>" + jsonResponse.getString("max_members") + "</b>";
                        currentPeople  = "<b>" + jsonResponse.getString("currentPeople") + "</b>";

                        setButton(View.INVISIBLE, mButtons);

                        tvEventName.setText(Html.fromHtml("event name: "+title));
                        tvEventDescription.setText(Html.fromHtml("event description: "+description));
                        tvTime.setText(Html.fromHtml("event start time: "+time));
                        tvDate.setText(Html.fromHtml("event date: "+date));
                        tvLocation.setText(Html.fromHtml("event location: "+location));
                        tvCategory.setText(Html.fromHtml("event category: "+category));
                        tvACK.setText(Html.fromHtml("new user need the aprovel: "+ACK));
                        tvRole.setText(Html.fromHtml("you are the "+role+" in the event"));
                        tvMaxPeople.setText(Html.fromHtml("event user cap: "+ maxPeople));
                        tvCurrentUsers.setText(Html.fromHtml("current users in the event: "+currentPeople));

                        mRelativeLayout.setAlpha(1);

                    }
                } catch (JSONException e) {
                    mProgressDialog.hide();
                    e.printStackTrace();
                }
            }
        };

        createRequest(userId,eventId,FETCH_EVENT_DETAILS,getEventReStringListener);

        /*EventRequest eventRequest = new EventRequest(userId, eventId,FETCH_EVENT_DETAILS, getEventReStringListener);
        RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
        queue.add(eventRequest);*/
    }

    void setButton(int state, Button... buttons){
        Log.i("role",role);
       if(role.equals("<b>participant</b>")) {
            for (Button button : buttons) {
                button.setVisibility(state);
            }
           }
       else if (role.equals("<b>waiting for ack</b>")){
           buttons[0].setText("wait for ack from the admin");
           buttons[0].setTextColor(Color.RED);
           buttons[0].setClickable(false);
           buttons[1].setVisibility(state);
           buttons[2].setVisibility(state);
        }
        else if (role.equals("<b>moderator</b>")){
           buttons[0].setTextColor(Color.RED);
           buttons[2].setVisibility(state);
       }
        else {
           buttons[0].setVisibility(state);
           buttons[1].setVisibility(state);
           buttons[2].setVisibility(View.VISIBLE);
           buttons[2].setText("join this event");
       }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvViewPeople:
                startActivity(new Intent(EventActivity.this, UsersInEventActivity.class));
                break;

            case  R.id.bMdeleteEvent:
                openDialog("to delete this event?",DELETE_EVENT);
                break;

            case R.id.bMeditEvent:
                openDialog("to edit this event?",EDIT_EVENT);
                break;

            case R.id.bJoinEvent:
                openDialog("to join this event?",JOIN_EVENT);
                break;
        }

    }

    private void openDialog(String text,final int choice) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want "  +text)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == DELETE_EVENT){
                          getOnResponse(DELETE_EVENT,"event deleted", ShowMyEventsActivity.class);
                        }

                        else if(choice == EDIT_EVENT){
                           Intent intent = new Intent(EventActivity.this,CreateNewEventActivity.class);

                            intent.putExtra("title",title.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("description",description.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("time",time.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("date",date.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("location",location.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("category",category.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("ACK",ACK.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("role",role);
                            intent.putExtra("maxPeople",maxPeople.replace("<b>","").replace("</b>","").trim());
                            intent.putExtra("currentPeople",currentPeople);
                            startActivityForResult(intent,20);

                        }
                        if(choice == JOIN_EVENT){
                            getOnResponse(JOIN_EVENT, title + "joined the event", MainAppScreenActivity.class);
                        }


                    }

                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // finish();
                    }
                })
                .show();
    }

    private void getOnResponse(int state, final CharSequence successText, final Class className) {
        Response.Listener<String> DeleteEventReStringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        //String role = jsonResponse.getString("role");
                        Toast.makeText(EventActivity.this, successText +" successfully",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(EventActivity.this, className));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Log.i("userId", String.valueOf(userId));
        createRequest(userId,eventId, state,DeleteEventReStringListener);
    }

    private void createRequest(int userId, String eventId,int state, Response.Listener<String> litsner) {
        EventRequest eventRequest = new EventRequest(userId, eventId, state, litsner);
        RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
        queue.add(eventRequest);
    }
}
