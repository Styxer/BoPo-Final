package com.example.ofir.bopofinal.Rides;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ofir.bopofinal.R;
import com.example.ofir.bopofinal.myRides.rideData;

import java.util.List;

/**
 * Created by ofir on 1/21/2017.
 */
public class customAdapter extends RecyclerView.Adapter<customAdapter.ViewHolder> {

    private Context context;
    private List<rideData> my_data;
    private rideData mData;

    public customAdapter(Context context, List<rideData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }




    @Override
    public customAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    /*    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ride_card,parent,false);

        return new ViewHolder(itemView);*/
        return null;
    }

    @Override
    public void onBindViewHolder(customAdapter.ViewHolder holder, int position) {
        holder.eventName.setText(my_data.get(position).getRideData().getEvent_name());
        holder.eventLocation.setText(my_data.get(position).getEvent_location().get(position));
      //  holder.driverName.setText(my_data.get(position).);
    //    holder.seatsAvilable.setText(my_data.get(position).gets());

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventName, eventLocation, driverName, seatsAvilable;
        private Button userDetails;



        public ViewHolder(View itemView) {
            super(itemView);

          /*  eventName = (TextView) itemView.findViewById(R.id.tvRideEventName);
            eventLocation = (TextView) itemView.findViewById(R.id.tvRideEventLocation);
            driverName = (TextView) itemView.findViewById(R.id.tvRideDriverName);
            seatsAvilable = (TextView) itemView.findViewById(R.id.tvRideSeatsAvilable);*/

        }
    }
}