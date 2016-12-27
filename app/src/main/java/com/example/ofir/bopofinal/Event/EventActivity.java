package com.example.ofir.bopofinal.Event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.PeopleInEvent.UsersInEventActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView tvEventName, tvEventDescription, tvTime, tvDate, tvLocation, tvCategory,
                          tvACK, tvRole, tvMaxPeople, tvCurrentUsers, tvViewPeople;

    private String title ,description, time, date, location, category, ACK, role, maxPeople, currentPeople;
    Bundle bundle;
    String eventId;
    int userId;


    public EventActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        bundle = getIntent().getExtras();
        eventId = bundle.getString("str");
        userId = LoggedInUserService.getInstance().getM_id();
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

        tvViewPeople.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {

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

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        EventRequest eventRequest = new EventRequest(userId, eventId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
        queue.add(eventRequest);



    }



    @Override
    public void onClick(View view) {
        startActivity(new Intent(EventActivity.this, UsersInEventActivity.class));
    }
}
