package com.example.ofir.bopofinal.mapUtility;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.RoutingListener;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ofir on 2/1/2017.
 */
public class computerRouteUtility implements RoutingListener {

    private GoogleMap mMap;
    private Context context;
    private static final int[] COLORS = new int[]{R.color.colorPrimary,R.color.primary,R.color.colorPrimaryDark,R.color.colorAccent,Color.CYAN};
    LatLng start;
    LatLng end ;
    private List<Polyline> polylines;

    public computerRouteUtility(GoogleMap mMap, Context context, List<Polyline> polylines, LatLng start, LatLng end){
        this.mMap = mMap;
        this.context = context;
        this.polylines = polylines;
        this.start = start;
        this.end = end;

    }




    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRoutingStart() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);

        mMap.moveCamera(center);


        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(context.getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            // Toast.makeText(getContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        //options.icon(BitmapDescriptorFactory.fromResource(R.drawable.cast_ic_notification_small_icon));
        mMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(end);
        // options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
        mMap.addMarker(options);
        List<LatLng> myList = new ArrayList<>();
        myList.add(start);
        myList.add(end);
        LatLng middle = utility.computeCentroid(myList);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(middle,8));



    }

    @Override
    public void onRoutingCancelled() {

    }
}
