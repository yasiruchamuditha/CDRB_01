package com.example.cdrb_01;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdrb_01.classes.MyAdapter;
import com.example.cdrb_01.classes.VaccinationRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TableActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<VaccinationRecord> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vaccinations");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    VaccinationRecord record = snapshot.getValue(VaccinationRecord.class);
                    dataList.add(record);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TableActivity1.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
