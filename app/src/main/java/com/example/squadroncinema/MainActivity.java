package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*
    Author    : Hirusha Rukmal
    Developed : Android Studio Arctic Fox | 2020.3.1 patch 2
    Implnote  : Development of the Welcome page
     */

    //declaring variables
    private Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Viewing tha application on fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //hiding the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();  //hiding the actionbar

        //referred layout
        setContentView(R.layout.activity_main);

        //initializing the variables to the activity components
        Continue      = findViewById(R.id.Continue);

        //setting the get started button
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //redirecting to login after deleting user profile
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
    }
}