//package com.example.squadroncinema;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class AddMovie extends AppCompatActivity {
//
//    private TextInputEditText bookedDate, movieDescription, movieDuration, movieImageLink,
//            movieTitle, seatNumbers, status, ticketCount, ticketType,tiketsTotal,userid,username;
//    private Button addMovieBtn;
//    private ProgressBar loadingPB;
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//    private String movieID;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_movie);
//        bookedDate = findViewById(R.id.idEdtMovieName);
//        movieDescription = findViewById(R.id.idEdtMovieType);
//        movieDuration = findViewById(R.id.idEdtMovieDuration);
//        movieImageLink = findViewById(R.id.idEdtMovieImage);
//        movieTitle = findViewById(R.id.idEdtCoverImage);
//        seatNumbers = findViewById(R.id.idEdtMovieDescription);
//        status = findViewById(R.id.idEdtMovieStatus);
//        ticketCount = findViewById(R.id.idEdtStartDate);
//        ticketType = findViewById(R.id.idEdtEndDate);
//        tiketsTotal = findViewById(R.id.idEdtEndDate);
//        userid = findViewById(R.id.idEdtEndDate);
//        username = findViewById(R.id.idEdtEndDate);
//        addMovieBtn = findViewById(R.id.idBtnAddMovies);
//        loadingPB = findViewById(R.id.idPBLoading);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("movieTicketDetails");
//
//        addMovieBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadingPB.setVisibility(View.VISIBLE);
//                String bookedDate = movieNameEdt.getText().toString();
//                String movieDescription = movieTypeEdt.getText().toString();
//                String movieDuration = movieDurationEdt.getText().toString();
//                String movieImageLink = movieImageEdt.getText().toString();
//                String movieTitle = movieCoverEdt.getText().toString();
//                String seatNumbers = movieDescriptionEdt.getText().toString();
//                String status = movieStatusEdt.getText().toString();
//                String ticketCount = movieStartEdt.getText().toString();
//                String ticketType = movieEndEdt.getText().toString();
//                String tiketsTotal = movieEndEdt.getText().toString();
//                String userid = movieEndEdt.getText().toString();
//                String username = movieEndEdt.getText().toString();
//                movieID = movieName;
//                com.example.squadroncinema.ShowTicketRVModel showTicketRVModel = new com.example.squadroncinema.ShowTicketRVModel(bookedDate, movieDescription, movieDuration, movieImageLink,movieTitle, seatNumbers, status, ticketCount, ticketType,tiketsTotal,userid,username, movieID);
//
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        loadingPB.setVisibility(View.GONE);
//                        databaseReference.child(movieID).setValue(showTicketRVModel);
//                        Toast.makeText(AddMovie.this, "Movie Added..", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(AddMovie.this, com.example.squadroncinema.MovieMainActivity.class));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(AddMovie.this, "Error is" + error.toString(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//        });
//    }
//}