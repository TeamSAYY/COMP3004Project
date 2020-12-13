package com.example.drmednotifier.medicationslist;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.Medication;

public class SelfReportViewHolder extends RecyclerView.ViewHolder {
    private TextView medTime;
    private TextView medName;
    private TextView medDose;
    private CheckBox checkBox;

    public SelfReportViewHolder(@NonNull View itemView) {
        super(itemView);
        medTime = itemView.findViewById(R.id.item_dose_time);
        medName = itemView.findViewById(R.id.item_dose_medication_name);
        medDose = itemView.findViewById(R.id.item_dose_medication_dose);
        checkBox = itemView.findViewById(R.id.item_dose_status);
    }

    public void bind(Medication medication) {
        medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose_1()));

        medDose.setVisibility(View.GONE);
        checkBox.setEnabled(false);
    }

    public void bind(Medication medication, MedActivity medActivity, int numOfAlarm, boolean taken) {
        medTime.setText(String.format("%02d:%02d", medication.getHour(numOfAlarm), medication.getMinute(numOfAlarm)));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose(numOfAlarm)));

        medDose.setVisibility(View.GONE);
        checkBox.setEnabled(false);
        checkBox.setChecked(medActivity.getMedStatus(numOfAlarm));
    }
}
