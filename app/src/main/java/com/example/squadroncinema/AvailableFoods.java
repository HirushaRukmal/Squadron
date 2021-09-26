package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AvailableFoods extends AppCompatActivity {

    private RecyclerView rv_list;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_foods);

        FirebaseDatabase.getInstance( "https://squadroncinema-default-rtdb.firebaseio.com//").getReference().child("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot.getChildren());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("TAG", "onCancelled: " + error);
            }
        });


        rv_list = findViewById(R.id.rv_list);
    }
}



