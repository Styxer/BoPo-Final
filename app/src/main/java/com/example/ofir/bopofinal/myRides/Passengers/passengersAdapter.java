package com.example.ofir.bopofinal.myRides.Passengers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.mapUtility.computerRouteUtility;
import com.example.ofir.bopofinal.mapUtility.utility;
import com.example.ofir.bopofinal.myRides.Passenger.passengerActivity;
import com.example.ofir.bopofinal.myRides.rideData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ofir on 1/28/2017.
 */
public class passengersAdapter extends RecyclerView.Adapter<passengersAdapter.MyViewHolder> {

    private static Context context;
    private static List<passengersData> my_data;

    private passengersData passengersData;
    int fromDriver, fromEvent;


    public passengersAdapter(Context context, List<passengersData> my_data) {
        this.context = context;
        this.my_data = my_data;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.passengers_card, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        holder.userName.setText(my_data.get(position).getPassengerName());
       // holder.eventName.setText(my_data.get(position).getEventName());
        holder.initializeMapView();

        //init latlang
/*        holder.latLngEvent =  //holder.getLocationFromAddress(context,my_data.get(position).getEventLocation());
        holder.latLanPickUp = holder.getLocationFromAddress(context,my_data.get(position).getPickUpLocation());
        holder.latLangStartLocation = holder.getLocationFromAddress(context,my_data.get(position).getStartLocation());*/
        holder.latLngEvent = utility.getLocationFromAddress(context,my_data.get(position).getEventLocation());
        holder.latLangStartLocation =  utility.getLocationFromAddress(context,my_data.get(position).getPickUpLocation());
        holder.latLangStartLocation = utility.getLocationFromAddress(context,my_data.get(position).getStartLocation());

        Random r = new Random();
        fromDriver = r.nextInt(50 - 14 + 1) + 14;
        fromEvent = r.nextInt(65 - 14 + 1) + 14;
        holder.distanceFromDriver.setText(fromDriver+ " km");
        holder.distanceFromEvent.setText(fromEvent + "km");


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, passengerActivity.class);
                passengersData = my_data.get(position);
                Bundle args = new Bundle();
                args.putParcelable("pickUp", passengersData.getLatLanPickUp());
                args.putParcelable("event", passengersData.getLatLngEvent());
                args.putParcelable("start", passengersData.getLatLangStartLocation());
                i.putExtra("bundle", args);
              //  i.putExtra("driverName",String.valueOf(passengersData.get()));
                i.putExtra("eventName",String.valueOf(passengersData.getEventName()));
                i.putExtra("fromEvent",String.valueOf(fromEvent));
                i.putExtra("fromDriver",String.valueOf(fromDriver));
                context.startActivity(i);

            }
        });
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        if (holder.mMap != null) {
            holder.mMap.clear();
            holder.mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        private TextView userName, distanceFromDriver, distanceFromEvent;
        protected CardView mCardView;
        GoogleMap mMap;
        MapView map_view;
        public LatLng latLngEvent, latLanPickUp, latLangStartLocation;
        List<LatLng> myList;


        public MyViewHolder(View v) {

            super(v);

            userName = (TextView) v.findViewById(R.id.tvPssengersName);
            distanceFromEvent = (TextView) v.findViewById(R.id.tvPassengersDistanceFromEvent);
            distanceFromDriver = (TextView) v.findViewById(R.id.tvPssengersDistacneFromDriver);
            map_view = (MapView) v.findViewById(R.id.passengersMapView);
            mCardView = (CardView) v.findViewById(R.id.passenger_card_view);

        }


        public void initializeMapView() {
            if (map_view != null) {
                map_view.onCreate(null);
                map_view.onResume();
                map_view.getMapAsync(this);

            }
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(context.getApplicationContext());
            mMap = googleMap;
            //LatLng sydney = new LatLng(-34, 151);

            // mMap.getUiSettings().setAllGesturesEnabled(false);
            GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
            //markers


            List<Polyline> polylines = new ArrayList<>();
            computerRouteUtility computerRouteUtility = new computerRouteUtility(mMap, context, polylines,
                    my_data.get(getAdapterPosition()).getLatLanPickUp(), my_data.get(getAdapterPosition()).getLatLngEvent());

            computerRouteUtility computerRouteUtility2 = new computerRouteUtility(mMap, context, polylines,
                    my_data.get(getAdapterPosition()).getLatLanPickUp(), my_data.get(getAdapterPosition()).getLatLangStartLocation());

            utility.route(my_data.get(getAdapterPosition()).getLatLanPickUp(), my_data.get(getAdapterPosition()).getLatLngEvent(), computerRouteUtility);
            utility.route(my_data.get(getAdapterPosition()).getLatLanPickUp(), my_data.get(getAdapterPosition()).getLatLangStartLocation(), computerRouteUtility2);
        }

    }






}
