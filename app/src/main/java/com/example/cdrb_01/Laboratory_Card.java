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

public class Laboratory_Card extends AppCompatActivity {

    private TableLayout tableLayout;
    private String receivedValue;
    //private TextView textView;

    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratory_card);

        // Initialize the TextView
        //textView = findViewById(R.id.textView); // Replace with your actual TextView ID

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("INPUT_VALUE_Laboratory")) {
            receivedValue = intent.getStringExtra("INPUT_VALUE_Laboratory");
        }

        // Set the text to the TextView
        //textView.setText("Laboratory Card : " + receivedValue);
        tableLayout = findViewById(R.id.table_layout);

        back_button = findViewById(R.id.back_button);

        // Add OnClickListener to the back_button
        back_button.setOnClickListener(view -> {
            // Create an Intent to navigate back to the home page
            Intent LaboratorybackIntent = new Intent(Laboratory_Card.this, Home_Page.class);
            startActivity(LaboratorybackIntent);
            finish(); // Finish
        });

        // Assuming receivedValue is the baby_id
        DatabaseReference babyReference = FirebaseDatabase.getInstance().getReference("Main").child(receivedValue).child("Laboratory");

        babyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableLayout.removeAllViews(); // Clear existing rows

                // Add column headers
                addHeaders();

                // Iterate through children to find vaccination details
                for (DataSnapshot laboratorySnapShot : dataSnapshot.getChildren()) {
                    String babyId = (String) laboratorySnapShot.child("Baby_id").getValue();
                    String babyName = (String) laboratorySnapShot.child("Baby_name").getValue();
                    String contactNumber = (String) laboratorySnapShot.child("Contact_number").getValue();
                    String email = (String) laboratorySnapShot.child("Email").getValue();
                    String parentName = (String) laboratorySnapShot.child("Parent_name").getValue();
                    String specialName = (String) laboratorySnapShot.child("Special_note").getValue();
                    String testDate = (String) laboratorySnapShot.child("Test_date").getValue();
                    String testName=(String)laboratorySnapShot.child("Test_name").getValue();
                    String testResult=(String)laboratorySnapShot.child("Test_result").getValue();

                    // Add a row for each vaccination record
                    addRow(babyId, babyName, contactNumber, email, parentName, specialName, testDate,testName,testResult);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TableActivity1", "Failed to fetch data", databaseError.toException());
                Toast.makeText(Laboratory_Card.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to add column headers
    private void addHeaders() {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Baby ID", "Baby Name", "Contact Number", "Email", "Parent Name", "Special Name", "Test Date","Test Name","Test Result"};

        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(8, 8, 8, 8);
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.bg1)); // Set header text color here
            row.addView(textView);
        }

        tableLayout.addView(row);
    }

    // Helper method to add a row to the table
    private void addRow(String babyId,String babyName,String contactNumber,String email,String parentName,String specialName,String testDate,String testName,String testResult) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        String[] data = {babyId, babyName, contactNumber, email, parentName, specialName, testDate,testName,testResult};

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
