package com.example.drmednotifier.medicationslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.Medication;

import java.util.ArrayList;
import java.util.List;

public class DoseRecyclerViewAdapter extends RecyclerView.Adapter<DoseRecyclerViewAdapter.ViewHolder> {
    private List<Medication> doses;

    public DoseRecyclerViewAdapter() {
        doses = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dose, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medication medication = doses.get(position);
        holder.bind(medication);
    }

    @Override
    public int getItemCount() {
        return doses.size();
    }

    public void setAlarms(List<Medication> medications, int dayOfWeek) {
        doses = new ArrayList<>();
        if (medications == null) return;

        for (Medication medication : medications) {
            if (dayOfWeek == 1 && !medication.isSunday()) continue;
            if (dayOfWeek == 2 && !medication.isMonday()) continue;
            if (dayOfWeek == 3 && !medication.isTuesday()) continue;
            if (dayOfWeek == 4 && !medication.isWednesday()) continue;
            if (dayOfWeek == 5 && !medication.isThursday()) continue;
            if (dayOfWeek == 6 && !medication.isFriday()) continue;
            if (dayOfWeek == 7 && !medication.isSaturday()) continue;

            int times = medication.getTimes();
            if (times >= 1) {
                doses.add(new Medication(
                        medication.getMedId(),
                        medication.getName(),
                        medication.getDescription(),
                        medication.getQuantity(),
                        medication.getShape_id(),
                        medication.getCreated(),
                        medication.isMonday(),
                        medication.isTuesday(),
                        medication.isWednesday(),
                        medication.isThursday(),
                        medication.isFriday(),
                        medication.isSaturday(),
                        medication.isSunday(),
                        medication.getHour_1(),
                        medication.getMinute_1(),
                        medication.getDose_1()
                ));
            }
            if (times >= 2) {
                doses.add(new Medication(
                        medication.getMedId(),
                        medication.getName(),
                        medication.getDescription(),
                        medication.getQuantity(),
                        medication.getShape_id(),
                        medication.getCreated(),
                        medication.isMonday(),
                        medication.isTuesday(),
                        medication.isWednesday(),
                        medication.isThursday(),
                        medication.isFriday(),
                        medication.isSaturday(),
                        medication.isSunday(),
                        medication.getHour_2(),
                        medication.getMinute_2(),
                        medication.getDose_2()
                ));
            }
            if (times >= 3) {
                doses.add(new Medication(
                        medication.getMedId(),
                        medication.getName(),
                        medication.getDescription(),
                        medication.getQuantity(),
                        medication.getShape_id(),
                        medication.getCreated(),
                        medication.isMonday(),
                        medication.isTuesday(),
                        medication.isWednesday(),
                        medication.isThursday(),
                        medication.isFriday(),
                        medication.isSaturday(),
                        medication.isSunday(),
                        medication.getHour_3(),
                        medication.getMinute_3(),
                        medication.getDose_3()
                ));
            }
            if (times >= 4) {
                doses.add(new Medication(
                        medication.getMedId(),
                        medication.getName(),
                        medication.getDescription(),
                        medication.getQuantity(),
                        medication.getShape_id(),
                        medication.getCreated(),
                        medication.isMonday(),
                        medication.isTuesday(),
                        medication.isWednesday(),
                        medication.isThursday(),
                        medication.isFriday(),
                        medication.isSaturday(),
                        medication.isSunday(),
                        medication.getHour_4(),
                        medication.getMinute_4(),
                        medication.getDose_4()
                ));
            }
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView medTime;
        private TextView medName;
        private TextView medDose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medTime = itemView.findViewById(R.id.item_dose_time);
            medName = itemView.findViewById(R.id.item_dose_medication_name);
            medDose = itemView.findViewById(R.id.item_dose_medication_dose);
        }
        public void bind(Medication medication) {
            medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
            medName.setText(medication.getName());
            medDose.setText(String.format("%d", medication.getDose_1()));
        }
    }
}
