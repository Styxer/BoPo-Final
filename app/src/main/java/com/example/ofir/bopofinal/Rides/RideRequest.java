package com.example.ofir.bopofinal.Rides;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 1/20/2017.
 */
public class RideRequest extends StringRequest {

    private static final String RIDE_REQUEST_URL = "http://tower.site88.net/Ride.php";
    public static final String EVENT_ID = "user_id";
    public static final String USER_ID = "event_id";
    public static final String ROLE = "role";
    public static final String START_LOCATION = "start_location";
    public static final String MAX_PEOPLE = "max_people";
    public static final String CAR_MODEL = "car_model";
    public static final String CAR_COLOR = "car_color";
    public static final String CAR_SIZE = "car_size";
    private Map<String, String> params;

    public RideRequest(int userID, int EventID, String role , String startLocation, String maxPeople,
                       String carModel, String carColor, String carSize, Response.Listener<String> listener) {
        super(Method.POST, RIDE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put(USER_ID, userID+"");
        params.put(EVENT_ID, EventID+"");
        params.put(ROLE, role);
        params.put(START_LOCATION, startLocation);
        params.put(MAX_PEOPLE, maxPeople);
        params.put(CAR_MODEL, carModel);
        params.put(CAR_SIZE, carSize);
        params.put(CAR_COLOR, carColor);




    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

