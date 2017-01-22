package com.example.ofir.bopofinal.Search;

import android.content.Context;
import android.content.Intent;
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

     public PersonRecyclerAdapter(ArrayList<SearchPersonDetails> arrayList, Context ctx)
    {
        for (SearchPersonDetails e : arrayList)
        {
            this.arrayList.add(e);
            this.ctx = ctx;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_people_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, ctx);
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
        holder.email.setText(arrayList.get(position).getEmail());
        holder.birthday.setText(arrayList.get(position).getBirthday());
        holder.phone_number.setText(arrayList.get(position).getPhoneNumber());
        holder.address.setText(arrayList.get(position).getAddress());
        holder.photo_name.setText(arrayList.get(position).getImage());
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


   public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name, user_id, email, birthday, phone_number, address, photo_name;
        ImageView photo;
        Context ctx;

       public MyViewHolder(View itemView, Context ctx) {
           super(itemView);
           this.ctx = ctx;
           itemView.setOnClickListener(this);
           name = (TextView) itemView.findViewById(R.id.tvPersonName);
           user_id = (TextView) itemView.findViewById(R.id.tvSearchUserId);
           photo = (ImageView) itemView.findViewById(R.id.ivSearchPeopleImage);
           email = (TextView) itemView.findViewById(R.id.tvSearchedPersonEmail);
           birthday = (TextView) itemView.findViewById(R.id.tvSearchedPersonBirthday);
           phone_number = (TextView) itemView.findViewById(R.id.tvSearchedPersonPhone);
           address = (TextView) itemView.findViewById(R.id.tvSearchedPersonAddress);
           photo_name = (TextView) itemView.findViewById(R.id.tvSearchedPersonImageName);
       }

        @Override
        public void onClick(View view) {
//            String str = (String)view.getTag();
//            String s;
            Intent intent = new Intent (this.ctx, ShowUserProfileActivity.class);
            intent.putExtra("photo_name",this.photo_name.getText().toString());
            intent.putExtra("name",this.name.getText().toString());
            intent.putExtra("email",this.email.getText().toString());
            intent.putExtra("phone",this.phone_number.getText().toString());
            intent.putExtra("birthday",this.birthday.getText().toString());
            intent.putExtra("address",this.address.getText().toString());
            intent.putExtra("user_id",this.user_id.getText().toString());
            this.ctx.startActivity(intent);
        }
    }
}
