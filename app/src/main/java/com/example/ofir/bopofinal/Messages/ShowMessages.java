package com.example.ofir.bopofinal.Messages;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alonka on 10-Jan-17.
 */

public class ShowMessages {
    Context ctx;
    ArrayList<MessageDetails> arrayList = new ArrayList<>();
    String json_url = "http://tower.site88.net/MyMessages.php";
    public static boolean noMessagesFlag = false;
    private static Intent m_intent;
    String m_userID = LoggedInUserService.getInstance().getM_id() + "";
    public ShowMessages(Context ctx){
        this.ctx = ctx;
        requestMaker();
    }
    public Response.Listener<JSONArray> getResponseListener ()
    {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                if (response.length() == 0) {
                    noMessagesFlag = true;
                    ctx.startActivity(new Intent(ctx, MessagesHandlerActivity.class));
                } else {
                    noMessagesFlag = false;
                    while (count < response.length()) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(count);
                            MessageDetails messageDetails = new MessageDetails(jsonObject.getString("message_id"),jsonObject.getString("user_id"), jsonObject.getString("sender_id"), jsonObject.getString("event_id"),
                                    jsonObject.getString("category_name"), jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getString("status"));
                            arrayList.add(messageDetails);
                            count++;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MessageResultService.getInstance().setArray(arrayList);
                    }
                    ctx.startActivity(new Intent(ctx, MessagesHandlerActivity.class));
                }
            }
        };
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Messages showing error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        };
    }
    public void requestMaker() {
        MyMessagesRequest myMessagesRequest = new MyMessagesRequest(m_userID, json_url, getResponseListener(), getErrorListener());
        RequestQueue queue = Volley.newRequestQueue(this.ctx);
        queue.add(myMessagesRequest);
    }

}
