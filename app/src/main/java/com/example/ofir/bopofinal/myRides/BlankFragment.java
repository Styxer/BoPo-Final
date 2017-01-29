package com.example.ofir.bopofinal.myRides;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.PeopleInEvent.MyData;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.myRides.Passengers.PassengersActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlankFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MyAdapter mMyAdapter;
    private List<rideData> m_data_list;
    private CardView mCardView;
    private rideData mRideData;
    private ArrayList<String> data;
     ProgressDialog loadin;






    public BlankFragment() {
        // Required empty public constructor

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        mCardView = (CardView) rootView.findViewById(R.id.card_view);
        m_data_list = new ArrayList<>();
        data = new ArrayList<>();

        mGridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMyAdapter = new MyAdapter(getActivity(),m_data_list);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMyAdapter);











        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
             // final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);



    }

    @Override
    public void onStart() {
        super.onStart();
        LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();
        int user_id = loggedInUserService.getM_id();
        loadin = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);

       load_data_from_server(user_id, "http://tower.site88.net/getRideListData.php?user_id=");//data





    }

    private void load_data_from_server(final int event_id, final String url) {

        final AsyncTask<Integer,String,Void> task = new AsyncTask<Integer, String, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                rideData rideData  = null;
                rideData ride  = null;
                ArrayList<String > temp = new ArrayList<>();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                       JSONObject object = array.getJSONObject(i);


                        String string = object.optString("event_location","no");
                        if(!string.equals("no"))
                            temp.add(string);

                        else{

                            rideData = new rideData(object.getInt("ride_id"),object.getInt("user_id"),object.getInt("event_id"),
                                    object.getString("start_location"), object.getString("max_people"), object.getString("car_model"),
                                    object.getString("car_color"), object.getString("car_size"));
                        }



                    }


                        ride = new rideData(rideData,temp);

                    m_data_list.add(ride);








                      //  m_data_list.add(data);




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
                mMyAdapter.notifyDataSetChanged();
                loadin.dismiss();
                loadin.hide();
            }
        };

        task.execute(event_id);
    }



}

