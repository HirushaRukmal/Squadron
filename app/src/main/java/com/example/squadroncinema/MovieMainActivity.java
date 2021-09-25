package com.example.squadroncinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieMainActivity extends AppCompatActivity implements MovieRVAdapter.MovieClickInterface{

    private RecyclerView movieRV;
    private ProgressBar loadingPB;

    //firebase db
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //retrive the list
    private ArrayList<ShowTicketRVModel> showTicketRVModelArrayList;

    //card for bottom
    private RelativeLayout bottomSheetRL;

    //card for bottom (details about ticket booking)
    private MovieRVAdapter movieRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);

        movieRV = findViewById(R.id.idRVMovie);
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        showTicketRVModelArrayList = new ArrayList<>();

        //intialize firebase db
        databaseReference = firebaseDatabase.getReference("movieTicketDetails");


        movieRVAdapter = new MovieRVAdapter(showTicketRVModelArrayList, this, this);

        //layout manager
        movieRV.setLayoutManager(new LinearLayoutManager(this));

        movieRV.setAdapter(movieRVAdapter);

        getAllMovieDetails();
    }

    private void getAllMovieDetails() {
        showTicketRVModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                showTicketRVModelArrayList.add(snapshot.getValue(ShowTicketRVModel.class));
                movieRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                movieRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                movieRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                movieRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCourseClick(int position) {
        //call the bottom sheet function
        displayBottomSheet(showTicketRVModelArrayList.get(position));
    }

    private void displayBottomSheet(ShowTicketRVModel showTicketRVModel){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.activity_bottom_sheet_dialog, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        //intialize the fieldes in the card
        TextView movieNameTV = layout.findViewById(R.id.idTVMovieName);
        TextView movieDescTV = layout.findViewById(R.id.idTVMovieDesc);
        TextView movieDuration = layout.findViewById(R.id.idTVMovieDuration);
        TextView movieType = layout.findViewById(R.id.idTVMovieType);
        ImageView movieIV = layout.findViewById(R.id.idIVMovie);



        //for the two buttons
//        Button cancelBtn = layout.findViewById(R.id.idBtnCancel);
//        Button nextBtn = layout.findViewById(R.id.idBtnNext);

        //get data to the card
        movieNameTV.setText(showTicketRVModel.getMovieTitle());
        movieDescTV.setText(showTicketRVModel.getMovieDescription());
        movieDuration.setText(showTicketRVModel.getMovieDuration());
        movieType.setText(showTicketRVModel.getTicketType());

        Picasso.get().load(showTicketRVModel.getMovieImageLink()).into(movieIV);
    }
}