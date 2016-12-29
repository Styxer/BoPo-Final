package com.example.ofir.bopofinal.Administrator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdministratorMainScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private static ImageButton m_ibManageCategories;
    private static ImageButton m_ibLogout;
    private static Intent m_intent;
    public static boolean noPendingRequests = false;

    Context context;
    ArrayList<CategoryDetails> arrayList = new ArrayList<>();
    String json_url = "http://tower.site88.net/ShowPendingCategoryRequests.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_main_screen);

        m_ibManageCategories = (ImageButton) findViewById(R.id.ibManageCategories);
        m_ibLogout = (ImageButton) findViewById(R.id.ibLogout);

        getSupportActionBar().setTitle("Welcome administrator!");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ibManageCategories:
                RequestMaker();
                break;
            case R.id.ibLogout:
                Toast.makeText(AdministratorMainScreenActivity.this, "bye bye" +" "+ LoggedInUserService.getInstance().getM_name(),
                        Toast.LENGTH_SHORT).show();
                LogOutUser();
                m_intent = new Intent(AdministratorMainScreenActivity.this, LoginActivity.class);
                AdministratorMainScreenActivity.this.startActivity(m_intent);
                break;

        }
    }

    public void LogOutUser()
    {
        LoggedInUserService.getInstance().reset();
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
                    noPendingRequests = true;
                    startActivity(new Intent(AdministratorMainScreenActivity.this, ManageCategoriesActivity.class));
                }
                else {
                    noPendingRequests = false;
                    while (count < response.length())
                    {
                        try
                        {
                            JSONObject jsonObject = response.getJSONObject(count);
                            CategoryDetails categoryDetails = new CategoryDetails(jsonObject.getString("category_name"), jsonObject.getString("category_status"), jsonObject.getString("user_id"), jsonObject.getString("request_id"),false);
                            arrayList.add(categoryDetails);
                            count++;

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        ManageCategoriesResultService.getInstance().setArray(arrayList);
                    }
                    startActivity(new Intent(AdministratorMainScreenActivity.this, ManageCategoriesActivity.class));
                }
            }
        };
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Pending categories showing error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        };
    }

    public void RequestMaker()
    {
        ManageCategoriesRequest manageCategoriesRequest = new ManageCategoriesRequest(json_url, getResponseListener(), getErrorListener());
        RequestQueue queue = Volley.newRequestQueue(AdministratorMainScreenActivity.this);
        queue.add(manageCategoriesRequest);
    }
}
