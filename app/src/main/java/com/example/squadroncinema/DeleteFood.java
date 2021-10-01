package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class DeleteFood extends AppCompatActivity {
    String str;
    TextView tv, tv2, tv3, tv4;
    String des;
    String pri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);

        tv = (TextView)findViewById(R.id.text1);
        tv2 = (TextView)findViewById(R.id.text3);
        tv3 = (TextView)findViewById(R.id.text4);


        Intent intent = getIntent();
        str = intent.getStringExtra("name");


        des = intent.getStringExtra("des");


        pri = intent.getStringExtra("price");


        tv.setText(str);
        tv2.setText(des);
        tv3.setText(pri);
    }
}