package com.example.ofir.bopofinal.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.Events.DisplayEventsActivity;
import com.example.ofir.bopofinal.Events.EventDetails;
import com.example.ofir.bopofinal.Events.EventResultService;
import com.example.ofir.bopofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Button btnSearch;
    EditText etSearch;
    TextView tvSearchBy;
    RadioButton rbSearchEvents, rbSearchPeople, rbSearchByName, rbSearchByLocation, rbSearchByDate, rbSearchByCategory;
    RadioGroup rgOne, rgTwo;
    String Search_column;
    Context context;
    ArrayList<SearchPersonDetails> arrayList = new ArrayList<>();
    ArrayList<EventDetails> arrayListEvents = new ArrayList<>();
    String json_url;
    public static boolean noResultsFlag = false;
    public static int tableFlag = 0; //1 for people, 2 for events,0 for default.
    public static int columnFlag = 0; //1 for event name, 2 for category, 3 for location, 4 for date, 0 for default.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        tvSearchBy = (TextView) findViewById(R.id.tvSearchBy);
        rbSearchEvents = (RadioButton) findViewById(R.id.rbEvents);
        rbSearchPeople = (RadioButton) findViewById(R.id.rbPeople);
        rbSearchByName = (RadioButton) findViewById(R.id.rbByName);
        rbSearchByCategory = (RadioButton) findViewById(R.id.rbByCategory);
        rbSearchByLocation = (RadioButton) findViewById(R.id.rbByLocation);
        rbSearchByDate = (RadioButton) findViewById(R.id.rbByDate);
        rgOne = (RadioGroup) findViewById(R.id.rgNumberOne);
        rgTwo = (RadioGroup) findViewById(R.id.rgNumberTwo);

        tableFlag = 0;
        columnFlag = 0;
        getSupportActionBar().setTitle("Search");

        rgOne.check(R.id.rbEvents);
        rgTwo.check(R.id.rbByName);

        rbSearchPeople.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbSearchPeople.isChecked()) {
                    tvSearchBy.setVisibility(View.INVISIBLE);
                    rgTwo.setVisibility(View.INVISIBLE);
                } else if (rbSearchEvents.isChecked()) {
                    tvSearchBy.setVisibility(View.VISIBLE);
                    rgTwo.setVisibility(View.VISIBLE);
                    rgTwo.check(R.id.rbByName);
                }
            }
        });
       btnSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(rbSearchPeople.isChecked())
               {
                   tableFlag = 1;
                   json_url = "http://tower.site88.net/SearchPeople.php";
               }
               else if (rbSearchEvents.isChecked())
               {
                   tableFlag = 2;
                   if (rbSearchByName.isChecked())
                       {
                           Search_column = "name";
                           columnFlag = 1;
                           json_url = "http://tower.site88.net/SearchEventsByName.php";
                       }
                   else if (rbSearchByCategory.isChecked())
                       {
                           Search_column = "category";
                           columnFlag = 2;
                           json_url = "http://tower.site88.net/SearchEventsByCategory.php";
                       }
                   else if (rbSearchByLocation.isChecked())
                       {
                           Search_column = "location";
                           columnFlag = 3;
                           json_url = "http://tower.site88.net/SearchEventsByLocation.php";
                       }
                   else if (rbSearchByDate.isChecked())
                       {
                           Search_column = "date";
                           columnFlag = 4;
                           json_url = "http://tower.site88.net/SearchEventsByDate.php";
                       }
               }
               SharedPreferencesMaker(etSearch.getText().toString());
               RequestMaker(etSearch.getText().toString(), json_url);
           }
       });

    }


    public Response.Listener<JSONArray> getResponseListener()
    {
        return new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                int count = 0;
                if (response.length()== 0)
                {
                    noResultsFlag = true;
                    startActivity(new Intent(SearchActivity.this, DisplaySearchResultsActivity.class));
                }
                else if (tableFlag == 1){
                    noResultsFlag = false;
                    while (count < response.length())
                    {
                        try
                        {
                            JSONObject jsonObject = response.getJSONObject(count);

                                SearchPersonDetails searchPersonDetails = new SearchPersonDetails(jsonObject.getString("name"),jsonObject.getString("user_id"), jsonObject.getString("image"), jsonObject.getString("email"), jsonObject.getString("birthday"), jsonObject.getString("phone_number"),jsonObject.getString("address"));
                                arrayList.add(searchPersonDetails);
                                count++;


                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        SearchResultService.getInstance().setArray(arrayList);
                    }
                    startActivity(new Intent(SearchActivity.this, DisplaySearchResultsActivity.class));
                }

                else if (tableFlag == 2){
                    noResultsFlag = false;
                    while (count < response.length())
                    {
                        try
                        {
                            JSONObject jsonObject = response.getJSONObject(count);

                            EventDetails eventDetails =  new EventDetails(jsonObject.getString("event_name"), jsonObject.getString("event_location"), jsonObject.getString("event_date"), jsonObject.getString("event_time"),jsonObject.getString("event_id"));
                            arrayListEvents.add(eventDetails);
                            count++;


                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        EventResultService.getInstance().setArray(arrayListEvents);
                    }
                    startActivity(new Intent(SearchActivity.this, DisplayEventsActivity.class));
                }
            }
        };
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "search results showing error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        };
    }

   public void SharedPreferencesMaker(String searchStr) {
        SharedPreferences Pref = getSharedPreferences("SearchParams", MODE_PRIVATE);
        SharedPreferences.Editor editor = Pref.edit();
        editor.putString("str",searchStr);
        editor.apply();
    }

    public void RequestMaker(String str, String url)
    {
        SearchRequest searchRequest = new SearchRequest(str, url, getResponseListener(), getErrorListener());
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(searchRequest);
    }
}
