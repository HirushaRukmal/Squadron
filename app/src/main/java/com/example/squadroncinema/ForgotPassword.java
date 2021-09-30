package com.example.squadroncinema;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText pswdemail;
    private Button resetbtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar

        setContentView(R.layout.activity_forgot_password);

        pswdemail = findViewById(R.id.pswdemail);
        resetbtn  = findViewById(R.id.resetbtn);
        mAuth     = FirebaseAuth.getInstance();

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = pswdemail.getText().toString().trim();

                //validating if the field is empty
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ForgotPassword.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPassword.this,"Check your mailbox to reset your password", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            }else{
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPassword.this,"Enter a registered email address", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}