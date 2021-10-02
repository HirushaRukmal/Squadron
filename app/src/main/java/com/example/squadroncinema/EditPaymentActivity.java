package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditPaymentActivity extends AppCompatActivity {

    private TextInputEditText MovieSeatNumbers, MovieDuration, MovieTitle, MovieTicketCount;
    private ImageView IVMovie;
    private Button updateTicketBtn;
    private Button deleteTicketBtn;
    private ProgressBar loadingPB;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String ticketID;
    private String bookedDate;

    private MovieTicketBookingRVModal movieTicketBookingRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        //dropdown
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditPaymentActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.planets_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //get current date time
        Date currentTime = Calendar.getInstance().getTime();

        MovieSeatNumbers = findViewById(R.id.idEdtMovieSeatNumbers);
        MovieDuration = findViewById(R.id.idEdtMovieDuration);
        MovieTitle = findViewById(R.id.idEdtMovieTitle);
        MovieTicketCount = findViewById(R.id.idEdtMovieTicketCount);
//        spinner.setOnItemSelectedListener("");

        Spinner mySpinner = (Spinner) findViewById(R.id.planets_spinner);
        IVMovie = findViewById(R.id.idIVMovie);

        updateTicketBtn = findViewById(R.id.idBtnUpdateTicket);
        deleteTicketBtn = findViewById(R.id.idBtnDeleteTicket);

        loadingPB = findViewById(R.id.idPBLoading);

        firebaseDatabase = FirebaseDatabase.getInstance();
        movieTicketBookingRVModal = getIntent().getParcelableExtra("MovieTicketBooking");

        if (movieTicketBookingRVModal != null) {
            MovieSeatNumbers.setText(movieTicketBookingRVModal.getSeatNumber());
            MovieDuration.setText(movieTicketBookingRVModal.getmDuration() + " hours");
            MovieTitle.setText(movieTicketBookingRVModal.getmName());
            MovieTicketCount.setText(movieTicketBookingRVModal.getTicketCount());
            Picasso.get().load(movieTicketBookingRVModal.getmImage()).into(IVMovie);
            ticketID = movieTicketBookingRVModal.getTicketId();
            bookedDate = movieTicketBookingRVModal.getBookedDate();
        }

        databaseReference = firebaseDatabase.getReference("MovieTicketBooking").child(ticketID);

        updateTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);

                //validation
//                if((Float.parseFloat(String.valueOf(currentTime))-Float.parseFloat(bookedDate))>3){
//
//                }else {
                String movieSeatNumber = MovieSeatNumbers.getText().toString();
                String movieTicketCount = MovieTicketCount.getText().toString();
                String movieTicketType = mySpinner.getSelectedItem().toString();
                String movieTicketPrice = "0";

                switch (movieTicketType) {
                    case "Gold":
                        movieTicketPrice = "750";
                        break;
                    case "Silver":
                        movieTicketPrice = "500";
                        break;
                    case "Platinum":
                        movieTicketPrice = "1000";
                        break;
                }

                if (TextUtils.isEmpty(MovieSeatNumbers.getText().toString()) || TextUtils.isEmpty(MovieTicketCount.getText().toString())) {
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(EditPaymentActivity.this, "Movie Seat Number and Ticket Count Required", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("seatNumber", movieSeatNumber);
                    map.put("ticketCount", movieTicketCount);
                    map.put("status", "1");
//                map.put("updatedDateTime", "2021/05/21");
                    map.put("ticketId", ticketID);
                    map.put("ticketType", movieTicketType);
                    map.put("ticketPrice", movieTicketPrice);
//                }

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loadingPB.setVisibility(View.GONE);
                            databaseReference.updateChildren(map);
                            Toast.makeText(EditPaymentActivity.this, "Ticket Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditPaymentActivity.this, TestPaymentActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(EditPaymentActivity.this, "Failed to Update ticket", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        //delete btn onclick
        deleteTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });
    }

    //delete function
    private void deleteCourse() {
        databaseReference.removeValue();
        Toast.makeText(EditPaymentActivity.this, "Ticket Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditPaymentActivity.this, TestPaymentActivity.class));
    }
}