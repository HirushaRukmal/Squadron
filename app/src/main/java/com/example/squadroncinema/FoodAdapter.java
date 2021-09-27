package com.example.squadroncinema;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter  extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private static final String TAG = "DiscoveryRecycleAdapter";
    private ArrayList<Food> serviceRecordsLists;
    private int height;
    Context context;
    String ACC_TOKEN;
    private int outSideTemp;
    private int measuredWidth;
    // data is passed into the constructor

    public FoodAdapter(Context context,
                       ArrayList<Food> serviceRecordsLists) {

        this.serviceRecordsLists = serviceRecordsLists;
        this.context = context;
        this.measuredWidth = measuredWidth;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        /*GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        params.width = (ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = (ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);*/

        return new ViewHolder(view);
    }

    // bind the data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: " + serviceRecordsLists);

        float liters = 0;

       /* holder.tv_filled_milage.setText(serviceRecordsLists.get(position).getNowMillage() + "Km");
        holder.tv_cost.setText(serviceRecordsLists.get(position).getCharge());
        holder.tv_fuel_type.setText(serviceRecordsLists.get(position).getFuelType());*/

        holder.tv_name.setText(serviceRecordsLists.get(position).getName());
        holder.tv_des.setText(serviceRecordsLists.get(position).getDescription());
        holder.tv_price.setText(serviceRecordsLists.get(position).getPrice());

    }


    // total number of rows
    @Override
    public int getItemCount() {
        return serviceRecordsLists.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name;
        TextView  tv_des;
        TextView tv_price;

        public ViewHolder(View itemView) {
            super(itemView);

             tv_name = itemView.findViewById(R.id.tv_name);
             tv_des = itemView.findViewById(R.id.tv_description);
             tv_price = itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
