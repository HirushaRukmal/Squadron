package com.example.squadroncinema;

import android.content.Context;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewFoodAdapter extends RecyclerView.Adapter<NewFoodAdapter.MyViewHolder> {


    Context context;
    ArrayList<newfood> list;

    public NewFoodAdapter(Context context, ArrayList<newfood> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        newfood newfood = list.get(position);
        holder.name.setText(newfood.getname());
        holder.Description.setText(newfood.getdescription());
        holder.price.setText(newfood.getPrice());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, Description, price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            Description = itemView.findViewById(R.id.tv_description);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}
