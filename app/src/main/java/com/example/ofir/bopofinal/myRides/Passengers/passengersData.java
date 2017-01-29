package com.example.ofir.bopofinal.myRides.Passengers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by ofir on 1/28/2017.
 */
public class passengersData {

    String passengerName, eventName , eventLocation, pickUpLocation, startLocation;
    public LatLng latLngEvent, latLanPickUp, latLangStartLocation;
    Context context;

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

    public LatLng getLatLngEvent() {
        return getLocationFromAddress(context,getEventLocation());
    }

    public LatLng getLatLanPickUp() {
        return getLocationFromAddress(context,getPickUpLocation());
    }

    public LatLng getLatLangStartLocation() {
        return getLocationFromAddress(context,getStartLocation());
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public passengersData(Context context, String passengerName, String eventName, String eventLocation, String pickUpLocation, String startLocation) {

        this.passengerName = passengerName;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.pickUpLocation = pickUpLocation;
        this.startLocation = startLocation;
        this.context = context;
    }
}
