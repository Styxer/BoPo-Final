package com.example.ofir.bopofinal.Search;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.lang.String;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Alonka on 22-Dec-16.
 */

public class SearchRequest extends JsonArrayRequest{
    public static final String UTF_8 = "UTF-8";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED_CHARSET = "application/x-www-form-urlencoded; charset=";
    final Map<String, String> params = new HashMap<String, String>();

    public SearchRequest(String str, String json_url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, json_url, null, listener, errorListener);
        if (SearchActivity.tableFlag==1) //for people
        params.put("name", str);
        else if (SearchActivity.tableFlag==2) //for events
            {
                if (SearchActivity.columnFlag == 1) //for event_name
                    {
                        params.put("event_name", str);
                    }
                else if(SearchActivity.columnFlag == 2) //for category
                    {
                        params.put("category_name", str);
                    }
                else if(SearchActivity.columnFlag == 3) //for location
                    {
                        params.put("event_location", str);
                    }
                else if(SearchActivity.columnFlag == 4) //for date
                {
                    params.put("event_date", str);
                }
            }
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
