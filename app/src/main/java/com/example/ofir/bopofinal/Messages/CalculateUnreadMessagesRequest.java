package com.example.ofir.bopofinal.Messages;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 11-Jan-17.
 */

public class CalculateUnreadMessagesRequest extends StringRequest {
    private static final String CALCULATE_UNREAD_MESSAGE_REQUEST_URL = "http://tower.site88.net/CountUnreadMessages.php";
    private Map<String, String> params;

    public CalculateUnreadMessagesRequest(String user_id, Response.Listener<String> listener) {
        super(Method.POST, CALCULATE_UNREAD_MESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put ("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}