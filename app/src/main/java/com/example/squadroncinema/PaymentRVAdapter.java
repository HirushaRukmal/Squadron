package com.example.squadroncinema;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaymentRVAdapter extends RecyclerView.Adapter<PaymentRVAdapter.ViewHolder> {
    //get data List
    private ArrayList<ShowTicketRVModel> showTicketRVModelArrayList;
    private Context context;
    int lastPos = -1;
    private  ShowClickInterface showClickInterface;

    public PaymentRVAdapter(ArrayList<ShowTicketRVModel> showTicketRVModelArrayList, Context context, ShowClickInterface showClickInterface) {
        this.showTicketRVModelArrayList = showTicketRVModelArrayList;
        this.context = context;
        this.showClickInterface = showClickInterface;
    }

    @NonNull
    @Override
    public PaymentRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_rv_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ShowTicketRVModel showTicketRVModel = showTicketRVModelArrayList.get(position);
        holder.filmNameTV.setText(showTicketRVModel.getMovieTitle());
        holder.ticketCountTV.setText("Ticket Count - "+showTicketRVModel.getTicketCount());
        holder.filmDate.setText("Booked Date - "+showTicketRVModel.getBookedDate());
        Picasso.get().load(showTicketRVModel.getMovieImageLink()).into(holder.filmIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        Log.i("testj",showTicketRVModelArrayList.toString());
        return showTicketRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ticketCountTV, filmNameTV, filmDate;
        private ImageView filmIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketCountTV = itemView.findViewById(R.id.idTVTicketCount);
            filmNameTV = itemView.findViewById(R.id.idTVFilmName);
            filmIV = itemView.findViewById(R.id.idIVFilm);
            filmDate = itemView.findViewById(R.id.idTVFilmDate);
        }
    }

    //onclick the course card
    public interface ShowClickInterface{
        void onCourseClick(int position);
    }
}

