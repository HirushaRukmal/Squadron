package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestPaymentActivity extends AppCompatActivity implements PaymentRVAdapter.ShowClickInterface, PaymentResultListener {

    //Initialize the variables
    private RecyclerView paymentRV;
    private ProgressBar loadingPB;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ArrayList<MovieTicketBookingRVModal> movieTicketBookingRVModalArrayList;

    private RelativeLayout bottomSheetRL;

    private PaymentRVAdapter paymentRVAdapter;
    private String PaymnetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_payment);

        paymentRV = findViewById(R.id.idRVPayment);
        bottomSheetRL = findViewById(R.id.idRLBPaymentSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        movieTicketBookingRVModalArrayList = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("MovieTicketBooking");

        paymentRVAdapter = new PaymentRVAdapter(movieTicketBookingRVModalArrayList, this, this);

        paymentRV.setLayoutManager(new LinearLayoutManager(this));

        paymentRV.setAdapter(paymentRVAdapter);

        getAllShowTicketDetails();
    }

    private void getAllShowTicketDetails() {
        movieTicketBookingRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                movieTicketBookingRVModalArrayList.add(snapshot.getValue(MovieTicketBookingRVModal.class));
                paymentRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                paymentRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                paymentRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                paymentRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingPB.setVisibility(View.GONE);
                paymentRVAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onPaymentClick(int position) {
        displayBottomSheet(movieTicketBookingRVModalArrayList.get(position));
    }

    private void displayBottomSheet(MovieTicketBookingRVModal movieTicketBookingRVModal) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.activity_bottom_sheet_payment, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

//        idIVMovie, idTVFilmName, idTVMovieDuration, idTVMovieDesc, idSeatNumbers, idTicketCount, idBookedDate, idTicketType, idTotal
        ImageView movieIV = layout.findViewById(R.id.idIVMovie);
        TextView FilmNameTV = layout.findViewById(R.id.idTVFilmName);
        TextView MovieDurationTV = layout.findViewById(R.id.idTVMovieDuration);
        TextView MovieDescTV = layout.findViewById(R.id.idTVMovieDesc);
        TextView SeatNumbersTV = layout.findViewById(R.id.idSeatNumbers);
        TextView TicketCountTV = layout.findViewById(R.id.idTicketCount);
        TextView BookedDateTV = layout.findViewById(R.id.idBookedDate);
        TextView TicketTypeTV = layout.findViewById(R.id.idTicketType);
        TextView TotalTV = layout.findViewById(R.id.idTotal);

        Button updateBtn = layout.findViewById(R.id.idBtnUpdate);
        Button payBtn = layout.findViewById(R.id.idBtnPay);
        Button payerDetails = layout.findViewById(R.id.idSavePayerDetails);

        Picasso.get().load(movieTicketBookingRVModal.getmImage()).into(movieIV);
        FilmNameTV.setText("Title : " + movieTicketBookingRVModal.getmName());
        MovieDurationTV.setText("Duration : " + movieTicketBookingRVModal.getmDuration() + " hours");
        MovieDescTV.setText(movieTicketBookingRVModal.getmDescription());
        SeatNumbersTV.setText("Seat Numbers : " + movieTicketBookingRVModal.getSeatNumber());
        TicketCountTV.setText("Ticket Count : " + movieTicketBookingRVModal.getTicketCount());
        BookedDateTV.setText("Booked Date : " + movieTicketBookingRVModal.getBookedDate());
        TicketTypeTV.setText("Ticket Type : " + movieTicketBookingRVModal.getTicketType());

        switch (movieTicketBookingRVModal.getStatus()){
            case "2":
                updateBtn.setVisibility(View.GONE);
                payBtn.setVisibility(View.GONE);
                break;
            case "1":
                updateBtn.setVisibility(View.VISIBLE);
                payBtn.setVisibility(View.VISIBLE);
                break;
        }

        String ticketPrice, ticketcount, ticketID;
        Float Total;

        //ticket price-ticket count /ticket total
        ticketID = movieTicketBookingRVModal.getTicketId();
        ticketPrice = movieTicketBookingRVModal.getTicketPrice();
        ticketcount = movieTicketBookingRVModal.getTicketCount();
        Total = Float.parseFloat(ticketPrice) * Float.parseFloat(ticketcount);

        TotalTV.setText("Total : Rs." + Total);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestPaymentActivity.this, EditPaymentActivity.class);
                i.putExtra("MovieTicketBooking", movieTicketBookingRVModal);
                startActivity(i);
            }
        });

        payerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestPaymentActivity.this, AddPayerDetailsActivity.class);
                startActivity(i);
            }
        });

        //Calculation for the ticket price

        String sAmount = String.valueOf(Float.valueOf(ticketPrice) * Float.valueOf(ticketcount));

        int amount = Math.round(Float.parseFloat(sAmount) * 100);


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initalize razorpay checkout
                Checkout checkout = new Checkout();

                checkout.setKeyID("rzp_test_EbjsmZ4glTMO8v");

                //checkout.setImage(R.drawable.);
                JSONObject object = new JSONObject();
                try {
                    object.put("name", "Squadron Cinema");
                    object.put("description", ticketID);
                    object.put("currency", "LKR");
                    object.put("amount", amount);
                    object.put("prefill contact", "0778078365");
                    object.put("prefill email", "testcode@gmail.com");
                    checkout.open(TestPaymentActivity.this, object);
                } catch (JSONException e) {
                    Log.e("exception", "Error in starting Razorpay Checkout", e);
                }
            }
        });


    }

    @Override
    public void onPaymentSuccess(String razorpay_signature) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Payment ID");
//        builder.setMessage(razorpay_signature);
//        builder.show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PaymentDetails");
        String key = firebaseDatabase.getReference("PaymentDetails").push().getKey();
        Date currentTime = Calendar.getInstance().getTime();

        String uid = "pO78xh3m63XNkpRZxjfQmUjjILS2";
        String RazorpayPaymentID = razorpay_signature;
        String Date = String.valueOf(currentTime);
        PaymnetID = key;

        PaymentRVModel paymentRVModel = new PaymentRVModel(PaymnetID, RazorpayPaymentID, uid, "test", Date);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                databaseReference.child(PaymnetID).setValue(paymentRVModel);
                Toast.makeText(TestPaymentActivity.this, "Payment success..." + RazorpayPaymentID, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TestPaymentActivity.this, TestPaymentActivity.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TestPaymentActivity.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}