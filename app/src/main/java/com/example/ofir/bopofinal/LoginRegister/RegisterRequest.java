package com.example.ofir.bopofinal.LoginRegister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 11/21/2016.
 */
public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://cellularguide.info/cellularguide.info/offir/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name,String email ,String dateOfBirth,String address,String phonenumber , String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("dateOfBirth", dateOfBirth);
        params.put("address", address);
        params.put("password", password);
        params.put("phonenumber", phonenumber);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
