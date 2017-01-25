package com.example.ofir.bopofinal.myRides;

/**
 * Created by ofir on 1/25/2017.
 */
public class rideData {
    int ride_id, user_id, event_id;
    String start_location, max_people, car_model, car_color, car_size, event_name, event_location, driver_name;

    public rideData(int ride_id, int user_id, int event_id, String start_location, String max_people, String car_model, String car_color, String car_size, String event_name, String event_location, String driver_name) {
        this.ride_id = ride_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.start_location = start_location;
        this.max_people = max_people;
        this.car_model = car_model;
       this.car_color = car_color;
        this.car_size = car_size;
        this.event_name = event_name;
        this.event_location = event_location;
        this.driver_name = driver_name;
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

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
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

    public String getEvent_location() {
        return event_location;
    }

    public String getDriver_name() {
        return driver_name;
    }
}
