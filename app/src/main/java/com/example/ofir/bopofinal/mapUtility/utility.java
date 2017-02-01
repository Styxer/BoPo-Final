package com.example.ofir.bopofinal.mapUtility;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.directions.route.AbstractRouting;
import com.directions.route.Routing;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by ofir on 2/1/2017.
 */
public class utility {


    public static LatLng getLocationFromAddress(Context context, String strAddress) {

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

    public static LatLng computeCentroid(List<LatLng> points) {
        double latitude = 0;
        double longitude = 0;
        int n = points.size();

        for (LatLng point : points) {
            latitude += point.latitude;
            longitude += point.longitude;
        }

        return new LatLng(latitude/n, longitude/n);
    }

    public static double calculateDistance(LatLng start, LatLng end){
        Location loc1 = new Location("");
        loc1.setLatitude(start.latitude);
        loc1.setLongitude(start.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(end.latitude);
        loc2.setLongitude(end.longitude);

        return distance_between(loc1.getLatitude(),loc1.getLongitude(),loc2.getLatitude(),loc2.getLongitude());
    }

    public static double  distance_between(double lat1, double lon1, double lat2, double lon2){

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

    public static void setPolyLines(GoogleMap map, LatLng start, LatLng end, int color){
        PolylineOptions line = new PolylineOptions().add(start,end);
        line.color(color);
        map.addPolyline(line);
    }

    public static void  route(LatLng start, LatLng end, computerRouteUtility computerRouteUtility ){
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(computerRouteUtility)
                .alternativeRoutes(true)
                .waypoints(start, end)
                .build();
        routing.execute();
    }
}
