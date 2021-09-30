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

public class MovieRVAdapter extends RecyclerView.Adapter<MovieRVAdapter.ViewHolder> {

    //get data list
    private ArrayList<MovieRVModal> movieRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private MovieClickInterface movieClickInterface;


    public MovieRVAdapter(ArrayList<MovieRVModal> movieRVModalArrayList, Context context, MovieClickInterface movieClickInterface) {
        this.movieRVModalArrayList = movieRVModalArrayList;
        this.context = context;
        this.movieClickInterface = movieClickInterface;
    }


    @NonNull
    //return the cards
    @Override
    public MovieRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_rv_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MovieRVModal movieRVModal = movieRVModalArrayList.get(position);
        holder.movieNameTV.setText(movieRVModal.getmName());
        holder.movieTypeTV.setText(movieRVModal.getmType());
        Picasso.get().load(movieRVModal.getmImage()).into(holder.movieIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieClickInterface.onMovieClick(position);
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
        return movieRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieNameTV, movieTypeTV;
        private ImageView movieIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTV = itemView.findViewById(R.id.idTVMovieName);
            movieTypeTV= itemView.findViewById(R.id.idTVMovieType);
            movieIV = itemView.findViewById(R.id.idIVMovie);
        }
    }

    //onclick the course card
    public interface MovieClickInterface{
        void onMovieClick(int position);
    }


}
