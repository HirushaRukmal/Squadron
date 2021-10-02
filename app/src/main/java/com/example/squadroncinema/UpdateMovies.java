package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import  com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdateMovies extends AppCompatActivity {



    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    private Button btndelete;
    DatabaseReference reff;
    private Button btnpri;

    EditText ab,ac,ad,ae,af,ag,ah;
    String Id;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_page);

        Intent secIntent = getIntent();

        Id=secIntent.getStringExtra("id");

        ab=(EditText)findViewById(R.id.idEdtMName);
        ac=(EditText)findViewById(R.id.idEdtMType);
        ad=(EditText)findViewById(R.id.idEdtMDuration);
        ae=(EditText)findViewById(R.id.idEdtMImage);
        af=(EditText)findViewById(R.id.idEdtMDescription);
        ag=(EditText)findViewById(R.id.idEdtMStartDate);
        ah=(EditText)findViewById(R.id.idEdtMEndDate);


        a=(TextView) findViewById(R.id.idEdtMName);
        b=(TextView) findViewById(R.id.idEdtMType);
        c=(TextView) findViewById(R.id.idEdtMDuration);
        d=(TextView) findViewById(R.id.idEdtMImage);
        e=(TextView) findViewById(R.id.idEdtMDescription);
        f=(TextView) findViewById(R.id.idEdtMStartDate);
        g=(TextView) findViewById(R.id.idEdtMEndDate);
        btndelete=(Button) findViewById(R.id.idBtnDelete);
        btnpri=(Button)findViewById(R.id.idBtnUpdate2);


        reff= FirebaseDatabase.getInstance().getReference().child("Movies").child("7");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mName =dataSnapshot.child("mName").getValue().toString();
                String mType =dataSnapshot.child("mType").getValue().toString();
                String mDuration =dataSnapshot.child("mDuration").getValue().toString();
                String mImage =dataSnapshot.child("mImage").getValue().toString();
                String mDescription =dataSnapshot.child("mDescription").getValue().toString();
                String mStartDate =dataSnapshot.child("mStartDate").getValue().toString();
                String mEndDate =dataSnapshot.child("mEndDate").getValue().toString();
                a.setText(mName);
                b.setText(mType);
                c.setText(mDuration);
                d.setText(mImage);
                e.setText(mDescription);
                f.setText(mStartDate);
                g.setText(mEndDate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

//        btnpri.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    if (TextUtils.isEmpty(a.getText().toString())) {
//                        Toast.makeText(getApplicationContext(), "Enter the Name", Toast.LENGTH_SHORT).show();
//                    } else {
//                        db.child("Movies").child("8").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                db = FirebaseDatabase.getInstance().getReference();
//                                db.child("Movies").child("8").child("mName").setValue(a.getText().toString().trim());
//                                db.child("Movies").child("8").child("mType").setValue(b.getText().toString().trim());
//                                db.child("Movies").child("8").child("mDuration").setValue(c.getText().toString().trim());
//                                db.child("Movies").child("8").child("mImage").setValue(d.getText().toString().trim());
//                                db.child("Movies").child("8").child("mDescription").setValue(e.getText().toString().trim());
//                                db.child("Movies").child("8").child("mStartDate").setValue(f.getText().toString().trim());
//                                db.child("Movies").child("8").child("mEndDate").setValue(g.getText().toString().trim());
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                        Toast.makeText(UpdateMovies.this, "Data updated Successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(UpdateMovies.this, EditMovie.class);
//                        startActivity(intent);
//                    }
//                } catch (NumberFormatException e) {
//                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        btnpri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNamea = ab.getText().toString().trim();
                String mTypea = ac.getText().toString().trim();
                String mDurationa = ad.getText().toString().trim();
                String mImagea = ae.getText().toString().trim();
                String mDescriptiona = af.getText().toString().trim();
                String mStartDatea = ag.getText().toString().trim();
                String mEndDatea = ah.getText().toString().trim();

                HashMap hashMap=new HashMap();
                hashMap.put("mName",mNamea);
                hashMap.put("mType",mTypea);
                hashMap.put("mDuration",mDurationa);
                hashMap.put("mImage",mImagea);
                hashMap.put("mDescription",mDescriptiona);
                hashMap.put("mStartDate",mStartDatea);
                hashMap.put("mEndDate",mEndDatea);

                reff.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(UpdateMovies.this, "Updated", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(UpdateMovies.this,EditMovie.class);
                        startActivity(intent2);
                    }
                });
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Movies").child("8").removeValue();
                Toast.makeText(UpdateMovies.this,  "Delete success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateMovies.this,EditMovie.class);
                startActivity(intent);
            }
        });

}

}