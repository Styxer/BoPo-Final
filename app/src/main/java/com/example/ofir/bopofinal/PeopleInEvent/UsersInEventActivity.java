package com.example.ofir.bopofinal.PeopleInEvent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.User.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class UsersInEventActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private CustomAdapter mAdapter;
    private List<MyData> m_data_list;
    private CardView mCardView;
    private MyData mData;
    public static final String EXTRA_TITLE = "title";
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_in_event);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mCardView = (CardView) findViewById(R.id.card_view);
        m_data_list = new ArrayList<>();


        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new CustomAdapter(this,m_data_list);
        mRecyclerView.setAdapter(mAdapter);

        mProgressDialog = new ProgressDialog(this);


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickLitsner(UsersInEventActivity.this, mRecyclerView ,new RecyclerItemClickLitsner.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(view.getContext(), UserActivity.class);
                       MyData data =   m_data_list.get(position);
                        String desc = data.getmName();
                        i.putExtra(EXTRA_TITLE,desc);
                        startActivity(i);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(UsersInEventActivity.this, "this is my Toast message2!!! =)",
                                Toast.LENGTH_LONG).show();
                    }
                })
        );





       mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(mGridLayoutManager.findLastCompletelyVisibleItemPosition() == m_data_list.size()-1){
                    load_data_from_server(m_data_list.get(m_data_list.size()-1).getmId());
                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        load_data_from_server(0);
    }

    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://tower.site88.net/UsersInEvent.php?id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        MyData data = new MyData(object.getInt("user_id"),object.getString("name"),
                               object.getString("role"), object.getString("image"));

                        m_data_list.add(data);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }

}
