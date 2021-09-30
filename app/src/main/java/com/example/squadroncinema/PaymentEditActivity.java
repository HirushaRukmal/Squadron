package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PaymentEditActivity extends AppCompatActivity {

    private TextInputEditText ticketTypeEdt, ticketCountEdt, seatNumbersEdt;
    private Button updateTicketBtn;
    private Button deleteTicketBtn;
    private ProgressBar loadingPB;

    //firebase database
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //for unique id insert
    private String ticketID;

    //create a variable for the model
    private ShowTicketRVModel showTicketRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_edit);

        ticketTypeEdt = findViewById(R.id.idEdtTicketType);
        ticketCountEdt = findViewById(R.id.idEdtTicketCount);
        seatNumbersEdt = findViewById(R.id.idEdtSeatNumbers);
        updateTicketBtn = findViewById(R.id.idBtnUpdateInvoice);
        deleteTicketBtn = findViewById(R.id.idBtnDeleteInvoice);
        loadingPB = findViewById(R.id.idPBLoading);

        //inialize firebase db
        firebaseDatabase = FirebaseDatabase.getInstance();

        //get data from the table and added to the form
        showTicketRVModel = getIntent().getParcelableExtra("movieTicketDetails");

        if (showTicketRVModel != null) {
            ticketTypeEdt.setText(showTicketRVModel.getTicketType());
            ticketCountEdt.setText(showTicketRVModel.getTicketCount());
            seatNumbersEdt.setText(showTicketRVModel.getSeatNumbers());
            ticketID = showTicketRVModel.getUserid();
        }

        //pass course id as a child for update delete (referance what to delete and update)
        databaseReference = firebaseDatabase.getReference("movieTicketDetails").child(ticketID);

        updateTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);

                String ticketType = ticketTypeEdt.getText().toString();
                String tiketsTotal = "";
                if (ticketType == "gold" || ticketType == "Gold") {
                    tiketsTotal = "750";
                } else if (ticketType == "sliver" || ticketType == "Silver") {
                    tiketsTotal = "500";
                }
                String ticketCount = ticketCountEdt.getText().toString();
                String seatNumbers = seatNumbersEdt.getText().toString();

                //#map insert data to map
                Map<String, Object> map = new HashMap<>();
                map.put("ticketType", ticketType);
                map.put("tiketsTotal", tiketsTotal);
                map.put("ticketCount", ticketCount);
                map.put("seatNumbers", seatNumbers);
                map.put("userid", ticketID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(PaymentEditActivity.this, "Ticket Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PaymentEditActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loadingPB.setVisibility(View.GONE);
                        Toast.makeText(PaymentEditActivity.this, "Failed to Update ticket", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        //delete btn onclick
        deleteTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTicket();
            }
        });

    }

    //delete function
    private void deleteTicket() {
        databaseReference.removeValue();
        Toast.makeText(PaymentEditActivity.this, "Ticket Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(PaymentEditActivity.this, MainActivity.class));
    }
}