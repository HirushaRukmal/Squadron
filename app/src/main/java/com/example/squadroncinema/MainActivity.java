package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    EditText fname,description,price;
    Button btn_save, btn_view,btn_update,btn_delete;
    Food fooObj;
    DatabaseReference dbRef;

    DatabaseReference db;
    Food f1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.fname);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        btn_save = findViewById(R.id.button);

    f1 = new Food();


    }

    public void senddata (View v){
        db = FirebaseDatabase.getInstance( "https://squadroncinema-default-rtdb.firebaseio.com//").getReference().child("Food");
        if(TextUtils.isEmpty(fname.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter tourist name", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(price.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please enter a email", Toast.LENGTH_LONG).show();
        }else{

//          Tourist newtourist = new Tourist(ed_regname.getText().toString().trim(), ed_regemail.getText().toString().trim(), Integer.parseInt(ed_regcontact.getText().toString().trim()), ed_regtype.getText().toString().trim() );

           // f1 = new Food(fname.getText().toString().trim(), description.getText().toString().trim(), price.getText().toString().trim());

            f1.setName(fname.getText().toString().trim());
            f1.setPrice(price.getText().toString().trim());
            f1.setName(description.getText().toString().trim());

            db.push().setValue(f1);

            Toast.makeText(getApplicationContext(),"Registration successfull",Toast.LENGTH_LONG).show();
        }

    }

}