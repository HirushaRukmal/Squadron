package com.example.squadroncinema;

import static com.google.firebase.firestore.FieldValue.delete;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

public class Myprofile extends AppCompatActivity {

    /*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the user profile
     */


    //declaring variables
    private TextView fName, lName, email, telephone;
    private Button delete,update;
    FloatingActionButton btn;
    private FirebaseAuth fAuth;
    private DatabaseReference ref;
    private FirebaseFirestore fStore;
    private String userId, secondaryApp;
    private FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Viewing tha application on fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the titlebar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar*/

        //referred layout
        setContentView(R.layout.activity_myprofile);

        //initializing the variables to the activity components
        fName     = findViewById(R.id.firstname);
        lName     = findViewById(R.id.lastname_placholder);
        email     = findViewById(R.id.email);
        telephone = findViewById(R.id.number);
        delete    = findViewById(R.id.delete);
        update    = findViewById(R.id.update);
        btn = findViewById(R.id.feedbackbtn);

        //initializing the variables to the firebase db
        fAuth  = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //initializing the variables to the current user
        userId = fAuth.getCurrentUser().getUid();
        fuser  = fAuth.getCurrentUser();

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

        //setting the delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteprofile();
            }
        });

        //setting the update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //redirecting to the update page
                Intent i = new Intent(Myprofile.this, Update.class);
                startActivity(i);
            }
        });

        //setting the feedback button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Myprofile.this, Feedback.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                Toast.makeText(this, "User Logged Out Succefully", Toast.LENGTH_SHORT).show();
                fAuth.signOut();
                Intent i = new Intent(Myprofile.this, Login.class);
                startActivity(i);
                this.finish();
                return true;
            case R.id.cal:
                Intent j = new Intent(Myprofile.this, Calculator.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //Delete function to delete the profile from fire store collection and real time db
    public void deleteprofile() {

        //deleting data from the realtime db
        ref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        ref.removeValue();

        //deleting data from the firebase auth
        fuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        //deleting data from the fire store collection
        fStore.collection("users").document(userId).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();

                            //displaying a toast message "Profile deleted successfully!"
                            Toast.makeText(Myprofile.this, "Profile deleted successfully!", Toast.LENGTH_LONG).show();

                            //redirecting to login after deleting user profile
                            Intent i = new Intent(Myprofile.this, Login.class);
                            startActivity(i);
                        }else{

                            //displaying a toast message with the error
                            Toast.makeText(Myprofile.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
        });
    }
}