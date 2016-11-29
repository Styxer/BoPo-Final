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

    public RegisterRequest(String role,String name,String email ,String dateOfBirth,String password,String phonenumber , String address, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("role", role);
        params.put("name", name);
        params.put("email", email);
        params.put("birthday", dateOfBirth);
        params.put("password", address);
        params.put("phone_number", password);
        params.put("address", phonenumber);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
