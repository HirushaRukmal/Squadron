package com.example.squadroncinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

public class RazorPaymentPortalActivity extends AppCompatActivity {

    Button btpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_payment_portal);

        //assign variables
        btpay = findViewById(R.id.bt_pay);

        //Initalize amount
        String sAmount = "100";

        //Convert and Round off
        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initalize razorpay checkout
                Checkout checkout = new Checkout();
                //set key id
                checkout.setKeyID("rzp_test_EbjsmZ4glTMO8v");
                //set Image
                checkout.setImage(R.drawable.rzp_logo);
                //initalize json object
                JSONObject object = new JSONObject();
                try {
                    //put name
                    object.put("name", "Test Code");
                    //put Description
                    object.put("description", "test Payment");
                    //put currancy unit
                    object.put("currancy", "SLR");
                    //pay amount
                    object.put("amount", amount);
                    //put mobile number
                    object.put("prefill contact", "0778078365");
                    // put email
                    object.put("prefill email", "testcode@gmail.com");
                    //open razorpay checkout activity
                    checkout.open(RazorPaymentPortalActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
//
//    @Override
//    public void onPaymentSuccess(String s) {
//        //initalize alert dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        //set title
//        builder.setTitle("Payment ID");
//        //set message
//        builder.setMessage(s);
//        //show alert dialog
//        builder.show();
//    }
//
//    @Override
//    public void onPaymentError(int i, String s) {
//        //display toast
//        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//    }
//}