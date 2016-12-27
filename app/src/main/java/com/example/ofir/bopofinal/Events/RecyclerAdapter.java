package com.example.ofir.bopofinal.Events;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ofir.bopofinal.Event.EventActivity;
import com.example.ofir.bopofinal.LoginRegister.LoginActivity;
import com.example.ofir.bopofinal.LoginRegister.RegisterActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Alonka on 14-Dec-16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    ArrayList<EventDetails> arrayList = new ArrayList<>();
    Context ctx;
    String EventID;
     public RecyclerAdapter(ArrayList<EventDetails> arrayList , Context ctx)
    {
        for (EventDetails e : arrayList)
        {
            this.arrayList.add(e);
            this.ctx = ctx;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getEventName());
        holder.location.setText(arrayList.get(position).getEventLocation());
        holder.date.setText(arrayList.get(position).getEventDate());
        holder.time.setText(arrayList.get(position).getEventTime());
        holder.event_id.setText(arrayList.get(position).getEventId());
//        EventID = holder.event_id.getText().toString();
//        holder.event_id.setTag(EventID);

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }


   public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name, location, date, time, event_id;
        Context ctx;

       public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
           this.ctx = ctx;
           itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.tvName);
            location = (TextView)itemView.findViewById(R.id.tvLocation);
            date = (TextView)itemView.findViewById(R.id.tvDate);
            time = (TextView)itemView.findViewById(R.id.tvTime);
            event_id = (TextView)itemView.findViewById(R.id.tvEventId);


        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "Thank you " + this.event_id.getText().toString(),
//                    Toast.LENGTH_LONG).show();
            String str = (String)view.getTag();
            String s;
            Intent intent = new Intent (this.ctx, EventActivity.class);
            s = this.event_id.getText().toString();
            intent.putExtra("str",s);
            this.ctx.startActivity(intent);
        }
    }
}
