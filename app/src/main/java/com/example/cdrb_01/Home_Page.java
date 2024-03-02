package com.example.cdrb_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_Page extends AppCompatActivity {

    private CardView vaccinationCard, clinicCard, triposhaCard, dentalCard, laboratoryCard, eyeCard;
    private String receivedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Retrieve the value from the Intent
        Intent babyIntent = getIntent();
        if (babyIntent != null && babyIntent.hasExtra("INPUT_VALUE")) {
            receivedValue = babyIntent.getStringExtra("INPUT_VALUE");
        }

        vaccinationCard = findViewById(R.id.VaccinationCard);
        clinicCard = findViewById(R.id.ClinicCard);
        triposhaCard = findViewById(R.id.TriposhaCard);
        dentalCard = findViewById(R.id.DentalCard);
        laboratoryCard = findViewById(R.id.LaboratoryCard);
        eyeCard = findViewById(R.id.EyeCard);

        vaccinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Vaccination_Card.class, "INPUT_VALUE_Vaccination");
            }
        });

        clinicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Clinic_Card.class, "INPUT_VALUE_Clinic");
            }
        });

        triposhaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Triposha_Card.class, "INPUT_VALUE_Triposha");
            }
        });

        dentalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Dental_Card.class, "INPUT_VALUE_Dental");
            }
        });

        laboratoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Laboratory_Card.class, "INPUT_VALUE_Laboratory");
            }
        });

        eyeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Eye_Card.class, "INPUT_VALUE_Eye");
            }
        });
    }

    private void startNextActivity(Class<? extends AppCompatActivity> cls, String extraKey) {
        Intent intent = new Intent(Home_Page.this, cls);
        intent.putExtra(extraKey, receivedValue);
        startActivity(intent);
    }
}
