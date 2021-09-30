package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Test extends AppCompatActivity implements MovieRVAdapter.MovieClickInterface {

    private RecyclerView moviesRV;
    private ProgressBar loadingPB;
//    private FloatingActionButton addFAB;

    //firebase database
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //retrive the list
    private ArrayList<MovieRVModal> movieRVModalArrayList;

    //card for bottom
    private RelativeLayout bottomSheetRL;

    //cardidBtnEdit
    private MovieRVAdapter movieRVAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        moviesRV = findViewById(R.id.idRVMovie);
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        movieRVModalArrayList = new ArrayList<>();

        //inialize firebase db
        databaseReference = firebaseDatabase.getReference("Movies");

        mAuth = FirebaseAuth.getInstance();

        //add course
//        addFAB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
//            }
//        });

        movieRVAdapter = new MovieRVAdapter(movieRVModalArrayList, this, this);

        //layout manager
        moviesRV.setLayoutManager(new LinearLayoutManager(this));

        moviesRV.setAdapter(movieRVAdapter);
        //get all the courses method call
        getAllMovies();
    }

    private void getAllMovies() {
        movieRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                movieRVModalArrayList.add(snapshot.getValue(MovieRVModal.class));
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
    public void onMovieClick(int position) {
        //call the bottom sheet function
        displayBottomSheet(movieRVModalArrayList.get(position));
    }

    //displaying bottom sheet function
    private void displayBottomSheet(MovieRVModal movieRVModal) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.activity_bottom_sheet_movieinfo, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        //intialize the fieldes in the card
        TextView movieNameTV = layout.findViewById(R.id.idTVMovieName);
        TextView movieTypeTV = layout.findViewById(R.id.idTVMovieType);
        TextView movieDurationTV = layout.findViewById(R.id.idTVMovieDuration);
        TextView movieDescriptionTV = layout.findViewById(R.id.idTVMovieDescription);
        ImageView movieIV = layout.findViewById(R.id.idIVMovie);

        //for the two buttons
        Button cancelBtn = layout.findViewById(R.id.idBtnCancel);
        Button bookBtn = layout.findViewById(R.id.idBtnBook);

        //get data to the card
        movieNameTV.setText(movieRVModal.getmName());
        movieTypeTV.setText(movieRVModal.getmType());
        movieDurationTV.setText(movieRVModal.getmDuration());
        movieDescriptionTV.setText(movieRVModal.getmDescription());
        Picasso.get().load(movieRVModal.getmImage()).into(movieIV);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, EditCourseActivity.class);
//                i.putExtra("course", courseRVModel);
//                startActivity(i);
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(courseRVModel.getCourseLink()));
//                startActivity(i);
            }
        });

    }
}