package com.example.squadroncinema;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity{

    /*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the registration page
     */

    //declaring variables
    private EditText firstnamereg,telephone, pswd, cpswd, email,lastname;
    private Button buttonregister;
    private TextView login_link;
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private FirebaseFirestore fstore;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Viewing tha application on fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar

        //referred layout
        setContentView(R.layout.activity_register);

        //initializing all variables
        firstnamereg    = (EditText) findViewById(R.id.firstnamereg);
        lastname        = (EditText) findViewById(R.id.lastname);
        email           = (EditText) findViewById(R.id.pswdemail);
        telephone       = (EditText) findViewById(R.id.telephone);
        pswd            = (EditText) findViewById(R.id.password);
        cpswd           = (EditText) findViewById(R.id.confirmpassword);
        buttonregister  = findViewById(R.id.buttonlogin);
        login_link      = findViewById(R.id.login_link);
        mAuth           = FirebaseAuth.getInstance();
        fstore          = FirebaseFirestore.getInstance();

        //creating a link to login form
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        //creating a link to registration
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstnamereg.getText().toString();
                String lastName  = lastname.getText().toString().trim();
                String Email     = email.getText().toString().trim();
                String Tel       = telephone.getText().toString().trim();
                String Pswd      = pswd.getText().toString().trim();
                String Cpswd     = cpswd.getText().toString().trim();

                //validating the form
                if (!Pswd.equals(Cpswd)) {
                    Toast.makeText(Register.this, "Please enter a valid ", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(firstName)){
                    firstnamereg.setError("First name is required!");
                    return;
                }

                if (TextUtils.isEmpty(lastName)){
                    lastname.setError("Last name is required!");
                    return;
                }

                if (TextUtils.isEmpty(Email)){
                    email.setError("Email Address is required!");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Please provide a valid email address!");
                    return;
                }

                if (TextUtils.isEmpty(Tel)){
                    telephone.setError("Mobile number is required!");
                    telephone.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Pswd)){
                    pswd.setError("Password is required!");
                    return;
                }

                if (TextUtils.isEmpty(Cpswd)){
                    cpswd.setError("Confirm Password is required!");
                    return;
                }

                if (!Pswd.equals(Cpswd)){
                    cpswd.setError("Password Mismatched!");
                    return;
                }

                else{
                    //sending data to firebase
                    mAuth.createUserWithEmailAndPassword(Email, Pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){

                                //displaying an error if task not successful
                                String error = task.getException().toString();
                                Toast.makeText(Register.this, "Error" + error, Toast.LENGTH_SHORT).show();
                            }else{

                                //sending data to firebase db
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserId);
                                HashMap userInfo = new HashMap();
                                userInfo.put("id", currentUserId);
                                userInfo.put("firstname", firstName);
                                userInfo.put("lastname", lastName);
                                userInfo.put("Email", Email);
                                userInfo.put("Telephone", Tel);
                                userInfo.put("Password", Pswd);

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                if (task.isSuccessful()){

                                                    //displaying a toast message "User Registered Successfully"
                                                    Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();

                                                    //sending data to fire store collection
                                                    userid = mAuth.getCurrentUser().getUid();
                                                    DocumentReference documentReference = fstore.collection("users").document(userid);
                                                    Map<String,Object> user = new HashMap<>();
                                                    user.put("firstname",firstName);
                                                    user.put("lastname", lastName);
                                                    user.put("Email", Email);
                                                    user.put("Telephone", Tel);
                                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            //Log.d(TAG,"onSuccess: user profile is created for"+ userid);
                                                        }
                                                    });
                                                }else {
                                                    Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                }
                                                finish();
                                            }
                                        });


                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}