package com.example.ofir.bopofinal.myRides.Passenger;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class passengerActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private Intent mIntent;
    private GoogleMap mMap;
    private LatLng latLngPickUp;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentPassenger);
        mapFragment.getMapAsync(this);

        mActionButton  = (FloatingActionButton) findViewById(R.id.passengerFab);
        mActionButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntent = getIntent();
        Bundle bundle = getIntent().getParcelableExtra("bundle");
       // String pickUpLocation = mIntent.getStringExtra("pickUp");
         latLngPickUp = bundle.getParcelable("event");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(latLngPickUp).title("test"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngPickUp,12));

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

    @Override
    public void onClick(View view) {
        startActivity(new Intent(passengerActivity.this, MainAppScreenActivity.class));
    }
}
