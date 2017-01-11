package com.example.ofir.bopofinal.CreateNewEvent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 12/15/2016.
 */
public class CreateNewEventRequest extends StringRequest {

    private static final String CREATE_NEW_EVENT__URL = "http://tower.site88.net/createNewEvent.php";
    private Map<String, String> params;

    public CreateNewEventRequest(int choice,String title, String description, String date, String time, String location,
                                 String maxParticipants, String category, String ack,
                                 String moderator_id, Response.Listener<String> listener) {
        super(Method.POST,CREATE_NEW_EVENT__URL, listener, null);
        params = new HashMap<>();
        params.put("choice", choice+"");
        params.put("event_name", title);
        params.put("event_description", description);
        params.put("event_date", date);
        params.put("event_time", time);
        params.put("max_members", maxParticipants);
        params.put("ack_needed", ack);
        params.put("event_location", location);
        params.put("category_name", category);
        params.put("moderator_id",moderator_id);
    }




    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
