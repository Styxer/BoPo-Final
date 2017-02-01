package com.example.ofir.bopofinal.myRides.Passenger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.mapUtility.computerRouteUtility;
import com.example.ofir.bopofinal.mapUtility.utility;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class passengerActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private Intent mIntent;
    private GoogleMap mMap;
    private LatLng latLngPickUp,  latLngEvent,  latLangStartLocation;
    private String eventName, distanceFromEvent, distanceFromDriver;
    private FloatingActionButton mActionButton;
    private TextView mEventName, mDistanceFromDriver, mDistanceFromEvent;
    private List<LatLng> mLatLangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentPassenger);
        mapFragment.getMapAsync(this);

        mEventName = (TextView) findViewById(R.id.tvPassengerEventName);
        mDistanceFromDriver = (TextView) findViewById(R.id.tvPassengerDistanceFromEvent);
        mDistanceFromEvent = (TextView) findViewById(R.id.tvPassengerDistanceFromDriver);

        mLatLangList = new ArrayList<>();

        mActionButton  = (FloatingActionButton) findViewById(R.id.passengerFab);
        mActionButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntent = getIntent();
        Bundle bundle = getIntent().getParcelableExtra("bundle");

        latLngPickUp = bundle.getParcelable("pickUp");
        latLngEvent  = bundle.getParcelable("event");
        latLangStartLocation  = bundle.getParcelable("start");

        mLatLangList.add(latLngPickUp);
        mLatLangList.add(latLngEvent);
        mLatLangList.add(latLangStartLocation);

        eventName = mIntent.getStringExtra("eventName");
        distanceFromEvent = mIntent.getStringExtra("fromEvent");
        distanceFromDriver = mIntent.getStringExtra("fromDriver");

        mEventName.setText(eventName);
        mDistanceFromEvent.setText(distanceFromEvent +" km");
        mDistanceFromDriver.setText(distanceFromDriver +" km");



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Polyline> polylines = new ArrayList<>();

        computerRouteUtility computerRouteFromPickUpToEvent = new computerRouteUtility(mMap,this,polylines,latLngPickUp,latLngEvent);
        computerRouteUtility computerRouteFromDriverToPickUp = new computerRouteUtility(mMap,this,polylines,latLngPickUp,latLangStartLocation);

        utility.route(latLngPickUp, latLngEvent,computerRouteFromPickUpToEvent);
        utility.route(latLngPickUp, latLangStartLocation,computerRouteFromDriverToPickUp);



    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(passengerActivity.this, MainAppScreenActivity.class));
    }
}
