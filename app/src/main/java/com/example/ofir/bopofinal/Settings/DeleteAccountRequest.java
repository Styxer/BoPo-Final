package com.example.ofir.bopofinal.Settings;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 04-Jan-17.
 */

public class DeleteAccountRequest extends StringRequest {
    private static final String DELETE_ACCOUNT_REQUEST_URL = "http://tower.site88.net/DeleteAccount.php";
    private Map<String, String> params;

    public DeleteAccountRequest(String user_id, Response.Listener<String> listener) {
        super(Method.POST, DELETE_ACCOUNT_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put ("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
