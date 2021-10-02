package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {

    /*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the feedback
     */

    private DatabaseReference ref, no;
    private EditText femail, description;
    private Button submit, count;
    private FirebaseAuth mAuth;
    private int countno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        femail = findViewById(R.id.femail);
        description= findViewById(R.id.description);
        submit = findViewById(R.id.submit);
        mAuth=FirebaseAuth.getInstance();
        no  = FirebaseDatabase.getInstance().getReference().child("users");
        ref = FirebaseDatabase.getInstance().getReference().child("feedback");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertfeedback();

            }
        });

    }

    private void insertfeedback() {

        String fEmail = femail.getText().toString();
        String Description  = description.getText().toString().trim();

        //validating the form
        if (TextUtils.isEmpty(fEmail)){
            femail.setError("First name is required!");
            return;
        }

        if (TextUtils.isEmpty(Description)){
            description.setError("Last name is required!");
            return;

        }

        feedbackmodel feedbackmodel = new feedbackmodel(fEmail, Description);

        ref.push().setValue(feedbackmodel);
        Toast.makeText(Feedback.this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
    }
}