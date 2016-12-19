package com.example.ofir.bopofinal.Categories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SuggestCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private static EditText etCategoryName;
    private static Button btnSend;
    private static Button btnBack;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_category);

        etCategoryName = (EditText) findViewById(R.id.etCategoryName);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnBack = (Button) findViewById(R.id.btnBack);

        intent = new Intent(SuggestCategoryActivity.this, MainAppScreenActivity.class);
        getSupportActionBar().setTitle("Suggest new category");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                final String category_name = etCategoryName.getText().toString();
                final int user_id = LoggedInUserService.getInstance().getM_id();
              //  System.out.print()

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(SuggestCategoryActivity.this, "Category suggestion was sent,"+"\n"+"   waiting for admin approval",
                                        Toast.LENGTH_LONG).show();
                                SuggestCategoryActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestCategoryActivity.this);
                                builder.setMessage("Category suggestion failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SuggestCategoryRequest suggestCategoryRequest = new SuggestCategoryRequest(category_name, user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SuggestCategoryActivity.this);
                queue.add(suggestCategoryRequest);
                break;

            case R.id.btnBack:
                intent = new Intent(SuggestCategoryActivity.this, MainAppScreenActivity.class);
                SuggestCategoryActivity.this.startActivity(intent);
                break;
        }
    }
}
