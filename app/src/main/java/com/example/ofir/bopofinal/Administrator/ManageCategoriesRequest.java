package com.example.ofir.bopofinal.Administrator;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ofir.bopofinal.Events.EventDetails;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.lang.String;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 27-Dec-16.
 */

public class ManageCategoriesRequest extends JsonArrayRequest {
    public static final String UTF_8 = "UTF-8";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED_CHARSET = "application/x-www-form-urlencoded; charset=";
    ArrayList<CategoryDetails> arrayList = new ArrayList<>();
    final Map<String, String> params = new HashMap<String, String>();

    public ManageCategoriesRequest(String json_url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, json_url, null, listener, errorListener);
    }

    @Override
    public byte[] getBody()
    {
        return encodeParams(params);
    }

    private byte[] encodeParams(Map<String, String> params)
    {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), UTF_8));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), UTF_8));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(UTF_8);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + UTF_8, uee);
        }
    }

    public String getBodyContentType()
    {
        return APPLICATION_X_WWW_FORM_URLENCODED_CHARSET + UTF_8;
    }


}
