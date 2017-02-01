package com.example.ofir.bopofinal.myRides.DriverTab;

/**
 * Created by ofir on 1/27/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.myRides.Passengers.PassengersActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class driverAdapter extends RecyclerView.Adapter<driverAdapter.MyViewHolder>  {

    private static Context context;
    private List<com.example.ofir.bopofinal.myRides.DriverTab.rideData> my_data;
    private com.example.ofir.bopofinal.myRides.DriverTab.rideData rideData;



    public driverAdapter(Context context, List<com.example.ofir.bopofinal.myRides.DriverTab.rideData> my_data) {
        this.context = context;
        this.my_data = my_data;





    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view


                View itemView  = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ride_card_item, parent, false);

              //  itemView.setOnClickListener(this);
                return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.pickUpLocation.setText(my_data.get(position).getRideData().getStart_location());
        holder.carModel.setText(my_data.get(position).getRideData().getCar_model());
        holder.carColor.setText(my_data.get(position).getRideData().getCar_color());
        holder.carSize.setText(my_data.get(position).getRideData().getCar_size());
       // LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();
       // holder.load_data_from_server(loggedInUserService.getM_id(),"http://tower.site88.net/getRideListEventName.php?user_id=",0);


        holder.eventLocation.setText(my_data.get(position).getEvent_location().get(position));
        holder.initializeMapView();
        holder.latLng =  holder.getLocationFromAddress(context,my_data.get(position).getEvent_location().get(position));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PassengersActivity.class);
                rideData = my_data.get(position).getRideData();
                Log.i("ridID", String.valueOf(rideData.getRide_id()));
                i.putExtra("rideID",String.valueOf(rideData.getRide_id()));
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
    public int getItemViewType(int position) {
        if (my_data != null) {
            com.example.ofir.bopofinal.myRides.DriverTab.rideData object = my_data.get(position);
            if (object != null) {
                return object.getmType();
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }







    public static class MyViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        protected CardView mCardView;
        protected TextView eventLocation, pickUpLocation, carModel,carColor ,carSize;
         GoogleMap mMap;
         MapView map_view;
        public LatLng latLng;


        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            eventLocation = (TextView) v.findViewById(R.id.tvCardRideEventLocation);
            pickUpLocation = (TextView) v.findViewById(R.id.tvCardRidePickUpLocation);
            carModel = (TextView) v.findViewById(R.id.tvCardRideCarModel);
            carColor = (TextView) v.findViewById(R.id.tvCardRideCarColor);
            carSize = (TextView) v.findViewById(R.id.tvCardRideCarSize);
            map_view = (MapView) itemView.findViewById(R.id.mapView);


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
            mMap.getUiSettings().setAllGesturesEnabled(false);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Event in "+eventLocation.getText()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
        }


        public  LatLng getLocationFromAddress(Context context, String strAddress) {

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





    }






}
