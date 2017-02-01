package com.example.ofir.bopofinal.PeopleInEvent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.Events.DisplayEventsActivity;
import com.example.ofir.bopofinal.Events.EventDetails;
import com.example.ofir.bopofinal.Events.EventResultService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.Search.DisplaySearchResultsActivity;
import com.example.ofir.bopofinal.Search.SearchPersonDetails;
import com.example.ofir.bopofinal.Search.SearchRequest;
import com.example.ofir.bopofinal.Search.SearchResultService;
import com.example.ofir.bopofinal.User.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UsersInEventActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CustomAdapter mAdapter;
    private List<MyData> m_data_list;
    private CardView mCardView;
    private MyData mData;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRRA_ROLE = "role";
    public static final String EXTRA_BIRTHDAY = "birthday";
    public static final String EXTRRA_PHONE_NUMBER = "phone_number";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRRA_IMAGE = "image";
    public static final String EXTRRA_ID = "id";

    private FloatingActionButton mActionButton;


    String EventID;

    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_in_event);

        getSupportActionBar().setTitle("people in event");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mCardView = (CardView) findViewById(R.id.card_view);
        m_data_list = new ArrayList<>();


        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new CustomAdapter(this,m_data_list);
        mRecyclerView.setAdapter(mAdapter);

        mProgressDialog = new ProgressDialog(this);



       mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(mGridLayoutManager.findLastCompletelyVisibleItemPosition() == m_data_list.size()-1){
                    load_data_from_server(m_data_list.get(m_data_list.size()-1).getmId());
                }

            }
        });

        mActionButton  = (FloatingActionButton) findViewById(R.id.fab);
        mActionButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        EventID = intent.getStringExtra("eventId");
        String.valueOf(Integer.parseInt(EventID));
        load_data_from_server(Integer.parseInt(EventID));
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAlpha(0);
        progressDialog.setMessage("Loading users info ...");
        progressDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                progressDialog.dismiss();
                recyclerView.setAlpha(1);
            }
        },4500);
    }

    private void load_data_from_server(final int event_id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                MyData data  = null;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://tower.site88.net/UsersInEvent.php?event_id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                         data = new MyData(object.getInt("user_id"),event_id,object.getString("name"),
                               object.getString("role"),object.getString("birthday"),object.getString("phone_number"),
                                object.getString("address") ,object.getString("image"));


                        m_data_list.add(data);

                    }

                    Collections.sort(m_data_list, new Comparator<MyData>() {
                        @Override
                        public int compare(MyData myDataA, MyData myDataB) {
                           return myDataA.getmRole().compareToIgnoreCase(myDataB.getmRole());
                        }
                    });
                    response.body().close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
            }
        };

        task.execute(event_id);
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(UsersInEventActivity.this, MainAppScreenActivity.class));
    }
}
