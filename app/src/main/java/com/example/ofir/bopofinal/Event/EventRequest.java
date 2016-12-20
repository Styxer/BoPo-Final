package com.example.ofir.bopofinal.Event;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by ofir on 12/19/2016.
 */
public class EventRequest extends StringRequest {

    private static final String CREATE_REQUEST__URL = "http://cellularguide.info/cellularguide.info/offir/creteNewEvent.php";
    private Map<String, String> params;

    public EventRequest(String user_id , Response.Listener<String> listener) {
        super(Method.POST, CREATE_REQUEST__URL, listener, null);
        params.put("user_id",user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
