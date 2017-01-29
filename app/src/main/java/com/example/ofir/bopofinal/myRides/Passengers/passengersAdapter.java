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
        holder.latLngEvent =  holder.getLocationFromAddress(context,my_data.get(position).getEventLocation());
        holder.latLanPickUp = holder.getLocationFromAddress(context,my_data.get(position).getPickUpLocation());
        holder.latLangStartLocation = holder.getLocationFromAddress(context,my_data.get(position).getStartLocation());
        Random r = new Random();
        holder.distanceFromDriver.setText(r.nextInt(100 - 14 + 1) + 14+ " km");
        holder.distanceFromEvent.setText(r.nextInt(100 - 14 + 1) + 14+ " km");






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
                i.putExtra("userName",String.valueOf(passengersData.getPassengerName()));
                i.putExtra("eventName",String.valueOf(passengersData.getEventName()));
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



    public static class MyViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback{

        private TextView userName, distanceFromDriver ,distanceFromEvent;
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


            mMap.addMarker(new MarkerOptions().position(my_data.get(getAdapterPosition()).getLatLanPickUp())
                    .title("pick up: " + my_data.get(getAdapterPosition()).getPassengerName() + " from "
                            + my_data.get(getAdapterPosition()).getPickUpLocation()));
            mMap.addMarker((new MarkerOptions().position(my_data.get(getAdapterPosition()).getLatLangStartLocation()))
                    .title("ride start at: " + my_data.get(getAdapterPosition()).getStartLocation()));
            mMap.addMarker(new MarkerOptions().position(my_data.get(getAdapterPosition()).getLatLngEvent())
                    .title("event location: " + my_data.get(getAdapterPosition()).getEventName()));




           //lines
           setPolyLines(mMap,my_data.get(getAdapterPosition()).getLatLangStartLocation(),my_data.get(getAdapterPosition()).getLatLanPickUp(),Color.BLUE);
           setPolyLines(mMap,my_data.get(getAdapterPosition()).getLatLanPickUp(),my_data.get(getAdapterPosition()).getLatLngEvent(),Color.RED);

           //computeCentroid
           myList = Arrays.asList(my_data.get(getAdapterPosition()).getLatLangStartLocation(), my_data.get(getAdapterPosition()).getLatLngEvent(),
                   my_data.get(0).getLatLanPickUp(),my_data.get(1).getLatLanPickUp());
           LatLng middle = computeCentroid(myList);

           //calcDistance
         /*   distances = new ArrayList<>();
          for (int i = 0; i < myList.size() - 2 ;i++){
                    Log.i("dagdsdsgsd", String.valueOf(myList.get(i+2)));
                   distances.add(i, calculateDistance(myList.get(0),myList.get(i+2)));//distance between drive to pickup
           }
          // distanceFromDriver.setText(distances.get(0) + " km");*/

           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(middle, 9));
       }


        public void setPolyLines(GoogleMap map,LatLng start, LatLng end,int color){
            PolylineOptions line = new PolylineOptions().add(start,end);
            line.color(color);
            map.addPolyline(line);
        }




        public LatLng getLocationFromAddress(Context context, String strAddress) {

            Geocoder coder = new Geocoder(context);
            List<Address> address;
            LatLng p1 = null;

            try {
                address = coder.getFromLocationName(strAddress, 5);
                if (address == null) {
                    return null;
                }
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();

                p1 = new LatLng(location.getLatitude(), location.getLongitude() );

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return p1;
        }

        private LatLng computeCentroid(List<LatLng> points) {
            double latitude = 0;
            double longitude = 0;
            int n = points.size();

            for (LatLng point : points) {
                latitude += point.latitude;
                longitude += point.longitude;
            }

            return new LatLng(latitude/n, longitude/n);
        }

        private double calculateDistance(LatLng start, LatLng end){
            Location loc1 = new Location("");
            loc1.setLatitude(start.latitude);
            loc1.setLongitude(start.longitude);

            Location loc2 = new Location("");
            loc2.setLatitude(end.latitude);
            loc2.setLongitude(end.longitude);

            return distance_between(loc1.getLatitude(),loc1.getLongitude(),loc2.getLatitude(),loc2.getLongitude());
        }

        double distance_between(double lat1 , double lon1, double lat2, double lon2)
        {
            //float results[] = new float[1];
    /* Doesn't work. returns inconsistent results
    Location.distanceBetween(
            l1.getLatitude(),
            l1.getLongitude(),
            l2.getLatitude(),
            l2.getLongitude(),
            results);
            */

            double R = 6371; // km
            double dLat = (lat2-lat1)*Math.PI/180;
            double dLon = (lon2-lon1)*Math.PI/180;
            lat1 = lat1*Math.PI/180;
            lat2 = lat2*Math.PI/180;

            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double d = R * c * 1000;



            return d;
        }
    }






}
