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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPayerDetailsActivity extends AppCompatActivity {

    //variables
    private Button payBtn;
    private TextInputEditText PayerNameEdt, PayerAddressEdt, PayerContactNumberEdt, PayerEmailEdt;
    private ProgressBar loadingPB;

    //firebase database
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //for unique id insert
    private String PayerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payer_details);

        //initialise all the variables
        PayerNameEdt = findViewById(R.id.idEdtPayerName);
        PayerAddressEdt = findViewById(R.id.idEdtPayerAddress);
        PayerContactNumberEdt = findViewById(R.id.idEdtPayerContactNumber);
        PayerEmailEdt = findViewById(R.id.idEdtPayerEmail);
        payBtn = findViewById(R.id.idBtnPay);
        loadingPB = findViewById(R.id.idPBLoading);

        //inialize firebase db
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PayerDetails");

        //unique key
        String key = firebaseDatabase.getReference("PayerDetails").push().getKey();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();

        String uid = "aIADTwZFxzPuJhMYSDruIMbtGO62";
            //onclick listner for insert
            payBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    loadingPB.setVisibility(View.VISIBLE);

                    PayerID = key;
                    String payerName = PayerNameEdt.getText().toString();
                    String payerAddress = PayerAddressEdt.getText().toString();
                    String payerEmail = PayerEmailEdt.getText().toString();
                    String payerContactNumber = PayerContactNumberEdt.getText().toString();


                    //pass all the above data to model class
                    PayerRVModel payerRVModel = new PayerRVModel(PayerID, payerName, payerAddress, payerEmail, payerContactNumber);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loadingPB.setVisibility(View.GONE);

                            databaseReference.child(PayerID).setValue(payerRVModel);
                            Toast.makeText(AddPayerDetailsActivity.this, "Payer Details add...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPayerDetailsActivity.this, TestPaymentActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(AddPayerDetailsActivity.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
    }

}