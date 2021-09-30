package com.example.squadroncinema;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaymentMainActivity extends AppCompatActivity implements PaymentRVAdapter.ShowClickInterface {

    private RecyclerView paymentRV;
    private ProgressBar loadingPB;

    //firebase db
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //retrive the list
    private ArrayList<ShowTicketRVModel> showTicketRVModelArrayList;

    //card for bottom
    private RelativeLayout bottomSheetRL;

    //card for bottom (details about ticket booking)
    private PaymentRVAdapter paymentRVAdapter;

    private TextInputEditText showSeatNumbers, showSeatCount, showTicketType, showTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);

        paymentRV = findViewById(R.id.idRVPayment);
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        showTicketRVModelArrayList = new ArrayList<>();

        //inialize firebase db
        databaseReference = firebaseDatabase.getReference("movieTicketDetails");


        paymentRVAdapter = new PaymentRVAdapter(showTicketRVModelArrayList, this, this);

        //layout manager
        paymentRV.setLayoutManager(new LinearLayoutManager(this));

        paymentRV.setAdapter(paymentRVAdapter);

        getAllShowTicketDetails();

    }

    private void getAllShowTicketDetails() {
        showTicketRVModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                showTicketRVModelArrayList.add(snapshot.getValue(ShowTicketRVModel.class));
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
        TextView showNameTV = layout.findViewById(R.id.idTVFilmName);
        TextView showDescTV = layout.findViewById(R.id.idTVFilmDesc);
        TextView showDuration = layout.findViewById(R.id.idTVMovieDuration);
        TextView showDate = layout.findViewById(R.id.idTVBookedDate);
        ImageView showIV = layout.findViewById(R.id.idIVFilm);
         showTotal = findViewById(R.id.idEdtTotal);
         showTicketType = findViewById(R.id.idEdtTicketType);
         showSeatCount = findViewById(R.id.idEdtTotalCount);
              showSeatNumbers = findViewById(R.id.idEdtSeatNumbers);

        //for the two buttons
        Button edtbtn = layout.findViewById(R.id.idBtnEdit);
        Button payBtn = layout.findViewById(R.id.idBtnPay);

        //get data to the card
        showNameTV.setText(showTicketRVModel.getMovieTitle());
        showDescTV.setText(showTicketRVModel.getMovieDescription());
        showDuration.setText(showTicketRVModel.getMovieDuration());
        showDate.setText(showTicketRVModel.getBookedDate());
//        showTotal.setText(showTicketRVModel.getTiketsTotal());
//        showTicketType.setText(showTicketRVModel.getTicketType());
//        showSeatCount.setText(showTicketRVModel.getTicketCount());
//        showSeatNumbers.setText(showTicketRVModel.getSeatNumbers());
        Picasso.get().load(showTicketRVModel.getMovieImageLink()).into(showIV);


        edtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaymentMainActivity.this, PaymentEditActivity.class);
                i.putExtra("movieTicketDetails", showTicketRVModel);
                startActivity(i);
            }
        });


        //Calculation for the ticket price
        String TicketPrice = showTicketRVModel.getTiketsTotal();
        String ticketCount = showTicketRVModel.getTicketCount();

        String sAmount = String.valueOf(Float.valueOf(TicketPrice) * Float.valueOf(ticketCount));

        //Convert and Round off
        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initalize razorpay checkout
                Checkout checkout = new Checkout();
                //set key id
                checkout.setKeyID("rzp_test_EbjsmZ4glTMO8v");
                //set Image
                checkout.setImage(R.drawable.rzp_logo);
                //initalize json object
                JSONObject object = new JSONObject();
                try {
                    //put name
                    object.put("name", "Invoice #123");
                    //put Description
                    object.put("description", "Invoice");
                    //put currancy unit
                    object.put("currency", "LKR");
                    //pay amount
                    object.put("amount", amount);
                    //put mobile number
                    object.put("prefill contact", "0778078365");
                    // put email
                    object.put("prefill email", "testcode@gmail.com");
                    //open razorpay checkout activity
                    checkout.open(PaymentMainActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
