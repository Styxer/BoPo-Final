package com.example.ofir.bopofinal.Administrator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ofir.bopofinal.R;
import java.util.ArrayList;
import android.content.Intent;

/**
 * Created by Alonka on 27-Dec-16.
 */

public class ManageCategoriesRecyclerAdapter  extends RecyclerView.Adapter<ManageCategoriesRecyclerAdapter.MyViewHolder>{
    ArrayList<CategoryDetails> arrayList = new ArrayList<>();
    Context ctx;

    public ManageCategoriesRecyclerAdapter(ArrayList<CategoryDetails> arrayList, Context ctx)
    {
        for (CategoryDetails c : arrayList)
        {
            this.arrayList.add(c);
            this.ctx = ctx;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_categories_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int pos = position;
        holder.category_name.setText(arrayList.get(position).getCategoryName());
        holder.category_status.setText(arrayList.get(position).getCategoryStatus());
        holder.user_id.setText(arrayList.get(position).getUserId());
        holder.request_id.setText(arrayList.get(position).getRequestId());
        holder.checkSelectedRequests.setChecked(arrayList.get(position).isSelected());
        holder.checkSelectedRequests.setTag(arrayList.get(position));

        holder.checkSelectedRequests.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                CategoryDetails c = (CategoryDetails) cb.getTag();

                c.setSelected(cb.isChecked());
                arrayList.get(pos).setSelected(cb.isChecked());
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category_name, category_status, user_id, request_id;
        CheckBox checkSelectedRequests ;
        Context ctx;

        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            category_name = (TextView) itemView.findViewById(R.id.tvCategoryName);
            category_status = (TextView) itemView.findViewById(R.id.tvCategoryStatus);
            user_id = (TextView) itemView.findViewById(R.id.tvSuggestingUserId);
            request_id = (TextView) itemView.findViewById(R.id.tvCategoryRequestId);
            checkSelectedRequests = (CheckBox) itemView.findViewById(R.id.cbChoose);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent (this.ctx, ManageCategoriesActivity.class);;
            this.ctx.startActivity(intent);
        }
    }
}
