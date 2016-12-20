package com.example.ofir.bopofinal.Events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ofir.bopofinal.R;
import java.util.ArrayList;
import android.content.Context;
/**
 * Created by Alonka on 14-Dec-16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    ArrayList<EventDetails> arrayList = new ArrayList<>();
    Context ctx;

     public RecyclerAdapter(ArrayList<EventDetails> arrayList)
    {
        for (EventDetails e : arrayList)
        {
            this.arrayList.add(e);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getEventName());
        holder.location.setText(arrayList.get(position).getEventLocation());
        holder.date.setText(arrayList.get(position).getEventDate());
        holder.time.setText(arrayList.get(position).getEventTime());
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }


   public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, location, date, time;

       public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            location = (TextView)itemView.findViewById(R.id.tvLocation);
            date = (TextView)itemView.findViewById(R.id.tvDate);
            time = (TextView)itemView.findViewById(R.id.tvTime);
        }
    }
}
