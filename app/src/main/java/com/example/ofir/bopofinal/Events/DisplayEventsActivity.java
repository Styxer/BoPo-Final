package com.example.ofir.bopofinal.Events;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.Search.SearchActivity;


public class DisplayEventsActivity extends AppCompatActivity {

    TextView name, location, date, time, event_id, NoEvents;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String role;
    String user_id;
    String SearchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);
        if (MainAppScreenActivity.SearchEventsFlag == true) {
            SharedPreferences Pref1 = getSharedPreferences("SearchParams", MODE_PRIVATE);
            SearchStr = Pref1.getString("str", "");
            getSupportActionBar().setTitle("results for " + SearchStr);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            name = (TextView) findViewById(R.id.tvEventName);
            location = (TextView) findViewById(R.id.tvLocation);
            date = (TextView) findViewById(R.id.tvDate);
            time = (TextView) findViewById(R.id.tvTime);
            event_id = (TextView) findViewById(R.id.tvEventId);
            NoEvents = (TextView) findViewById(R.id.tvNoEvents);

            if (SearchActivity.noResultsFlag == true) {
                recyclerView.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
                location.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                NoEvents.setVisibility(View.VISIBLE);
            } else if (SearchActivity.noResultsFlag == false) {
                recyclerView.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                location.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                NoEvents.setVisibility(View.INVISIBLE);

                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                adapter = new RecyclerAdapter(EventResultService.getInstance().getArray(), this);
                EventResultService.getInstance().reset();
                recyclerView.setAdapter(adapter);
            }
        }
        else if (MainAppScreenActivity.MyEventsFlag == true){
                SharedPreferences Pref = getSharedPreferences("UserInfoForEvents", MODE_PRIVATE);
                role = Pref.getString("role", "");
                user_id = Pref.getString("user_id", "");
                getSupportActionBar().setTitle("events where i am " + role);

                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                name = (TextView) findViewById(R.id.tvEventName);
                location = (TextView) findViewById(R.id.tvLocation);
                date = (TextView) findViewById(R.id.tvDate);
                time = (TextView) findViewById(R.id.tvTime);
                event_id = (TextView) findViewById(R.id.tvEventId);
                NoEvents = (TextView) findViewById(R.id.tvNoEvents);

                if (ShowMyEventsActivity.noEventsFlag == true) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.INVISIBLE);
                    location.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.INVISIBLE);
                    NoEvents.setVisibility(View.VISIBLE);
                } else if (ShowMyEventsActivity.noEventsFlag == false) {
                    recyclerView.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    NoEvents.setVisibility(View.INVISIBLE);

                    layoutManager = new LinearLayoutManager(this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

                    adapter = new RecyclerAdapter(EventResultService.getInstance().getArray(),this);
                    EventResultService.getInstance().reset();
                    recyclerView.setAdapter(adapter);
                }
            }
        }
    }
