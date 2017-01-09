package com.example.ofir.bopofinal.Administrator;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.R;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class ManageCategoriesActivity extends AppCompatActivity {

    TextView category_name, category_status, user_id, request_id, NoRequests, choose;
    Button btnAcceptRequests;
    Button btnRejectRequests;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);
        getSupportActionBar().setTitle("Pending categories list");

        recyclerView = (RecyclerView) findViewById(R.id.CategoryRecyclerView);
        category_name = (TextView) findViewById(R.id.tvCategoryName);
        category_status = (TextView) findViewById(R.id.tvCategoryStatus);
        user_id = (TextView) findViewById(R.id.tvUserID);
        request_id = (TextView) findViewById(R.id.tvRequestID);
        NoRequests = (TextView) findViewById(R.id.tvNoCategories);
        btnAcceptRequests = (Button) findViewById(R.id.btnAcceptRequests);
        btnRejectRequests = (Button) findViewById(R.id.btnRejectRequests);
        choose = (TextView)findViewById(R.id.tvChoose);

        if (AdministratorMainScreenActivity.noPendingRequests == true) {
            recyclerView.setVisibility(View.INVISIBLE);
            category_name.setVisibility(View.INVISIBLE);
            category_status.setVisibility(View.INVISIBLE);
            btnAcceptRequests.setVisibility(View.INVISIBLE);
            btnRejectRequests.setVisibility(View.INVISIBLE);
            choose.setVisibility(View.INVISIBLE);
            NoRequests.setVisibility(View.VISIBLE);
        } else if (AdministratorMainScreenActivity.noPendingRequests == false) {
            recyclerView.setVisibility(View.VISIBLE);
            category_name.setVisibility(View.VISIBLE);
            category_status.setVisibility(View.VISIBLE);
            btnAcceptRequests.setVisibility(View.VISIBLE);
            btnRejectRequests.setVisibility(View.VISIBLE);
            choose.setVisibility(View.VISIBLE);
            NoRequests.setVisibility(View.INVISIBLE);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            adapter = new ManageCategoriesRecyclerAdapter(ManageCategoriesResultService.getInstance().getArray(), this);
            ManageCategoriesResultService.getInstance().reset();
            recyclerView.setAdapter(adapter);
        }

        btnAcceptRequests.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<CategoryDetails> catList = ((ManageCategoriesRecyclerAdapter) adapter)
                        .arrayList;


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String category_name = jsonResponse.getString("category_name");
                            if (success) {
                                Toast.makeText(ManageCategoriesActivity.this, "Category " +category_name+ " was approved",
                                        Toast.LENGTH_SHORT).show();
                               ManageCategoriesActivity.this.startActivity(new Intent(ManageCategoriesActivity.this, AdministratorMainScreenActivity.class));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManageCategoriesActivity.this);
                                builder.setMessage("Category approval failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                               // Toast.makeText(ManageCategoriesActivity.this, "deleted from pending list",
                                     //   Toast.LENGTH_SHORT).show();
                             //   ManageCategoriesActivity.this.startActivity(new Intent(ManageCategoriesActivity.this, AdministratorMainScreenActivity.class));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManageCategoriesActivity.this);
                                builder.setMessage("delete from pending list failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                for (int i = 0; i < catList.size(); i++) {
                    CategoryDetails singleCategory = catList.get(i);
                    if (singleCategory.isSelected() == true) {
                        ApproveCategoriesRequest approveCategoriesRequest = new ApproveCategoriesRequest(catList.get(i).getCategoryName(),/*catList.get(i).getRequestId(),*/ responseListener);
                        RequestQueue queue = Volley.newRequestQueue(ManageCategoriesActivity.this);
                        queue.add(approveCategoriesRequest);
                        DeleteCategoriesRequest deleteCategoriesRequest = new DeleteCategoriesRequest(catList.get(i).getRequestId()/*,catList.get(i).getRequestId()*/, responseListener2);
                        RequestQueue queue1 = Volley.newRequestQueue(ManageCategoriesActivity.this);
                        queue1.add(deleteCategoriesRequest);
                    }
                }
            }

        });


        btnRejectRequests.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<CategoryDetails> catList = ((ManageCategoriesRecyclerAdapter) adapter)
                        .arrayList;

                Response.Listener<String> responseListener3 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(ManageCategoriesActivity.this, "Category was rejected",
                                        Toast.LENGTH_SHORT).show();
                                ManageCategoriesActivity.this.startActivity(new Intent(ManageCategoriesActivity.this, AdministratorMainScreenActivity.class));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManageCategoriesActivity.this);
                                builder.setMessage("delete from pending list failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                for (int i = 0; i < catList.size(); i++) {
                    CategoryDetails singleCategory = catList.get(i);
                    if (singleCategory.isSelected() == true) {
                        DeleteCategoriesRequest deleteCategoriesRequest = new DeleteCategoriesRequest(catList.get(i).getRequestId()/*,catList.get(i).getRequestId()*/, responseListener3);
                        RequestQueue queue3 = Volley.newRequestQueue(ManageCategoriesActivity.this);
                        queue3.add(deleteCategoriesRequest);
                    }
                }
            }

        });


    }
}
