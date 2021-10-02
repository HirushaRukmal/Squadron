package com.example.squadroncinema;

import static android.os.Build.ID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
//import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditMovie extends AppCompatActivity implements MovieRVAdapter.MovieClickInterface{

//    private TextInputEditText mNameEdt, mTypeEdt, mDurationEdt, mImageEdt, mDescriptionEdt, mStartDateEdt, mEndDateEdt;
//    private Button updateMovieBtn, deleteMovieBtn;
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//    private String mId;
//    private MovieRVModal movieRVModal;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_movie);
//        mNameEdt = findViewById(R.id.idEdtMName);
//        mTypeEdt = findViewById(R.id.idEdtMType);
//        mDurationEdt = findViewById(R.id.idEdtMDuration);
//        mImageEdt = findViewById(R.id.idEdtMImage);
//        mDescriptionEdt = findViewById(R.id.idEdtMDescription);
//        mStartDateEdt = findViewById(R.id.idEdtMStartDate);
//        mEndDateEdt = findViewById(R.id.idEdtMEndDate);
//        updateMovieBtn = findViewById(R.id.idBtnUpdateMovie);
//        deleteMovieBtn = findViewById(R.id.idBtnDeleteMovie);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//
//        movieRVModal = getIntent().getParcelableExtra("Movies");
//
//        if (movieRVModal != null) {
//            mNameEdt.setText(movieRVModal.getmName());
//            mTypeEdt.setText(movieRVModal.getmType());
//            mDurationEdt.setText(movieRVModal.getmDuration());
//            mImageEdt.setText(movieRVModal.getmImage());
//            mDescriptionEdt.setText(movieRVModal.getmDescription());
//            mStartDateEdt.setText(movieRVModal.getmStartDate());
//            mEndDateEdt.setText(movieRVModal.getmEndDate());
//            mId = movieRVModal.getmId();
//        }
//
//        databaseReference = firebaseDatabase.getReference("Movies").child(mId);
//        updateMovieBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String mName = mNameEdt.getText().toString();
//                String mType = mTypeEdt.getText().toString();
//                String mDuration = mDurationEdt.getText().toString();
//                String mImage = mImageEdt.getText().toString();
//                String mDescription = mDescriptionEdt.getText().toString();
//                String mStartDate = mStartDateEdt.getText().toString();
//                String mEndDate = mEndDateEdt.getText().toString();
//
//                Map<String, Object> map = new HashMap<>();
//                map.put("mName", mName);
//                map.put("mType", mType);
//                map.put("mDuration", mDuration);
//                map.put("mImage", mImage);
//                map.put("mDescription", mDescription);
//                map.put("mStartDate", mStartDate);
//                map.put("mEndDate", mEndDate);
//                map.put("mId", mId);
//
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        databaseReference.updateChildren(map);
//                        startActivity(new Intent(EditMovie.this, Test.class));
//                        Toast.makeText(EditMovie.this, "Movie Updated", Toast.LENGTH_SHORT).show();
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(EditMovie.this, "Failed to Update.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });
//
//        deleteMovieBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){deleteMovie();}
//        });
//
//    }
//
//    private void deleteMovie(){
//        databaseReference.removeValue();
//        Toast.makeText(EditMovie.this,"Movie Deleted.",Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(EditMovie.this,Test.class));
//
//
//    }
//}


    private RecyclerView moviesRV;
    private ProgressBar loadingPB;
//    private FloatingActionButton addFAB;
    TextView txt1;
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

    public String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);




        txt1 = findViewById(R.id.txt1);
        moviesRV = findViewById(R.id.idRVMovie);
        bottomSheetRL = findViewById(R.id.idRLBSheet2);
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
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_update, bottomSheetRL);
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
        TextView Id = layout.findViewById(R.id.txt1);

        //for the two buttons
        Button cancelBtn = layout.findViewById(R.id.idBtnUpdate);
        Button deleteMovieBtn = layout.findViewById(R.id.idBtnDelete);


        //get data to the card
        movieNameTV.setText(movieRVModal.getmName());
        movieTypeTV.setText(movieRVModal.getmType());
        movieDurationTV.setText(movieRVModal.getmDuration());
        movieDescriptionTV.setText(movieRVModal.getmDescription());
        Picasso.get().load(movieRVModal.getmImage()).into(movieIV);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(EditMovie.this,UpdateMovies.class);
                startActivity(intent2);
            }
        });

        deleteMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Movies").child("id").removeValue();
                Toast.makeText(EditMovie.this,  "Delete success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditMovie.this,EditMovie.class);
                startActivity(intent);
            }
        });

    }
}

