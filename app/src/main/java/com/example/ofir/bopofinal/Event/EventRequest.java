package com.example.ofir.bopofinal.Event;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 12/19/2016.
 */
public class EventRequest extends StringRequest {

    private static final String CREATE_REQUEST__URL = "http://tower.site88.net/event.php";
    private Map<String, String> params;

    public EventRequest(int user_id ,String event_id , Response.Listener<String> listener) {
        super(Method.POST, CREATE_REQUEST__URL, listener, null);
        params = new HashMap<>();
        params.put("user_id",user_id+"");
        params.put("event_id",event_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
