package com.example.ofir.bopofinal.Events;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class ShowMyEventsActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String MODERATOR = "moderator";
    public static final String PARTICIPANT = "participant";
    public static final String WAITING_FOR_ACK = "waiting for ack";
    public static final String ROLE = "role";
    public static final String USER_ID = "user_id";
    public static boolean noEventsFlag = false;
    Button btnModerator;
    Button btnParticipant;
    Button btnWaitingForAck;
    private String m_userID;

    Context context;
    ArrayList<EventDetails> arrayList = new ArrayList<>();
    String json_url = "http://tower.site88.net/MyEvents.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_events);
        m_userID = LoggedInUserService.getInstance().getM_id() + "";

        btnModerator = (Button) findViewById(R.id.btnModerator);
        btnParticipant = (Button) findViewById(R.id.btnParticipant);
        btnWaitingForAck = (Button) findViewById(R.id.btnWaitingForAck);
        getSupportActionBar().setTitle("My events");
    }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnModerator:
                        SharedPreferencesMaker(m_userID,MODERATOR);
                        RequestMaker(MODERATOR);
                        break;
                    case R.id.btnParticipant:
                        SharedPreferencesMaker(m_userID,PARTICIPANT);
                        RequestMaker(PARTICIPANT);
                        break;
                    case R.id.btnWaitingForAck:
                        SharedPreferencesMaker(m_userID,WAITING_FOR_ACK);
                        RequestMaker(WAITING_FOR_ACK);
                        break;
                }
            }


    public Response.Listener<JSONArray> getResponseListener()
    {
        return new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    int count = 0;
                    if (response.length()== 0)
                        {
                            noEventsFlag = true;
                            startActivity(new Intent(ShowMyEventsActivity.this, DisplayEventsActivity.class));
                        }
                    else {
                            noEventsFlag = false;
                        while (count < response.length())
                        {
                            try
                            {
                                JSONObject jsonObject = response.getJSONObject(count);
                                EventDetails eventDetails = new EventDetails(jsonObject.getString("event_name"), jsonObject.getString("event_location"), jsonObject.getString("event_date"), jsonObject.getString("event_time"));
                                arrayList.add(eventDetails);
                                count++;

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                            EventResultService.getInstance().setArray(arrayList);
                        }
                        startActivity(new Intent(ShowMyEventsActivity.this, DisplayEventsActivity.class));
                    }
                }
            };
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "My events showing error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        };
    }
    public void SharedPreferencesMaker(String user_id, String role)
    {
        SharedPreferences Pref = getSharedPreferences("UserInfoForEvents",MODE_PRIVATE);
        SharedPreferences.Editor editor = Pref.edit();
        editor.putString(USER_ID, user_id);
        editor.putString(ROLE, role);
        editor.apply();
    }
    public void RequestMaker(String role)
    {
        MyEventsRequest myEventsRequest = new MyEventsRequest(role, m_userID, json_url, getResponseListener(), getErrorListener());
        RequestQueue queue = Volley.newRequestQueue(ShowMyEventsActivity.this);
        queue.add(myEventsRequest);
    }
}
