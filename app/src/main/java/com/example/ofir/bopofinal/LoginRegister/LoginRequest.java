package com.example.ofir.bopofinal.LoginRegister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ofir on 11/21/2016.
 */
public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://tower.site88.net/login.php";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put(EMAIL, email);
        params.put(PASSWORD, password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
