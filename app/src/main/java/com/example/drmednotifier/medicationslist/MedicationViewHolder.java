package com.example.drmednotifier.medicationslist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.Medication;

public class MedicationViewHolder extends RecyclerView.ViewHolder {
    private TextView medTime;
    private TextView medName;
    private TextView medDose;

    public MedicationViewHolder(@NonNull View itemView) {
        super(itemView);

        medTime = itemView.findViewById(R.id.item_medication_time);
        medName = itemView.findViewById(R.id.item_medication_name);
        medDose = itemView.findViewById(R.id.item_medication_dose);
    }

    public void bind(Medication medication) {
        String alarmText = String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1());

        medTime.setText(alarmText);

        if (medication.getName().length() != 0) {
            medName.setText(String.format("%s", medication.getName()));
        } else {
            medName.setText(String.format("%s", "Medication"));
        }

        medDose.setText(String.format("%d pill(s)", medication.getDose_1()));
    }
}
