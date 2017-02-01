package com.example.ofir.bopofinal.myRides.DriverTab;

import java.util.ArrayList;

/**
 * Created by ofir on 1/25/2017.
 */
public class rideData {



    int ride_id, user_id, event_id;
    String start_location, max_people, car_model, car_color, car_size, event_name, pick_up_location, event_locationer;
    private int mType;
    private com.example.ofir.bopofinal.myRides.DriverTab.rideData rideData;
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


    public String getPick_up_location() {
        return pick_up_location;
    }

    public void setPick_up_location(String pick_up_location) {
        this.pick_up_location = pick_up_location;
    }

    public rideData(com.example.ofir.bopofinal.myRides.DriverTab.rideData rideData, ArrayList<String> event_location) {
        this.event_location = event_location;
        this.rideData = rideData;

    }

    public rideData(int ride_id, int user_id, int event_id, String pick_up_location, String event_locationer) {
        this.ride_id = ride_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.pick_up_location = pick_up_location;
        this.event_locationer  = event_locationer;
    }


    public com.example.ofir.bopofinal.myRides.DriverTab.rideData getRideData() {
        return rideData;
    }

    public void setRideData(com.example.ofir.bopofinal.myRides.DriverTab.rideData rideData) {
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

    public String getEvent_locationer() {
        return event_locationer;
    }

    public void setEvent_locationer(String event_locationer) {
        this.event_locationer = event_locationer;
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