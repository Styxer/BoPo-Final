package com.example.ofir.bopofinal.CreateNewEvent;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ofir on 12/7/2016.
 */
public class fetch_categories {

    private static final String categories_URL = "http://tower.site88.net/fetch_categories.php";
    private static final String JSON_ARRAY = "result";
    private JSONArray result;


    public void  getData(final Context context, final ArrayList<String> categories, final Spinner categories_selector) {
        StringRequest stringRequest = new StringRequest(categories_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            result = jsonObject.getJSONArray(JSON_ARRAY);
                            getCategories(result,categories, categories_selector,context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void getCategories(JSONArray jsonArray,ArrayList<String> categories, Spinner categories_selector,Context context){
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                categories.add(jsonObject.getString("category_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        categories.add("Choose category");
        Collections.reverse(categories);
        categories_selector.setAdapter(new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, categories));

    }
}
