package com.example.ofir.bopofinal.Search;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.ofir.bopofinal.R;

public class DisplaySearchResultsActivity extends AppCompatActivity {

    TextView NoResults;
    RecyclerView PersonRecyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String  SearchStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_results);

        SharedPreferences Pref = getSharedPreferences("SearchParams", MODE_PRIVATE);
        SearchStr = Pref.getString("str", "");

        PersonRecyclerView = (RecyclerView) findViewById(R.id.PersonRecyclerView);
        NoResults = (TextView)findViewById(R.id.tvNoResults);

        getSupportActionBar().setTitle("results for " + SearchStr);

        if (SearchActivity.noResultsFlag == true)
        {
            PersonRecyclerView.setVisibility(View.INVISIBLE);
            NoResults.setVisibility(View.VISIBLE);
        }
        else if (SearchActivity.noResultsFlag == false)
        {
            PersonRecyclerView.setVisibility(View.VISIBLE);
            NoResults.setVisibility(View.INVISIBLE);

            layoutManager = new LinearLayoutManager(this);
            PersonRecyclerView.setLayoutManager(layoutManager);
            PersonRecyclerView.setHasFixedSize(true);

            adapter = new PersonRecyclerAdapter(SearchResultService.getInstance().getArray());
            SearchResultService.getInstance().reset();
            PersonRecyclerView.setAdapter(adapter);
        }

    }
}
