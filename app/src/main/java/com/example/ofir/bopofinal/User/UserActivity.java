package com.example.ofir.bopofinal.User;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ofir.bopofinal.Event.EventActivity;
import com.example.ofir.bopofinal.Events.DisplayEventsActivity;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.PeopleInEvent.CustomAdapter;
import com.example.ofir.bopofinal.PeopleInEvent.MyData;
import com.example.ofir.bopofinal.PeopleInEvent.UsersInEventActivity;
import com.example.ofir.bopofinal.Profile.DownloadImageFromDB;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UserActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback {

    private TextView mTvUserName, mTvUserRole, mTvUserBirthDay;
    private ImageView mIvUserImage;
    private Button mBuserCall;
    String mPhone_number,mAddress;
    private GoogleMap mGoogleMap;
    private static LatLng mLatLang;
    private MyData mData;

    private FloatingActionButton mActionButton;

    String mName;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        mTvUserName = (TextView) findViewById(R.id.tvUserName);
       mTvUserRole = (TextView) findViewById(R.id.tvUserRole);
        mTvUserBirthDay = (TextView) findViewById(R.id.tvUserBday);

        mIvUserImage = (ImageView) findViewById(R.id.ivUserImage);

        mBuserCall = (Button) findViewById(R.id.bUserCall);
        mBuserCall.setOnClickListener(this);





        mIntent = getIntent();
        mPhone_number = mIntent.getStringExtra(UsersInEventActivity.EXTRRA_PHONE_NUMBER);

        mName = mIntent.getStringExtra(UsersInEventActivity.EXTRA_NAME);
       // mTvUserName.setText(title);
        String birthday =  mIntent.getStringExtra(UsersInEventActivity.EXTRA_BIRTHDAY);
        String role = mIntent.getStringExtra(UsersInEventActivity.EXTRRA_ROLE);


        if(role.equals("admin")){
            role  = "<b>" + role+ "</b>";
            Log.i("RoleAdmin",role);
        }else if (role.equals("waiting for ack")){
            mTvUserRole.setTextColor(Color.RED);
            Log.i("RoleWaitingForAck",role);
        }

        mTvUserName.setText(mName);
        mTvUserRole.setText(role);
        mTvUserBirthDay.setText(birthday);


        mBuserCall.setText("call " + mName);

        SupportMapFragment mapFragment  =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.UserMap);
        mapFragment.getMapAsync(this);

        mAddress  = mIntent.getStringExtra(UsersInEventActivity.EXTRA_ADDRESS);
        mLatLang =   getLocationFromAddress(this,mAddress);

        mIvUserImage = (ImageView) findViewById(R.id.ivUserImage);
        String image =  mIntent.getStringExtra(CustomAdapter.EXTRRA_IMAGE);
        String user_id = mIntent.getStringExtra("id");

        if(image.equals("profilePictureOfUserId" + user_id + ".JPG"))
            new DownloadImageFromDB(mIvUserImage).execute("http://tower.site88.net/pictures/profilePictureOfUserId" + user_id + ".JPG");
        else if(image.equals("DefaultPicture.JPG"))
            new DownloadImageFromDB(mIvUserImage).execute("http://tower.site88.net/pictures/DefaultPicture.JPG");

        mActionButton  = (FloatingActionButton) findViewById(R.id.fabUser);
        mActionButton.setOnClickListener(this);

    }






    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.bUserCall:
              Intent intent = new Intent(Intent.ACTION_CALL);
              intent.setData(Uri.parse("tel:" + mPhone_number));


              if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                  ActivityCompat.requestPermissions(UserActivity.this,
                          new String[]{Manifest.permission.CALL_PHONE},
                          1);

              }
              startActivity(intent);
              break;
          case R.id.fabUser:
              startActivity(new Intent(UserActivity.this, MainAppScreenActivity.class));
              break;

      }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.userLinearLayout);
        linearLayout.setAlpha(0);
        progressDialog.setMessage("Loading "+mName +" info ...");
        progressDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                progressDialog.dismiss();
                linearLayout.setAlpha(1);
            }
        },4500);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.addMarker(new MarkerOptions().position(mLatLang).title("test"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLang,13));

    }
    public LatLng getLocationFromAddress(Context context,String strAddress) {

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
