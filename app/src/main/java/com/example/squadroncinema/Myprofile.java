package com.example.squadroncinema;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Myprofile extends AppCompatActivity {

    private NavigationView nav_view;
    private TextView fName, lName, email, telephone;
    private DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the titlebar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar


        setContentView(R.layout.activity_myprofile);

        fName     = nav_view.getHeaderView(0).findViewById(R.id.firstnamereg);
        lName     = nav_view.getHeaderView(0).findViewById(R.id.lastname);
        email     = nav_view.getHeaderView(0).findViewById(R.id.email);
        telephone = nav_view.getHeaderView(0).findViewById(R.id.telephone);

        userRef   = FirebaseDatabase.getInstance().getReference().child("user").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        );


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    String firstname = snapshot.child("firstname").getValue().toString();
                    fName.setText(firstname);
                    String lastname = snapshot.child("lastname").getValue().toString();
                    lName.setText(lastname);
                    String Email = snapshot.child("Email").getValue().toString();
                    email.setText(Email);
                    String Telephone = snapshot.child("Telephone").getValue().toString();
                    telephone.setText(Telephone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}