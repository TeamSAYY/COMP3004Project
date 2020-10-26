package com.example.drmednotifier.medicationslist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.Medication;

public class MedicationViewHolder extends RecyclerView.ViewHolder {
//    private TextView medTime;
    private TextView medName;
    private TextView medDays;
    private TextView medTimes;
//    private TextView medDose;

    public MedicationViewHolder(@NonNull View itemView) {
        super(itemView);

//        medTime = itemView.findViewById(R.id.item_medication_time);
        medName = itemView.findViewById(R.id.item_medication_name);
        medDays = itemView.findViewById(R.id.item_medication_recurringDays);
        medTimes = itemView.findViewById(R.id.item_medication_times);
//        medDose = itemView.findViewById(R.id.item_medication_dose);
    }

    public void bind(Medication medication) {
//        String alarmText = String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1());
//        medTime.setText(alarmText);

        if (medication.getName().length() != 0) {
            medName.setText(String.format("%s", medication.getName()));
        } else {
            medName.setText(String.format("%s", "Default"));
        }

        if (medication.isMonday() && medication.isTuesday() && medication.isWednesday() && medication.isThursday() && medication.isFriday() && medication.isSaturday() && medication.isSunday()) {
            medDays.setText("Daily");
        } else {
            String days = "";
            if (medication.isMonday()) days += "Mon ";
            if (medication.isTuesday()) days += "Tue ";
            if (medication.isWednesday()) days += "Wed ";
            if (medication.isThursday()) days += "Thu ";
            if (medication.isFriday()) days += "Fri ";
            if (medication.isSaturday()) days += "Sat ";
            if (medication.isSunday()) days += "Sun ";
            medDays.setText(days);
        }

        String times = "";
        if (medication.getTimes() >= 1) times += String.format("%02d:%02d ", medication.getHour_1(), medication.getMinute_1());
        if (medication.getTimes() >= 2) times += String.format("%02d:%02d ", medication.getHour_2(), medication.getMinute_2());
        if (medication.getTimes() >= 3) times += String.format("%02d:%02d ", medication.getHour_3(), medication.getMinute_3());
        if (medication.getTimes() >= 4) times += String.format("%02d:%02d ", medication.getHour_4(), medication.getMinute_4());
        medTimes.setText(times);

//        medDose.setText(String.format("%d pill(s)", medication.getDose_1()));
    }
}
