package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class DeleteFood extends AppCompatActivity {
    String str;
    TextView tv;
    String des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);

        tv = (TextView)findViewById(R.id.text1);
        Intent intent = getIntent();
        str = intent.getStringExtra("name");

        tv = (TextView)findViewById(R.id.text3);
        intent = getIntent();
        str = intent.getStringExtra("description");

        tv = (TextView)findViewById(R.id.text4);
        intent = getIntent();
        str = intent.getStringExtra("price");


        tv.setText(str);
    }
}