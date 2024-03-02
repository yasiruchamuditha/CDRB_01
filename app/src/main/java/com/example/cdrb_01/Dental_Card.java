package com.example.cdrb_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_card);

        // Initialize the TextView
        //textView = findViewById(R.id.textView); // Replace with your actual TextView ID

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INPUT_VALUE_Dental")) {
            receivedValue = intent.getStringExtra("INPUT_VALUE_Dental");
        }

        // Set the text to the TextView
        //textView.setText("Dental Card : " + receivedValue);
        // Set the text to the TextView
        //textView.setText("Clinic Card : " + receivedValue);
        tableLayout = findViewById(R.id.table_layout);
        // Assuming receivedValue is the baby_id
        DatabaseReference babyReference = FirebaseDatabase.getInstance().getReference("Main").child(receivedValue).child("Vaccinations");

        babyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableLayout.removeAllViews(); // Clear existing rows

                // Add column headers
                addHeaders();

                // Iterate through children to find vaccination details
                for (DataSnapshot vaccinationSnapshot : dataSnapshot.getChildren()) {
                    String babyId = (String) vaccinationSnapshot.child("Baby_id").getValue();
                    String babyMonths = (String) vaccinationSnapshot.child("Baby_months").getValue();
                    String babyName = (String) vaccinationSnapshot.child("Baby_name").getValue();
                    String gender = (String) vaccinationSnapshot.child("Gender").getValue();
                    String vaccinationDate = (String) vaccinationSnapshot.child("Vaccination_date").getValue();
                    String vaccineName = (String) vaccinationSnapshot.child("Vaccine_name").getValue();
                    String immediateHealth = (String) vaccinationSnapshot.child("immediateHealth").getValue();

                    // Add a row for each vaccination record
                    addRow(babyId, babyMonths, babyName, gender, vaccinationDate, vaccineName, immediateHealth);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TableActivity1", "Failed to fetch data", databaseError.toException());
                Toast.makeText(Dental_Card.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to add column headers
    private void addHeaders() {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Baby ID", "Baby Months", "Baby Name", "Gender", "Vaccination Date", "Vaccine Name", "Immediate Health"};

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
    private void addRow(String babyId, String babyMonths, String babyName, String gender, String vaccinationDate, String vaccineName, String immediateHealth) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] data = {babyId, babyMonths, babyName, gender, vaccinationDate, vaccineName, immediateHealth};

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
