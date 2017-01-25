package com.example.ofir.bopofinal.Rides;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 1/20/2017.
 */
public class getRideRequest extends StringRequest {

    private static final String RIDE_REQUEST_URL = "http://tower.site88.net/getARide.php";
    public static final String USER_ID = "user_id";
    public static final String EVENT_ID = "event_id";
    public static final String DRIVER_ID  = "driver_id";
    public static final String PICK_UP_LOCATION = "pick_up_location";

    private Map<String, String> params;

    public getRideRequest(int userID, int EventID , int  driverID, String pickUpLocation
            , Response.Listener<String> listener) {
        super(Method.POST, RIDE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put(USER_ID, userID+"");
        params.put(EVENT_ID, EventID+"");
        params.put(DRIVER_ID, driverID+"");
        params.put(PICK_UP_LOCATION, pickUpLocation);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

