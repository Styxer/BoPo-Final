package com.example.ofir.bopofinal.myRides.Passengers;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.PeopleInEvent.CustomAdapter;
import com.example.ofir.bopofinal.PeopleInEvent.MyData;
import com.example.ofir.bopofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PassengersActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private passengersAdapter mAdapter;
    private List<passengersData>  m_data_list;
    private CardView mCardView;

    private static ProgressDialog mProgressDialog;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);
        getSupportActionBar().setTitle("passenger");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_passengers);
        mCardView = (CardView) findViewById(R.id.passenger_card_view);
        m_data_list = new ArrayList<>();


        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new passengersAdapter(this,m_data_list);
        mRecyclerView.setAdapter(mAdapter);

        mProgressDialog = new ProgressDialog(this);
        mActionButton = (FloatingActionButton) findViewById(R.id.passengersFab);
        mActionButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        startActivity(new Intent(PassengersActivity.this, MainAppScreenActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        Intent intent = getIntent();

        String ride_id = intent.getStringExtra("rideID");

       load_data_from_server(Integer.parseInt(ride_id));

        mRecyclerView.setAlpha(0);
        progressDialog.setMessage("Loading users info ...");
        progressDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                progressDialog.dismiss();
                mRecyclerView.setAlpha(1);
            }
        },4500);
    }

    private void load_data_from_server(final int ride_id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                passengersData data  = null;
                ArrayList<String> name = new ArrayList<>();
                ArrayList<String> picUpLocation = new ArrayList<>();
                String eventName = null;
                String eventLocation = null;
                String startLocation  = null;
                JSONArray array;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://tower.site88.net/getPassengerData.php?ride_id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                     array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        String temp = object.optString("name","No Name Found");
                        String temp1 = object.optString("event_name","No event Found");
                        String temp2 = object.optString("event_location","No event location");
                        String temp3 = object.optString("pick_up_location","No pick location");
                        String temp4 = object.optString("start_location","No start location");

                        if(!temp.equals("No Name Found") || !temp1.equals("No event Found") || temp2.equals("No event location") ||
                        !temp3.equals("No pick location") || !temp4.equals("No start location")) {
                            if (!temp.equals("No Name Found")) {
                                name.add(temp);
                            }
                            if (!temp1.equals("No event Found")) {
                                eventName = temp1;
                            }
                            if (!temp2.equals("No event location")) {
                                eventLocation = temp2;
                            }
                            if (!temp3.equals("No pick location")) {
                                picUpLocation.add(temp3);
                            }
                            if (!temp4.equals("No start location")) {
                                startLocation = temp4;
                            }
                        }

                    }
                    ArrayList<String> tempNames = new ArrayList<>();
                    tempNames.add("Just me");
                    tempNames.add("Alex Baker");

                    for (int i=0; i<picUpLocation.size(); i++) {
                        data = new passengersData(getApplicationContext(),tempNames.get(i),eventName, eventLocation,picUpLocation.get(i),startLocation);
                        m_data_list.add(data);

                    }

                   /* Collections.sort(m_data_list, new Comparator<passengersData>() {
                        @Override
                        public int compare(passengersData passengersDataA, passengersData passengersDataB) {
                            return passengersDataA.getPassengerName().compareToIgnoreCase(passengersDataA.getPassengerName());
                        }
                    });*/
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

        task.execute(ride_id);
    }

}
