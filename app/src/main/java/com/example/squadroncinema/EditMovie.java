//package com.example.squadroncinema;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
////import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EditMovie extends AppCompatActivity {
//
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
//
