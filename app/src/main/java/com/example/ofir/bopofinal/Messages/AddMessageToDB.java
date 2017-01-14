package com.example.ofir.bopofinal.Messages;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Alonka on 09-Jan-17.
 */

public class AddMessageToDB {
    Context ctx;
    String msg_type, user_id, sender_id, event_id,category_name, title, description, status, action;

    public AddMessageToDB(Context ctx, String msg_type, String user_id, String sender_id, String event_id,String category_name, String action) {
        this.ctx = ctx;
        this.msg_type = msg_type;
        this.user_id = user_id;
        this.sender_id = sender_id;
        this.event_id = event_id;
        this.category_name = category_name;
        if (msg_type.equals("ResponseToCategoryRequest") && action.equals("accepted")) {
            this.title = "Administrator responded to your category suggestion request";
            this.description = "category "  + this.category_name  + " was approved and added to the categories list";
        } else if (msg_type.equals("ResponseToCategoryRequest") && action.equals("rejected")) {
            this.title = "Administrator responded to your category suggestion request";
            this.description = "category "  + this.category_name  + " was rejected";
        }
        this.action = action;
        RequestMaker();
    }


    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {

                } else {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void RequestMaker() {
        AddMessageToDbRequest addMessageToDbRequest = new AddMessageToDbRequest(user_id, sender_id, event_id, title, description, responseListener);
        RequestQueue queue = Volley.newRequestQueue(this.ctx);
        queue.add(addMessageToDbRequest);
    }
}
