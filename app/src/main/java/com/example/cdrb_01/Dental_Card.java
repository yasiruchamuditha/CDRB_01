package com.example.cdrb_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dental_Card extends AppCompatActivity {
    private TableLayout tableLayout;
    private String receivedValue;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_card);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INPUT_VALUE_Clinic")) {
            receivedValue = intent.getStringExtra("INPUT_VALUE_Clinic");
        }

        tableLayout = findViewById(R.id.table_layout);
        back_button = findViewById(R.id.back_button);

        // Add OnClickListener to the back_button
        back_button.setOnClickListener(view -> {
            // Create an Intent to navigate back to the home page
            Intent DentalbackIntent = new Intent(Dental_Card.this, Home_Page.class);
            startActivity(DentalbackIntent);
            finish(); // Finish
        });

        DatabaseReference clinicReference = FirebaseDatabase.getInstance().getReference().child("Dental");

        clinicReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableLayout.removeAllViews(); // Clear existing rows

                // Add column headers
                addHeaders();

                // Retrieve clinic details
                String surgeryDate = dataSnapshot.child("Dental_Surgery_date").getValue(String.class);
                String surgeryTime = dataSnapshot.child("Dental_Surgery_time").getValue(String.class);
                String doctorName = dataSnapshot.child("Doctor_name").getValue(String.class);
                String specialNote = dataSnapshot.child("Special_note").getValue(String.class);

                // Add a row for clinic details
                addRow(surgeryDate, surgeryTime, doctorName, specialNote);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Clinic_Card", "Failed to fetch clinic data", databaseError.toException());
                Toast.makeText(Dental_Card.this, "Failed to fetch clinic data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to add column headers
    private void addHeaders() {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Date", "Time", "Doctor Name", "Special Note"};

        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(8, 8, 8, 8);
            textView.setTextSize(16);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }

    // Helper method to add a row to the table
    private void addRow(String date, String time, String doctorName, String specialNote) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] data = {date, time, doctorName, specialNote};

        for (String datum : data) {
            TextView textView = new TextView(this);
            textView.setText(datum);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(8, 8, 8, 8);
            textView.setTextSize(14);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}
