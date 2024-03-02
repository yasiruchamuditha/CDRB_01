package com.example.cdrb_01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Vaccination_Card extends AppCompatActivity {

    private String receivedValue;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_card);

        // Initialize the TextView
        textView = findViewById(R.id.textView); // Replace with your actual TextView ID

        Intent intentVaccination = getIntent();
        if (intentVaccination != null && intentVaccination.hasExtra("INPUT_VALUE_Vaccination")) {
            receivedValue = intentVaccination.getStringExtra("INPUT_VALUE_Vaccination");
        }

        // Set the text to the TextView
        textView.setText("Vaccination Card : " + receivedValue);
    }
}
