package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class DeleteFood extends AppCompatActivity {
    String str;
    TextView tv, tv2, tv3, tv4;
    String des;
    String pri;
    private Button deletefood;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);
        deletefood = findViewById(R.id.delete1);

        tv = (TextView) findViewById(R.id.text1);
        tv2 = (TextView) findViewById(R.id.text3);
        tv3 = (TextView) findViewById(R.id.text4);


        Intent intent = getIntent();
        str = intent.getStringExtra("name");


        des = intent.getStringExtra("des");


        pri = intent.getStringExtra("price");


        tv.setText(str);
        tv2.setText(des);
        tv3.setText(pri);

        deletefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(getApplicationContext(), DeleteSuccesfull.class);
                startActivity(intent_main);
            }
        });



    }


}
