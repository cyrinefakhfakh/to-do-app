package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class secondactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        TextView buttonNavigate = findViewById(R.id.textView);
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(secondactivity.this, Myactivity3.class);
                startActivity(intent);
            }
        });



        TextView buttonNavigate2 = findViewById(R.id.textView2);
        buttonNavigate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(secondactivity.this, Myactivity4.class);
                startActivity(intent);
            }
        });



        TextView buttonNavigate3 = findViewById(R.id.textView3);
        buttonNavigate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(secondactivity.this, Myactivity5.class);
                startActivity(intent);
            }
        });





        TextView buttonNavigate4 = findViewById(R.id.textView44);
        buttonNavigate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SecondActivity", "Button 4 clicked");
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(secondactivity.this, Myactivity6.class);
                startActivity(intent);
            }
        });
        TextView buttonNavigate5 = findViewById(R.id.textView5);
        buttonNavigate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SecondActivity", "Button 4 clicked");
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(secondactivity.this, shopping.class);
                startActivity(intent);
            }
        });






    }
}