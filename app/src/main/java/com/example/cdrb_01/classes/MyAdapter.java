package com.example.cdrb_01.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cdrb_01.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<VaccinationRecord> dataList;

    public MyAdapter(ArrayList<VaccinationRecord> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VaccinationRecord record = dataList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView babyNameTextView;
        TextView vaccinationDateTextView;

        TextView immediateHealth;
        TextView babyGender;
        TextView babyId;
        TextView babyMonth;

        TextView vaccinationName;
        // Add other views as needed

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            babyNameTextView = itemView.findViewById(R.id.baby_name_text_view);
            vaccinationDateTextView = itemView.findViewById(R.id.vaccination_date_text_view);
            immediateHealth=itemView.findViewById(R.id.immediateHealth_text_view);
            babyGender=itemView.findViewById(R.id.babyGender_text_view);
            babyId=itemView.findViewById(R.id.babyId_text_view);
            babyMonth=itemView.findViewById(R.id.babyMonths_text_view);
            vaccinationName=itemView.findViewById(R.id.vaccination_name_text_view);
            // Initialize other views
        }

        public void bind(VaccinationRecord record) {
            babyNameTextView.setText(record.getBabyName());
            vaccinationDateTextView.setText(record.getVaccinationDate());
            immediateHealth.setText(record.getImmediateHealth());
            babyGender.setText(record.getGender());
            babyId.setText(record.getBabyId());
            babyMonth.setText(record.getBabyMonths());
            vaccinationName.setText(record.getVaccineName());

            // Bind other data as needed
        }

    }
}
