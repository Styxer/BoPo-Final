package com.example.ofir.bopofinal.Administrator;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 28-Dec-16.
 */

public class ApproveCategoriesRequest extends StringRequest {
    private static final String SUGGEST_CATEGORY_REQUEST_URL = "http://tower.site88.net/ApproveCategoryRequest.php";
    private Map<String, String> params;

    public ApproveCategoriesRequest(String category_name,Response.Listener<String> listener) {
        super(Method.POST, SUGGEST_CATEGORY_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put ("category_name", category_name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

