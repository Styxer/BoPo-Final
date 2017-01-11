package com.example.ofir.bopofinal.Administrator;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 28-Dec-16.
 */

public class DeleteCategoriesRequest extends StringRequest {
    private static final String SUGGEST_CATEGORY_REQUEST_URL = "http://tower.site88.net/DeleteCategoryRequest.php";
    private Map<String, String> params;

    public DeleteCategoriesRequest(String request_id, String category_name,String user_id, Response.Listener<String> listener) {
        super(Method.POST, SUGGEST_CATEGORY_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put ("request_id", request_id);
        params.put ("category_name", category_name);
        params.put ("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}