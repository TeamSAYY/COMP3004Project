package com.example.drmednotifier.medicationslist;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.Medication;

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
        Log.d("myTag", "BIND DOSE ITEM VIEW 1");

        medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose_1()));
        checkBox.setEnabled(false);
    }

    public void bind(Medication medication, MedActivity medActivity, int numOfAlarm) {
        Log.d("myTag", "BIND DOSE ITEM VIEW 2");

        medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose_1()));

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
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("myTag", "CHECKBOX CLICKED");
                    medActivity.setMedStatus(numOfAlarm, isChecked);
                    MedActivitiesListViewModel medActivitiesListViewModel = new MedActivitiesListViewModel(application);
                    medActivitiesListViewModel.update(medActivity);
                }
            });
        }
    }
}
