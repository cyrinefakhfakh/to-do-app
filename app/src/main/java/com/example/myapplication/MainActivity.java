package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNavigate = findViewById(R.id.buttonNavigate);
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(MainActivity.this, android.R.layout.class);
                startActivity(intent);
            }
        });
    }
}
