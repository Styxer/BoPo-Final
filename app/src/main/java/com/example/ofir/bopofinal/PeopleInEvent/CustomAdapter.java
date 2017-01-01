package com.example.ofir.bopofinal.PeopleInEvent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ofir.bopofinal.R;

import java.util.List;

/**
 * Created by filipp on 9/16/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

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
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(my_data.get(position).getmName());
//        holder.role.setText(my_data.get(position).getmRole());
       // holder.ratingBar.setRating(my_data.get(position).get());
        Glide.with(context).load(my_data.get(position).getmImage_link()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView name,role;
        private ImageView imageView;
        private RatingBar ratingBar;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cardName);
            imageView = (ImageView) itemView.findViewById(R.id.cardImage);
        }
    }
}