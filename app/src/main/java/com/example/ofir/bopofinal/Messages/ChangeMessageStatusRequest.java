package com.example.ofir.bopofinal.Messages;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 10-Jan-17.
 */

public class ChangeMessageStatusRequest extends StringRequest {
    private static final String CHANGE_MESSAGE_STATUS_REQUEST_URL = "http://tower.site88.net/ChangeMessageStatus.php";
    private Map<String, String> params;

    public ChangeMessageStatusRequest(String message_id, String new_status, Response.Listener<String> listener) {
        super(Method.POST, CHANGE_MESSAGE_STATUS_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put ("message_id", message_id);
        params.put ("new_status", new_status);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}