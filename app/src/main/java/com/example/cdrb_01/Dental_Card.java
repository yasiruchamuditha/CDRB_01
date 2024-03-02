package com.example.cdrb_01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Dental_Card extends AppCompatActivity {

    private String receivedValue;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_card);

        // Initialize the TextView
        textView = findViewById(R.id.textView); // Replace with your actual TextView ID

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INPUT_VALUE_Dental")) {
            receivedValue = intent.getStringExtra("INPUT_VALUE_Dental");
        }

        // Set the text to the TextView
        textView.setText("Dental Card : " + receivedValue);
    }
}
