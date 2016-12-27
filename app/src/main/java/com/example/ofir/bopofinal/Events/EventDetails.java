package com.example.ofir.bopofinal.Events;


/**
 * Created by Alonka on 14-Dec-16.
 */

public class EventDetails {
    private String event_name, event_location, event_date,event_time, event_id;

    public EventDetails(String event_name, String event_location, String event_date, String event_time, String event_id)
    {
        this.event_name = event_name;
        this.event_location = event_location;
        this.event_date = event_date;
        this.event_time = event_time;
        this.event_id = event_id;
    }

    public String getEventName(){
        return event_name;
    }

    public void setEventName(String name)
    {
        event_name = name;
    }

    public String getEventLocation(){
        return event_location;
    }

    public void setEventLocation(String location)
    {
        event_location = location;
    }

    public String getEventDate(){
        return event_date;
    }

    public void setEventDate(String date)
    {
        event_date = date;
    }

    public String getEventTime(){
        return event_time;
    }

    public void setEventTime(String time)
    {
        event_time = time;
    }

    public String getEventId(){
        return event_id;
    }

    public void setEventId(String time)
    {
        event_id = event_id;
    }
}
