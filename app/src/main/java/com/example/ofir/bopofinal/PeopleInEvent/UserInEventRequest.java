package com.example.ofir.bopofinal.PeopleInEvent;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 1/18/2017.
 */
public class UserInEventRequest extends StringRequest {

    private static final String USER_REQUEST_URL = "http://tower.site88.net/AcceptOrRejectUserFromEvent.php";
    public static final String USER_ID  = "user_id";
    public static final String EVENT_ID = "event_id";
    public static final String CHOICE = "choice";
    private Map<String, String> params;

    public UserInEventRequest(int user_id, int event_id,int choice, Response.Listener<String> listener) {
        super(Method.POST, USER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put(USER_ID, user_id+"");
        params.put(EVENT_ID, event_id+"");
        params.put(CHOICE, choice+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

