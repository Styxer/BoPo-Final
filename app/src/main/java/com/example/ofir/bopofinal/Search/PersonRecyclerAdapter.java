package com.example.ofir.bopofinal.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ofir.bopofinal.Profile.DownloadImageFromDB;
import com.example.ofir.bopofinal.R;

import java.util.ArrayList;

/**
 * Created by Alonka on 14-Dec-16.
 */

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.MyViewHolder>{

    ArrayList<SearchPersonDetails> arrayList = new ArrayList<>();
    Context ctx;

     public PersonRecyclerAdapter(ArrayList<SearchPersonDetails> arrayList)
    {
        for (SearchPersonDetails e : arrayList)
        {
            this.arrayList.add(e);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_people_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String current_user_photo;
        String current_user_id;
        holder.name.setText(arrayList.get(position).getName());
        holder.user_id.setText(arrayList.get(position).getUserId());
        current_user_photo = arrayList.get(position).getImage();
        current_user_id = arrayList.get(position).getUserId();
        if(current_user_photo.equals("profilePictureOfUserId" +  current_user_id + ".JPG"))
        new DownloadImageFromDB(holder.photo).execute("http://tower.site88.net/pictures/" + current_user_photo);
        else if(current_user_photo.equals("DefaultPicture.JPG"))
            new DownloadImageFromDB(holder.photo).execute("http://tower.site88.net/pictures/DefaultPicture.JPG");
        else if (current_user_photo.equals("AdministratorPicture.JPG"))
            new DownloadImageFromDB(holder.photo).execute("http://tower.site88.net/pictures/AdministratorPicture.JPG");
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }


   public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, user_id;
        ImageView photo;

       public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvPersonName);
            user_id  = (TextView) itemView.findViewById(R.id.tvSearchUserId);
            photo  = (ImageView) itemView.findViewById(R.id.ivSearchPeopleImage);
        }
    }
}
