package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddMovieActivity extends AppCompatActivity {

    private TextInputEditText mNameEdt, mTypeEdt, mDurationEdt, mImageEdt, mDescriptionEdt, mStartDateEdt, mEndDateEdt;
    private Button addMoviesBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
//    private String mId;
    int max = 0 ;

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

        addMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processinert();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Movies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                max = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void processinert() {

        if (TextUtils.isEmpty(mNameEdt.getText().toString())){
            mNameEdt.setError("Name is Required");
            mNameEdt.requestFocus();
            return;
        }
        if(mNameEdt.length()>=15){
            mNameEdt.setError("Name is to long. Max 15 Characters");
            mNameEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mTypeEdt.getText().toString())){
            mTypeEdt.setError("Type is Required");
            mTypeEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mDurationEdt.getText().toString())){
            mDurationEdt.setError("Duration is Required");
            mDurationEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mImageEdt.getText().toString())){
            mImageEdt.setError("Image Link is Require");
            mImageEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mDescriptionEdt.getText().toString())){
            mDescriptionEdt.setError("Description is Require");
            mDescriptionEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mStartDateEdt.getText().toString())){
            mStartDateEdt.setError("Start Date is Require");
            mStartDateEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mEndDateEdt.getText().toString())){
            mEndDateEdt.setError("End Date is Require");
            mEndDateEdt.requestFocus();
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("mName", mNameEdt.getText().toString().trim());
        map.put("mType", mTypeEdt.getText().toString().trim());
        map.put("mDuration", mDurationEdt.getText().toString().trim());
        map.put("mImage", mImageEdt.getText().toString().trim());
        map.put("mDescription", mDescriptionEdt.getText().toString().trim());
        map.put("mStartDate", mStartDateEdt.getText().toString().trim());
        map.put("mEndDate", mEndDateEdt.getText().toString().trim());
        map.put("id", String.valueOf((max + 1)));


        databaseReference.child(String.valueOf(max + 1)).setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mNameEdt.setText("");
                        mTypeEdt.setText("");
                        mDurationEdt.setText("");
                        mImageEdt.setText("");
                        mDescriptionEdt.setText("");
                        mStartDateEdt.setText("");
                        mEndDateEdt.setText("");

                        Toast.makeText(getApplicationContext(), "Movies Added Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Movies Not added", Toast.LENGTH_LONG).show();
                    }
                });

    }
}