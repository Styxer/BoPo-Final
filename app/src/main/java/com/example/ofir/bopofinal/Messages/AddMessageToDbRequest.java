package com.example.ofir.bopofinal.Messages;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 09-Jan-17.
 */

public class AddMessageToDbRequest extends StringRequest {
    private static final String SEND_MESSAGE_REQUEST_URL = "http://tower.site88.net/SendMessageToDb.php";
    private Map<String, String> params;

    public AddMessageToDbRequest(String user_id, String sender_id, String event_id, String title, String description, Response.Listener<String> listener) {
        super(Method.POST, SEND_MESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put ("user_id", user_id);
        params.put ("sender_id", sender_id);
        params.put ("event_id", event_id);
        params.put ("title", title);
        params.put ("description", description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

