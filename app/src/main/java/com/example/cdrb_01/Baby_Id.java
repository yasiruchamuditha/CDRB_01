package com.example.cdrb_01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Baby_Id extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_id);

        editText = findViewById(R.id.editText); // Replace with your actual EditText ID
        button = findViewById(R.id.button); // Replace with your actual Button ID

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText
                String inputValue = editText.getText().toString();

                // Create an Intent to start the second activity
                Intent babyIntent = new Intent(Baby_Id.this, Home_Page.class);

                // Pass the value to the second activity using Intent extra
                babyIntent.putExtra("INPUT_VALUE", inputValue);

                // Start the second activity
                startActivity(babyIntent);
            }
        });
    }
}
