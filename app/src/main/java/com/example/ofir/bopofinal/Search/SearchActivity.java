package com.example.ofir.bopofinal.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ofir.bopofinal.Events.ShowMyEventsActivity;
import com.example.ofir.bopofinal.R;

public class SearchActivity extends AppCompatActivity {

    public static boolean searchByEventsFlag = true;
    Button btnSearch;
    EditText etSearch;
    RadioButton rbSearchEvents;
    RadioButton rbSearchPeople;
    RadioButton rbSearchByName;
    RadioButton rbSearchByLocation;
    RadioButton rbSearchByDate;
    RadioButton rbSearchByCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        rbSearchEvents = (RadioButton) findViewById(R.id.rbEvents);
        rbSearchPeople = (RadioButton) findViewById(R.id.rbPeople);
        rbSearchByName = (RadioButton) findViewById(R.id.rbByName);
        rbSearchByCategory = (RadioButton) findViewById(R.id.rbByCategory);
        rbSearchByLocation = (RadioButton) findViewById(R.id.rbByLocation);
        rbSearchByDate = (RadioButton) findViewById(R.id.rbByDate);
        getSupportActionBar().setTitle("Search");


    }
}
