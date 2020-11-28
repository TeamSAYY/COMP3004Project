package com.example.drmednotifier.medicationslist;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.Medication;

import java.util.Calendar;

public class SelfReportViewHolder extends RecyclerView.ViewHolder {
    private TextView medTime;
    private TextView medName;
    private TextView medDose;
    private CheckBox checkBox;
    private Application application;
    private int year;
    private int month;
    private int day;

    public SelfReportViewHolder(@NonNull View itemView, Application application, long timeMillis) {
        super(itemView);
        medTime = itemView.findViewById(R.id.item_dose_time);
        medName = itemView.findViewById(R.id.item_dose_medication_name);
        medDose = itemView.findViewById(R.id.item_dose_medication_dose);
        checkBox = itemView.findViewById(R.id.item_dose_status);
        this.application = application;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void bind(Medication medication) {
        Log.d("myTag", "BIND DOSE ITEM VIEW 1");

        medTime.setText(String.format("%02d:%02d", medication.getHour_1(), medication.getMinute_1()));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose_1()));

        medDose.setVisibility(View.GONE);
        checkBox.setEnabled(false);
    }

    public void bind(Medication medication, MedActivity medActivity, int numOfAlarm, boolean taken) {
        Log.d("myTag", "BIND DOSE ITEM VIEW 2");

        medTime.setText(String.format("%02d:%02d", medication.getHour(numOfAlarm), medication.getMinute(numOfAlarm)));
        medName.setText(medication.getName());
        medDose.setText(String.format("%d", medication.getDose(numOfAlarm)));

        medDose.setVisibility(View.GONE);
        checkBox.setEnabled(false);
        checkBox.setChecked(medActivity.getMedStatus(numOfAlarm));

//        if (medActivity.getMedStatus(numOfAlarm) && !taken || !medActivity.getMedStatus(numOfAlarm) && taken) this.itemView.setVisibility(View.GONE);
    }
}
