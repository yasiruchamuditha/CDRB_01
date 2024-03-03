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

public class Clinic_Card extends AppCompatActivity {

    private TableLayout tableLayout;
    private String receivedValue;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_card);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INPUT_VALUE_Clinic")) {
            receivedValue = intent.getStringExtra("INPUT_VALUE_Clinic");
        }

        tableLayout = findViewById(R.id.table_layout);
        back_button = findViewById(R.id.back_button);

        // Add OnClickListener to the back_button
        back_button.setOnClickListener(view -> {
            // Create an Intent to navigate back to the home page
            Intent ClinicBackIntent = new Intent(Clinic_Card.this, Home_Page.class);
            startActivity(ClinicBackIntent);
            finish(); // Finish
        });


        DatabaseReference babyReference = FirebaseDatabase.getInstance().getReference("Main").child(receivedValue).child("EyeAndEar");

        babyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableLayout.removeAllViews(); // Clear existing rows

                // Add column headers
                addHeaders();

                // Iterate through children to find vaccination details
                for (DataSnapshot earAndEyeSnapShot : dataSnapshot.getChildren()) {
                    String babyId = (String) earAndEyeSnapShot.child("Bid").getValue();
                    String etName = (String) earAndEyeSnapShot.child("ETName").getValue();
                    String tdate = (String) earAndEyeSnapShot.child("Tdate").getValue();
                    String months = (String)earAndEyeSnapShot.child("months").getValue();
                    String note=(String)earAndEyeSnapShot.child("specialN").getValue();

                    // Add a row for each vaccination record
                    addRow(babyId, etName,tdate,months,note);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TableActivity1", "Failed to fetch data", databaseError.toException());
                Toast.makeText(Clinic_Card.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to add column headers
    private void addHeaders() {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Baby ID", "Clinic Name","Date","Months","Note"};

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
    private void addRow(String babyId,String etName,String tdate,String months,String note) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] data = {babyId,etName, tdate,months,note};

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