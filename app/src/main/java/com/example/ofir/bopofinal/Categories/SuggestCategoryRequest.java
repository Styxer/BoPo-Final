package com.example.ofir.bopofinal.Categories;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Alonka on 02-Dec-16.
 */

public class SuggestCategoryRequest extends StringRequest{
    private static final String SUGGEST_CATEGORY_REQUEST_URL = "http://tower.site88.net/SuggestCategory.php";
    private Map<String, String> params;

    public SuggestCategoryRequest(String category_name, int user_id, Response.Listener<String> listener) {
        super(Method.POST, SUGGEST_CATEGORY_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("category_name", category_name);
        params.put ("user_id", user_id +"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
