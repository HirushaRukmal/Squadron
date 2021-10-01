package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class PlaceOrder extends AppCompatActivity {

    private ArrayList<CartData> cartData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        cartData = getIntent().getExtras().getParcelableArrayList("cart_data");
        Log.d("TAG", "onCreate: ");
    }

}