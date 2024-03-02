package com.example.cdrb_01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TableActivity1 extends AppCompatActivity {

    private TableLayout tableLayout;
    private String receivedValue;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);
        textView = findViewById(R.id.received); // Replace with your actual TextView ID

        // Retrieve the value from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            receivedValue = intent.getStringExtra("INPUT_VALUE");

            // Display the received value in a TextView or use it as needed
            textView.setText("Received Value: " + receivedValue);
        }

        tableLayout = findViewById(R.id.table_layout);
        // Assuming "Main" is the root of your database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Main").child(receivedValue).child("Vaccinations");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableLayout.removeAllViews(); // Clear existing rows

                // Add column headers
                addHeaders();

                // Populate the table with data
                for (DataSnapshot babySnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot monthSnapshot : babySnapshot.getChildren()) {
                        String babyId = babySnapshot.getKey();
                        String babyMonths = monthSnapshot.getKey();
                        String babyName = (String) monthSnapshot.child("Baby_name").getValue();
                        String gender = (String) monthSnapshot.child("Gender").getValue();
                        String vaccinationDate = (String) monthSnapshot.child("Vaccination_date").getValue();
                        String vaccineName = (String) monthSnapshot.child("Vaccine_name").getValue();
                        String immediateHealth = (String) monthSnapshot.child("immediateHealth").getValue();

                        // Add a row for each record
                        addRow(babyId, babyMonths, babyName, gender, vaccinationDate, vaccineName, immediateHealth);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TableActivity1", "Failed to fetch data", databaseError.toException());
                Toast.makeText(TableActivity1.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
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
            //textView.setBackgroundResource(R.drawable.cell_shape);
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
            //textView.setBackgroundResource(R.drawable.cell_shape);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}
