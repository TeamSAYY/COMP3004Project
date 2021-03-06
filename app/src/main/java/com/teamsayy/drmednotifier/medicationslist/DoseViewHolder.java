package com.teamsayy.drmednotifier.medicationslist;

import android.app.Application;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamsayy.drmednotifier.R;
import com.teamsayy.drmednotifier.data.MedActivity;
import com.teamsayy.drmednotifier.data.Medication;

import java.util.Calendar;

public class DoseViewHolder extends RecyclerView.ViewHolder {
    private TextView medTime;
    private TextView medName;
    private TextView medDose;
    private CheckBox checkBox;
    private Application application;
    private int year;
    private int month;
    private int day;

    public DoseViewHolder(@NonNull View itemView, Application application, int year, int month, int day) {
        super(itemView);
        medTime = itemView.findViewById(R.id.item_dose_time);
        medName = itemView.findViewById(R.id.item_dose_medication_name);
        medDose = itemView.findViewById(R.id.item_dose_medication_dose);
        checkBox = itemView.findViewById(R.id.item_dose_status);
        this.application = application;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void bind(Medication medication) {
        medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose_1()));
        checkBox.setEnabled(false);
    }

    public void bind(Medication medication, MedActivity medActivity, int numOfAlarm) {
        medTime.setText(String.format("%02d:%02d", medication.getHour(numOfAlarm), medication.getMinute(numOfAlarm)));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose(numOfAlarm)));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long millis = calendar.getTimeInMillis();

        if (millis > System.currentTimeMillis()) {
            checkBox.setEnabled(false);
        } else {
            checkBox.setEnabled(true);
            checkBox.setChecked(medActivity.getMedStatus(numOfAlarm));
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                medication.setQuantity(medication.getQuantity() + (isChecked?-1:1) * medication.getDose(numOfAlarm));
                MedicationsListViewModel medicationsListViewModel = new MedicationsListViewModel(application);
                medicationsListViewModel.update(medication);

                medActivity.setMedStatus(numOfAlarm, isChecked);
                MedActivitiesListViewModel medActivitiesListViewModel = new MedActivitiesListViewModel(application);
                medActivitiesListViewModel.update(medActivity);
            });
        }
    }
}
