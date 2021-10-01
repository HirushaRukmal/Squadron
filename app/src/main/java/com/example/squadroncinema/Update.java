package com.example.squadroncinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.squadroncinema.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class Update extends AppCompatActivity {

    /*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the update profile
     */

    //declaring variables
    private TextView fName, lName, email, telephone;
    private Button delete,update;
    private FirebaseAuth fAuth;
    private DatabaseReference ref;
    private FirebaseFirestore fStore;
    private String userId;
    private FirebaseUser fuser;
    ActivityUpdateBinding binding;
    FloatingActionButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Viewing tha application on fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the titlebar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar*/


        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName     = binding.firstname.getText().toString();
                String lName     = binding.lastnamePlacholder.getText().toString();
                String email     = binding.email.getText().toString();
                String telephone = binding.number.getText().toString();

                updateData(fName, lName, email, telephone);

            }
        });

        //initializing the variables to the activity components
        fName     = findViewById(R.id.firstname);
        lName     = findViewById(R.id.lastname_placholder);
        email     = findViewById(R.id.email);
        telephone = findViewById(R.id.number);
        delete    = findViewById(R.id.delete);
        update    = findViewById(R.id.update);

        //initializing the variables to the firebase db
        fAuth  = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //initializing the variables to the current user
        userId = fAuth.getCurrentUser().getUid();
        fuser  = fAuth.getCurrentUser();

        //cancel button onclick
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Update.this, Myprofile.class);
                startActivity(i);
            }
        });

        //initializing the variables to the fire store collection "Users"
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                //setting the variables to the fields in the fire store collection
                fName.setText(value.getString("firstname"));
                lName.setText(value.getString("lastname"));
                email.setText(value.getString("Email"));
                telephone.setText(value.getString("Telephone"));

            }
        });

    }

    private void updateData(String fName, String lName, String email, String telephone) {

        HashMap User = new HashMap();
        User.put("firstname", fName);
        User.put("lastname", lName);
        User.put("Email", email);
        User.put("Telephone", telephone);

        ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userId).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    fStore.collection("users").document(userId).update(User).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(Update.this,"Updated successfully!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Update.this, Myprofile.class);
                            startActivity(i);
                        }
                    });
                    binding.firstname.setText("");
                    binding.lastnamePlacholder.setText("");
                    binding.email.setText("");
                    binding.number.setText("");


                }else{

                    Toast.makeText(Update.this,"Failed to update", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}