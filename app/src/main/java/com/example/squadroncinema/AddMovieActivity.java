package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMovieActivity extends AppCompatActivity {

    private TextInputEditText mNameEdt, mTypeEdt, mDurationEdt ,  mImageEdt, mDescriptionEdt, mStartDateEdt, mEndDateEdt;
    private Button addMoviesBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        mNameEdt = findViewById(R.id.idEdtMName);
        mTypeEdt = findViewById(R.id.idEdtMType);
        mDurationEdt = findViewById(R.id.idEdtMDuration);
        mImageEdt = findViewById(R.id.idEdtMImage);
        mDescriptionEdt = findViewById(R.id.idEdtMDescription);
        mStartDateEdt = findViewById(R.id.idEdtMStartDate);
        mEndDateEdt = findViewById(R.id.idEdtMEndDate);
        addMoviesBtn = findViewById(R.id.idBtnAddMovies);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Movies");

        addMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = mNameEdt.getText().toString();
                String mType = mTypeEdt.getText().toString();
                String mDuration = mDurationEdt.getText().toString();
                String mImage = mImageEdt.getText().toString();
                String mDescription = mDescriptionEdt.getText().toString();
                String mStartDate = mStartDateEdt.getText().toString();
                String mEndDate = mEndDateEdt.getText().toString();
                mId = mName;
                MovieRVModal movieRVModal = new MovieRVModal(mName,mType,mDuration,mImage,mDescription,mStartDate,mEndDate,mId);


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(mId).setValue(movieRVModal);
                        Toast.makeText(AddMovieActivity.this, "Movie Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddMovieActivity.this,Test.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddMovieActivity.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}