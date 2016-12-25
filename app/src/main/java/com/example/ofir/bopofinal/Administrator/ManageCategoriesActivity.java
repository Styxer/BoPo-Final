package com.example.ofir.bopofinal.Administrator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ofir.bopofinal.Events.EventResultService;
import com.example.ofir.bopofinal.Events.RecyclerAdapter;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.Search.SearchActivity;

public class ManageCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);
        getSupportActionBar().setTitle("Pending categories list");

//
//            CategoriesRecyclerView = (RecyclerView) findViewById(R.id.categoriesRecyclerView);
//            name = (TextView) findViewById(R.id.tvEventName);
//            location = (TextView) findViewById(R.id.tvLocation);
//            date = (TextView) findViewById(R.id.tvDate);
//            time = (TextView) findViewById(R.id.tvTime);
//            NoEvents = (TextView) findViewById(R.id.tvNoEvents);
//
//            if (SearchActivity.noResultsFlag == true) {
//                recyclerView.setVisibility(View.INVISIBLE);
//                name.setVisibility(View.INVISIBLE);
//                location.setVisibility(View.INVISIBLE);
//                date.setVisibility(View.INVISIBLE);
//                time.setVisibility(View.INVISIBLE);
//                NoEvents.setVisibility(View.VISIBLE);
//            } else if (SearchActivity.noResultsFlag == false) {
//                recyclerView.setVisibility(View.VISIBLE);
//                name.setVisibility(View.VISIBLE);
//                location.setVisibility(View.VISIBLE);
//                date.setVisibility(View.VISIBLE);
//                time.setVisibility(View.VISIBLE);
//                NoEvents.setVisibility(View.INVISIBLE);
//
//                layoutManager = new LinearLayoutManager(this);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setHasFixedSize(true);
//
//                adapter = new RecyclerAdapter(EventResultService.getInstance().getArray());
//                EventResultService.getInstance().reset();
//                recyclerView.setAdapter(adapter);
//            }

    }
}
