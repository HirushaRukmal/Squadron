package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class AvailableFoods extends AppCompatActivity implements FoodAdapter.onDeviceNameClickListener {

    private RecyclerView rv_list;
    DatabaseReference db;
    private ArrayList<Food> foodsList = new ArrayList<>();
    private ArrayList<CartData> cartData = new ArrayList<>();
    private Button btn_cart;

    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_foods);

        btn_cart = findViewById(R.id.btn_cart);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase.getInstance( "https://squadroncinema-default-rtdb.firebaseio.com//").getReference().child("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot.getChildren());
                for(DataSnapshot data : snapshot.getChildren()){
                    Log.d("TAG", "onDataChange: " + data.getValue());
                    Log.d("TAG", "*********: " + data.getKey());

                    Food receivedData = snapshot.getValue(Food.class);
                    Log.d("TAG", "onDataChange: ");


                    Object jsonObject = data.getValue();
                    String jsonInString = new Gson().toJson(jsonObject);
                    try {
                        JSONObject mJSONObject = new JSONObject(jsonInString);
                        Log.d("TAG", "onDataChange: " + mJSONObject);
                        Food food = new Food(mJSONObject.getString("name"),mJSONObject.getString("description"),mJSONObject.getString("price"));
                        foodsList.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("TAG", "onDataChange: " + foodsList);


                foodAdapter = new FoodAdapter(getApplicationContext(), foodsList, AvailableFoods.this::onDeviceNameClick);
                rv_list.setHasFixedSize(true);
                rv_list.setAdapter(foodAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("TAG", "onCancelled: " + error);
            }
        });

      /*  Food f1 = new Food();
        f1.setName("Pizza");
        f1.setDescription("Cheese and Onion");
        f1.setPrice("1500/=");

        Food f2 = new Food();
        f2.setName("Kottu");
        f2.setDescription("Cheese and Onion");
        f2.setPrice("700/=");

        Food f3 = new Food();
        f3.setName("Burger");
        f3.setDescription("Cheese and Chicken");
        f3.setPrice("1200/=");

        Food f4 = new Food();
        f4.setName("Fried Rice");
        f4.setDescription("Fish");
        f4.setPrice("675/=");

        foodsList.add(f1);
        foodsList.add(f2);
        foodsList.add(f3);
        foodsList.add(f4);

        Log.d("TAG", "onCreate: " + foodsList);*/

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send data to cart view
                Intent intent = new Intent(getApplicationContext(), PlaceOrder.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("cart_data", cartData);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onDeviceNameClick(int position, int qut) {
        Log.d("TAG", "onDeviceNameClick: " + position);

        CartData cartData_ = new CartData(foodsList.get(position), qut);
        cartData.add(cartData_);

        Log.d("TAG", "onDeviceNameClick: " + cartData);


    }
}



