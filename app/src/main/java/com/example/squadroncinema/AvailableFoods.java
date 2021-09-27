package com.example.squadroncinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class AvailableFoods extends AppCompatActivity {

    private RecyclerView rv_list;
    DatabaseReference db;
    private ArrayList<Food> foodsList = new ArrayList<>();

    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_foods);

        /*FirebaseDatabase.getInstance( "https://squadroncinema-default-rtdb.firebaseio.com//").getReference().child("Food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot.getChildren());
                for(DataSnapshot data : snapshot.getChildren()){
                    Log.d("TAG", "onDataChange: " + data.getValue());
                    Log.d("TAG", "*********: " + data.getKey());

                    Object jsonObject = data.getValue();
                    String jsonInString = new Gson().toJson(jsonObject);
                    try {
                        JSONObject mJSONObject = new JSONObject(jsonInString);
                        Log.d("TAG", "onDataChange: " + mJSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("TAG", "onDataChange: " + foodsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("TAG", "onCancelled: " + error);
            }
        });*/

        Food f1 = new Food();
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

        Log.d("TAG", "onCreate: " + foodsList);


        rv_list = findViewById(R.id.rv_list);

        foodAdapter = new FoodAdapter(getApplicationContext(), foodsList);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(foodAdapter);
    }
}



