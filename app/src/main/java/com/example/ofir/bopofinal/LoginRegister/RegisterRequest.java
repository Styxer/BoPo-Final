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

    private static final String REGISTER_REQUEST_URL = "http://tower.site88.net/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String role,String name,String email ,String dateOfBirth,String password,String phonenumber , String address, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("role", role);
        params.put("name", name);
        params.put("email", email);
        params.put("birthday", dateOfBirth);
        params.put("password", password);
        params.put("phone_number", phonenumber);
        params.put("address", address);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
