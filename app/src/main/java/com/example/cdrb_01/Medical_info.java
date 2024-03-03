package com.example.cdrb_01;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Medical_info extends AppCompatActivity {

    private String receivedValue;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_info);

        Intent intentVaccination = getIntent();
        if (intentVaccination != null && intentVaccination.hasExtra("INPUT_VALUE_ProfileView")) {
            receivedValue = intentVaccination.getStringExtra("INPUT_VALUE_ProfileView");
        }

        back_button = findViewById(R.id.back_button);

        // Add OnClickListener to the back_button
        back_button.setOnClickListener(view -> {
            // Create an Intent to navigate back to the home page
            Intent MedicalbackIntent = new Intent(Medical_info.this, Home_Page.class);
            startActivity(MedicalbackIntent);
            finish(); // Finish
        });


        // Initialize TextViews for medical information
        TextView textName = findViewById(R.id.text_name);
        TextView textDateOfBirth = findViewById(R.id.text_date_of_birth);
        TextView textGender = findViewById(R.id.text_gender);
        TextView textTimeOfBirth = findViewById(R.id.text_time_of_birth);
        TextView textBirthWeight = findViewById(R.id.text_birth_weight);
        TextView textBloodType = findViewById(R.id.text_blood_type);
        TextView textLengthAtBirth = findViewById(R.id.text_length_at_birth);
        TextView textRecordOfVaccinations = findViewById(R.id.text_record_of_vaccinations);
        TextView textHealthConcerns = findViewById(R.id.text_health_concerns);
        TextView textMedicalProcedures = findViewById(R.id.text_medical_procedures);
        TextView textApgaScore = findViewById(R.id.text_apga_score);



        // Assuming receivedValue is the baby_id
        DatabaseReference babyReference = FirebaseDatabase.getInstance().getReference("Main").child(receivedValue).child("MedicalInfo");
        Log.d(TAG, "Received value: " + receivedValue);
        babyReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Populate TextViews with medical information
                textName.setText((String) dataSnapshot.child("name").getValue());
                textDateOfBirth.setText((String) dataSnapshot.child("dateOfBirth").getValue());
                textGender.setText((String) dataSnapshot.child("gender").getValue());
                System.out.println(textGender);
                textTimeOfBirth.setText((String) dataSnapshot.child("timeOfBirth").getValue());
                textBirthWeight.setText((String) dataSnapshot.child("BirthWeight").getValue());
                textBloodType.setText((String) dataSnapshot.child("BloodType").getValue());
                textLengthAtBirth.setText((String) dataSnapshot.child("LengthAtBirth").getValue());
                textRecordOfVaccinations.setText((String) dataSnapshot.child("RecordOfVaccinations").getValue());
                textHealthConcerns.setText((String) dataSnapshot.child("immediateHealthConcerns").getValue());
                textMedicalProcedures.setText((String) dataSnapshot.child("medicalProcedures").getValue());
                textApgaScore.setText((String) dataSnapshot.child("ApgaScore").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Medical_info", "Failed to fetch data", databaseError.toException());
                Toast.makeText(Medical_info.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
