package com.example.ofir.bopofinal.User;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ofir.bopofinal.PeopleInEvent.UsersInEventActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class UserActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback {

    private TextView mTvUserName, mTvUserRole, mTvUserBirthDay;
    private ImageView mIvUserImage;
    private Button mBuserCall;
    String mPhone_number,mAddress;
    private GoogleMap mGoogleMap;
    private static LatLng mLatLang;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        mTvUserName = (TextView) findViewById(R.id.tvUserName);
       mTvUserRole = (TextView) findViewById(R.id.tvUserRole);
        mTvUserBirthDay = (TextView) findViewById(R.id.tvUserBday);
      //  mtvUserPhoneNumber = (TextView) findViewById(R.id.tvUserPhoneNumber);
        //mTvUserAddress = (TextView) findViewById(R.id.tvUserAddress);


        mIvUserImage = (ImageView) findViewById(R.id.ivUserImage);

        mBuserCall = (Button) findViewById(R.id.bUserCall);
        mBuserCall.setOnClickListener(this);


        mIntent = getIntent();
        mPhone_number = mIntent.getStringExtra(UsersInEventActivity.EXTRRA_PHONE_NUMBER);
        String name = mIntent.getStringExtra(UsersInEventActivity.EXTRA_TITLE);
       // mTvUserName.setText(title);
        String birthday =  mIntent.getStringExtra(UsersInEventActivity.EXTRA_BIRTHDAY);
        String role = mIntent.getStringExtra(UsersInEventActivity.EXTRRA_ROLE);

        if(role.equals("admin")){
            role  = "<b>" + role+ "</b>";
            Log.i("RoleAdmin",role);
        }else if (role.equals("waiting for ack")){
            role = "<font color='#EE0000'>red</font>";
            Log.i("RoleWaitingForAck",role);
        }

        mTvUserName.setText(name);
        mTvUserRole.setText(Html.fromHtml(role));
        mTvUserBirthDay.setText(birthday);

        //mTvUserBirthday.setText(mIntent.getStringExtra(UsersInEventActivity.EXTRA_BIRTHDAY));
//        mtvUserPhoneNumber.setText(mPhone_number);
       // mTvUserAddress.setText(intent.getStringExtra(UsersInEventActivity.EXTRA_ADDRESS));
        String url = (mIntent.getStringExtra(UsersInEventActivity.EXTRRA_IMAGE));
      /*  Bitmap bitmap = StringToBitMap(url);
        mIvUserImage.setImageBitmap(bitmap);*/

        mBuserCall.setText("call " + name);

        SupportMapFragment mapFragment  =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.UserMap);
        mapFragment.getMapAsync(this);

        mAddress  = mIntent.getStringExtra(UsersInEventActivity.EXTRA_ADDRESS);
        mLatLang =   getLocationFromAddress(this,mAddress);

    }






    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mPhone_number));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UserActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);

        }
        startActivity(intent);
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
