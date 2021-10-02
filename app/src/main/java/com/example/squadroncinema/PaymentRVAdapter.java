package com.example.squadroncinema;

import android.annotation.SuppressLint;
import android.content.Context;
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
    private ArrayList<MovieTicketBookingRVModal> movieTicketBookingRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private ShowClickInterface showClickInterface;

    public PaymentRVAdapter(ArrayList<MovieTicketBookingRVModal> movieTicketBookingRVModalArrayList, Context context, ShowClickInterface showClickInterface) {
        this.movieTicketBookingRVModalArrayList = movieTicketBookingRVModalArrayList;
        this.context = context;
        this.showClickInterface = showClickInterface;
    }

    @NonNull
    @Override
    public PaymentRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MovieTicketBookingRVModal movieTicketBookingRVModal = movieTicketBookingRVModalArrayList.get(position);
        holder.TicketIDTV.setText("Ticket ID : " + movieTicketBookingRVModal.getTicketId());
        holder.MovieNameTV.setText("Title : " + movieTicketBookingRVModal.getmName());
        holder.MovieBookedDateTV.setText("Booked Date : " + movieTicketBookingRVModal.getBookedDate());
        String test = movieTicketBookingRVModal.getStatus();
        switch (test) {
            case "2":
                holder.PayedStatus.setText("Payed");
                break;
            case "1":
                holder.PayedStatus.setText("Not Payed");
                break;
        }
        Picasso.get().load(movieTicketBookingRVModal.getmImage()).into(holder.movieIV);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClickInterface.onPaymentClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return movieTicketBookingRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView MovieBookedDateTV, MovieNameTV, TicketIDTV, PayedStatus;
        private ImageView movieIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TicketIDTV = itemView.findViewById(R.id.idTVTicketID);
            MovieNameTV = itemView.findViewById(R.id.idTVMovieName);
            movieIV = itemView.findViewById(R.id.idIVFilm);
            MovieBookedDateTV = itemView.findViewById(R.id.idTVMovieBookedDate);
            PayedStatus = itemView.findViewById(R.id.idPayedStatus);

        }
    }

    public interface ShowClickInterface {
        void onPaymentClick(int position);
    }
}
