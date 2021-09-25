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
public class MovieRVAdapter extends RecyclerView.Adapter<MovieRVAdapter.ViewHolder>{

    //get data List
    private ArrayList<ShowTicketRVModel> showTicketRVModelArrayList;
    private Context context;
    int lastPos = -1;
    private  MovieClickInterface movieClickInterface;

    public MovieRVAdapter(ArrayList<ShowTicketRVModel> showTicketRVModelArrayList, Context context, MovieClickInterface movieClickInterface) {
        this.showTicketRVModelArrayList = showTicketRVModelArrayList;
        this.context = context;
        this.movieClickInterface = movieClickInterface;
    }

    @NonNull
    @Override
    public MovieRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_rv_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ShowTicketRVModel showTicketRVModel = showTicketRVModelArrayList.get(position);
        holder.movieNameTV.setText(showTicketRVModel.getMovieTitle());
        holder.movieDurationTV.setText("Hours - "+showTicketRVModel.getMovieDuration());
        Picasso.get().load(showTicketRVModel.getMovieImageLink()).into(holder.movieIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieClickInterface.onCourseClick(position);
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

        return showTicketRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView movieDurationTV, movieNameTV;
        private ImageView movieIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieDurationTV = itemView.findViewById(R.id.idTVMovieDuration);
            movieNameTV = itemView.findViewById(R.id.idTVMovieName);
            movieIV = itemView.findViewById(R.id.idIVMovie);
        }
    }

    //onclick the course card
    public interface MovieClickInterface{
        void onCourseClick(int position);
    }

}
