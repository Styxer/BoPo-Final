package com.example.ofir.bopofinal.PeopleInEvent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.example.ofir.bopofinal.LoginRegister.LoggedInUserService;

import com.example.ofir.bopofinal.Profile.DownloadImageFromDB;
import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.User.UserActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by filipp on 9/16/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    private Context context;
    private List<MyData> my_data;
    private MyData mData;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRRA_ROLE = "role";
    public static final String EXTRA_BIRTHDAY = "birthday";
    public static final String EXTRRA_PHONE_NUMBER = "phone_number";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRRA_IMAGE = "image";
    public static final String EXTRRA_ID = "id";

    public static final int JOIN_EVENT = 1;
    public static final int REJECT_EVENT = 2;

    private int flag = -1;


    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        int myCurrentPosition =  holder.getAdapterPosition();
        LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();

        holder.name.setText(my_data.get(position).getmName());
        holder.role.setText(my_data.get(position).getmRole());
        holder.birthDay.setText(my_data.get(position).getmBirthday());
        holder.address.setText(my_data.get(position).getmAddress());

        holder.toggleACK.setVisibility(View.GONE);
        holder.toggleACK.setText("no decision");
        holder.toggleACK.setTextColor(Color.GRAY);

        if(my_data.get(position).getmImage_link().equals("profilePictureOfUserId" + my_data.get(position).getmId() + ".JPG"))
            new DownloadImageFromDB(holder.imageView).execute("http://tower.site88.net/pictures/profilePictureOfUserId" + my_data.get(position).getmId() + ".JPG");
        else if(my_data.get(position).getmImage_link().equals("DefaultPicture.JPG"))
            new DownloadImageFromDB(holder.imageView).execute("http://tower.site88.net/pictures/DefaultPicture.JPG");

       if (myCurrentPosition == position && my_data.get(position).getmRole().equals("waiting for ack") &&
               my_data.get(0).getmId() == loggedInUserService.getM_id()){
           holder.toggleACK.setVisibility(View.VISIBLE);

           holder.toggleACK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                   if(isChecked) {
                       holder.toggleACK.setTextOn("accept " +my_data.get(position).getmName());
                       holder.toggleACK.setTextColor(Color.BLUE);
                      flag = 1;
                   }else {
                       holder.toggleACK.setTextOff("reject " +my_data.get(position).getmName());
                       holder.toggleACK.setTextColor(Color.RED);
                         flag = 2;

                   }
               }
           });


       }

        holder.userDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UserActivity.class);
                mData =   my_data.get(position);
                Log.i("myID", String.valueOf(mData.getmId()));
                i.putExtra("id",String.valueOf(mData.getmId()));
                i.putExtra(EXTRA_NAME,mData.getmName());
                i.putExtra(EXTRRA_ROLE,mData.getmRole());
                i.putExtra(EXTRA_BIRTHDAY,mData.getmBirthday());
                i.putExtra(EXTRRA_PHONE_NUMBER,mData.getmPhone_number());
                i.putExtra(EXTRA_ADDRESS,mData.getmAddress());
                i.putExtra(EXTRRA_IMAGE,mData.getmImage_link());

                if(flag == 1 )//join
                    sendUserRequest(my_data.get(position).getmId(),my_data.get(position).getmEventID(),JOIN_EVENT, position);
                else if (flag == 2)//reject
                    sendUserRequest(my_data.get(position).getmId(),my_data.get(position).getmEventID(),REJECT_EVENT, position);

                context.startActivity(i);
            }
        });

    }




    private void sendUserRequest(int user_id, int event_id, int choice, final int position) {


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("sendUserRequest",response.toString());

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                     /*   Toast.makeText(context, "This is my Toast message!",
                                Toast.LENGTH_LONG).show();*/

                    }
                    else{
                     /*   Toast.makeText(context, "This is5262 my Toast message!",
                                Toast.LENGTH_LONG).show();*/
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        UserInEventRequest userInEventRequest = new UserInEventRequest(user_id, event_id, choice, responseListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(userInEventRequest);
    }


    @Override
    public int getItemCount() {

        return my_data.size();
    }




    public  class ViewHolder extends  RecyclerView.ViewHolder {

        private TextView name, role, birthDay, address;
        private ImageView imageView;
        private ToggleButton toggleACK;
        private Button userDetails;
        private String m_image, m_userID;








        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvUserName);
            role = (TextView) itemView.findViewById(R.id.tvUserRole);
            birthDay = (TextView) itemView.findViewById(R.id.tvUserBday);
            address = (TextView) itemView.findViewById(R.id.tvUserAddress);
            imageView = (ImageView) itemView.findViewById(R.id.cardImage);
            toggleACK = (ToggleButton) itemView.findViewById(R.id.toggleButton);
            userDetails  =(Button) itemView.findViewById(R.id.bUserDetails);
           // m_image = LoggedInUserService.getInstance().getM_image();



        }



    }


}