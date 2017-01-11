package com.example.ofir.bopofinal.Messages;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.R;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alonka on 09-Jan-17.
 */

public class MessagesRecyclerAdapter extends RecyclerView.Adapter<MessagesRecyclerAdapter.MyViewHolder>{

    ArrayList<MessageDetails> arrayList = new ArrayList<>();
    Context ctx;
    public static boolean MessageReadFlag;
    public static boolean MessageUnreadFlag;
    public MessagesRecyclerAdapter(ArrayList<MessageDetails> arrayList , Context ctx)
    {
        for (MessageDetails m : arrayList)
        {
            this.arrayList.add(m);
            this.ctx = ctx;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (arrayList.get(position).getStatus().equals("unread"))
        {
            MessageReadFlag = false;
            MessageUnreadFlag = true;
        }
        else if (arrayList.get(position).getStatus().equals("read"))
        {
            MessageReadFlag = true;
            MessageUnreadFlag = false;
        }
        holder.message_id.setText(arrayList.get(position).getMessageID());
        holder.user_id.setText(arrayList.get(position).getUserID());
        holder.sender_id.setText(arrayList.get(position).getSenderID());
        holder.event_id.setText(arrayList.get(position).getEventId());
        holder.category_name.setText(arrayList.get(position).getCategoryName());
        if (MessageReadFlag == false && MessageUnreadFlag == true)
            holder.title.setTypeface(null, Typeface.BOLD);
        else if (MessageReadFlag == true && MessageUnreadFlag == false)
            holder.title.setTypeface(null, Typeface.NORMAL);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.status.setText(arrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView message_id, user_id, sender_id, event_id,category_name, title, description, status;
        Context ctx;
        long num;

        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            this.ctx = ctx;
            itemView.setOnClickListener(this);

            message_id = (TextView) itemView.findViewById(R.id.tvMessageId);
            user_id = (TextView) itemView.findViewById(R.id.tvMessageUserId);
            sender_id = (TextView)itemView.findViewById(R.id.tvMessageSenderId);
            event_id = (TextView)itemView.findViewById(R.id.tvMessageEventId);
            category_name = (TextView)itemView.findViewById(R.id.tvMessageCategoryName);
            title = (TextView)itemView.findViewById(R.id.tvMessageTitle);
            description = (TextView)itemView.findViewById(R.id.tvMessageDeascription);
            status = (TextView)itemView.findViewById(R.id.tvMessageStatus);
            MessageReadFlag = true;
            MessageUnreadFlag = false;

        }

        @Override
        public void onClick(View view) {

            String titl, desc;
            int currentMsgNum;

            currentMsgNum = CountUnreadMessagesService.getInstance().getM_messages_number();
            if (this.status.getText().toString().equals("unread"))
            {
                CountUnreadMessagesService.getInstance().setM_messages_number(--currentMsgNum);
                ChangeMessageStatus(this.message_id.getText().toString(),"read");
            }
            Intent intent = new Intent (this.ctx, SingleMessageActivity.class);
            desc = this.description.getText().toString();
            titl = this.title.getText().toString();
            intent.putExtra("title",titl);
            intent.putExtra("description",desc);
            this.ctx.startActivity(intent);
        }

        public void ChangeMessageStatus(String msgId, String status)
        {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {

                        } else {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            ChangeMessageStatusRequest changeMessageStatusRequest = new ChangeMessageStatusRequest(msgId, status,responseListener);
            RequestQueue queue = Volley.newRequestQueue(this.ctx);
            queue.add(changeMessageStatusRequest);

        }
    }

}
