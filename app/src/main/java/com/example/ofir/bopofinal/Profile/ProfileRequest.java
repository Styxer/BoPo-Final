package com.example.ofir.bopofinal.Profile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 02-Jan-17.
 */

public class ProfileRequest extends StringRequest {
    private static final String SHOW_PROFILE_REQUEST_URL = "http://tower.site88.net/ChangeProfile.php";
    private Map<String, String> params;

    public ProfileRequest(String user_id,String name, String email, String birthday, String phone, String address, Response.Listener<String> listener) {
        super(Method.POST, SHOW_PROFILE_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put ("user_id", user_id);
        params.put ("name", name);
        params.put ("email", email);
        params.put ("birthday", birthday);
        params.put ("phone_number", phone);
        params.put ("address", address);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
