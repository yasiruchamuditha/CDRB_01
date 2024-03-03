package com.example.cdrb_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Page extends AppCompatActivity {

    private CardView vaccinationCard, clinicCard, triposhaCard, dentalCard, laboratoryCard, eyeCard;
    private Button profileView;
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
        profileView=findViewById(R.id.profileDetails);
        vaccinationCard = findViewById(R.id.VaccinationCard);
        clinicCard = findViewById(R.id.ClinicCard);
        triposhaCard = findViewById(R.id.TriposhaCard);
        dentalCard = findViewById(R.id.DentalCard);
        laboratoryCard = findViewById(R.id.LaboratoryCard);
        eyeCard = findViewById(R.id.EyeCard);


        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(Medical_info.class, "INPUT_VALUE_ProfileView");
            }
        });

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
                //startNextActivity(Dental_Card.class, "INPUT_VALUE_Dental");
                Intent LogoutIntent = new Intent(Home_Page.this, Login_Page.class);
                startActivity(LogoutIntent);
                finish(); // Finish
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
