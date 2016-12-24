package com.example.ofir.bopofinal.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }


   public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;

       public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvPersonName);
        }
    }
}
