package com.example.ofir.bopofinal.Event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EventActivity extends AppCompatActivity {

    private static TextView tvEventName, tvEventDescription, tvTime, tvDate, tvLocation, tvCategory,
                          tvACK, tvRole, tvMaxPeople, tvCurrentUsers, tvViewPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

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

                    final String title ,description, time, date, location, category, ACK, role, maxPeople, currentPeople;


                    if (success) {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
