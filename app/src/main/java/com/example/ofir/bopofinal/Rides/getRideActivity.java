package com.example.ofir.bopofinal.Rides;

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

import com.example.ofir.bopofinal.PeopleInEvent.CustomAdapter;
import com.example.ofir.bopofinal.PeopleInEvent.MyData;
import com.example.ofir.bopofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getRideActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private customAdapter mAdapter;
    private List<rideData> m_data_list;
    private CardView mCardView;
    private MyData mData;


    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ride);


        mRecyclerView = (RecyclerView) findViewById(R.id.ride_recycler_view);
        mCardView = (CardView) findViewById(R.id.ride_card_view);
        m_data_list = new ArrayList<>();


        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new customAdapter(this,m_data_list);
        mRecyclerView.setAdapter(mAdapter);

        mActionButton  = (FloatingActionButton) findViewById(R.id.rideFab);
        mActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        load_data_from_server(2,44);
    }

    private void load_data_from_server(final int user_id, final int event_id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                rideData data;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://tower.site88.net/getRide.php?event_id="+integers[0]+"&user_id="+integers[1])
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        data = new rideData(object.getInt("user_id"),object.getInt("event_id"),object.getString("role")
                                ,object.getString("start_location"), object.getString("max_people")
                                ,object.getString("car_model"),object.getString("car_color"), object.getString("car_size")
                                ,object.getString("driver_name"),object.getString("event_name") );



                        m_data_list.add(data);

                    }

                    Collections.sort(m_data_list, new Comparator<rideData>() {
                        @Override
                        public int compare(rideData rideDataA, rideData rideDataB) {
                            return rideDataA.getEventName().compareToIgnoreCase(rideDataB.getEventName());
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

        task.execute(event_id,user_id);
    }
}
