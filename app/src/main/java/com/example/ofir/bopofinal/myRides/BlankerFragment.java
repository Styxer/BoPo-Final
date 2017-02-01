package com.example.ofir.bopofinal.myRides;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.directions.route.AbstractRouting;
import com.directions.route.Routing;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.mapUtility.computerRouteUtility;
import com.example.ofir.bopofinal.mapUtility.utility;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankerFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    private FragmentActivity myContext;
    ProgressDialog loadin;
    ArrayList<String > event_location, pick_up_location;
    List<LatLng> event_locations,pick_up_locations;
    private List<Polyline> polylines;




    public BlankerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_blanker, container, false);
        mapView = (MapView) rootView.findViewById(R.id.mapViewBlanker);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
       // mapView.getMapAsync(this);//
        event_location = new ArrayList<>();
        pick_up_location = new ArrayList<>();
        event_locations = new ArrayList<>();
        pick_up_locations = new ArrayList<>();
         polylines = new ArrayList<>();



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();
        int user_id = loggedInUserService.getM_id();
        loadin = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...", false, false);
        load_data_from_server(user_id, "http://tower.site88.net/getPassengerListData.php?user_id=");//data
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



      // for (int i = 0; i < event_locations.size() ; i++) {
            event_locations.add(utility.getLocationFromAddress(getContext(), event_location.get(0)));
        //    mMap.addMarker(new MarkerOptions().position(event_locations.get(0)).title(1 + " event in:" +event_location.get(0)));
      // }
        for (int i = 0; i < pick_up_location.size() ; i++) {
            pick_up_locations.add(utility.getLocationFromAddress(getContext(), pick_up_location.get(i)));
          //  mMap.addMarker(new MarkerOptions().position(pick_up_locations.get(i)).title(i+1 +" :pick up from: "+pick_up_location.get(i)));
        }
        computerRouteUtility computerRouteUtility = new computerRouteUtility(mMap,getContext(),polylines,pick_up_locations.get(0),event_locations.get(0));

        for (int i = 0 ;i<pick_up_location.size() - 1 ; i++)
          utility.route(pick_up_locations.get(i), event_locations.get(i),computerRouteUtility);


    }




    private void load_data_from_server(final int event_id, final String url) {

        final AsyncTask<Integer,String,Void> task = new AsyncTask<Integer, String, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

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
                        String string1 = object.optString("pick_up_location","no");

                        if(!string.equals("no"))
                            event_location.add(string);
                        if(!string1.equals("no"))
                            pick_up_location.add(string1);


                    }

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
                loadMap();
                loadin.dismiss();
                loadin.hide();
            }
        };

        task.execute(event_id);
    }

    public void loadMap(){

        mapView.getMapAsync(this);
    }


}
