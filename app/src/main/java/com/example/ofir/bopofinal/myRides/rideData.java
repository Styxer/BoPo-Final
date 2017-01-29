package com.example.ofir.bopofinal.myRides;

import java.util.ArrayList;

/**
 * Created by ofir on 1/25/2017.
 */
public class rideData {



    int ride_id, user_id, event_id;
    String start_location, max_people, car_model, car_color, car_size, event_name;
    private int mType;
    private rideData rideData;
    private ArrayList<String> event_location;


    public rideData(int ride_id, int user_id, int event_id, String start_location, String max_people, String car_model, String car_color, String car_size) {
        this.ride_id = ride_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.start_location = start_location;
        this.max_people = max_people;
        this.car_model = car_model;
        this.car_color = car_color;
        this.car_size = car_size;
       // this.event_location = event_location;


    }





    public rideData(rideData rideData, ArrayList<String> event_location) {
        this.event_location = event_location;
        this.rideData = rideData;

    }

    public com.example.ofir.bopofinal.myRides.rideData getRideData() {
        return rideData;
    }

    public void setRideData(com.example.ofir.bopofinal.myRides.rideData rideData) {
        this.rideData = rideData;
    }

    public ArrayList<String> getEvent_location() {
        return event_location;
    }

    public void setEvent_location(ArrayList<String> event_location) {
        this.event_location = event_location;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }



    public int getmType() {
        return mType;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public void setMax_people(String max_people) {
        this.max_people = max_people;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public void setCar_size(String car_size) {
        this.car_size = car_size;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }




    public int getRide_id() {
        return ride_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getStart_location() {
        return start_location;
    }

    public String getMax_people() {
        return max_people;
    }

    public String getCar_model() {
        return car_model;
    }

    public String getCar_color() {
        return car_color;
    }

    public String getCar_size() {
        return car_size;
    }

    public String getEvent_name() {
        return event_name;
    }



}
