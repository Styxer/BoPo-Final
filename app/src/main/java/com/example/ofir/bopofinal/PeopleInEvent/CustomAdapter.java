package com.example.ofir.bopofinal.PeopleInEvent;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ofir.bopofinal.PeopleInEvent.MyData;
import com.example.ofir.bopofinal.R;

import java.util.List;
import java.util.Observable;

/**
 * Created by ofir on 9/16/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    private Context mContext;
    private List<MyData> m_my_data;
    private CardView mCardView;






    public CustomAdapter(Context context, List<MyData> my_data, CardView mCardView) {
        this.mContext = context;
        this.m_my_data = my_data;
        this.mCardView = mCardView;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.description.setText(m_my_data.get(position).getmDescription());
        Glide.with(mContext).load(m_my_data.get(position).getmImage_link()).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return m_my_data.size();
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView description;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}