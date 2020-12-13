package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.medicationslist.DoseRecyclerViewAdapter;
import com.example.drmednotifier.medicationslist.MedActivitiesListViewModel;
import com.example.drmednotifier.medicationslist.MedicationsListViewModel;

import java.util.Calendar;

public class Dose_Page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose__page);

        TextView theDate = findViewById(R.id.dose_date);
        Toolbar theToolbar = findViewById(R.id.toolbar_Dose);
        RecyclerView doseRecyclerView = findViewById(R.id.dose_recyclerView);

        doseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent incomingIntent = getIntent();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int year = incomingIntent.getIntExtra("YEAR", calendar.get(Calendar.YEAR));
        int month = incomingIntent.getIntExtra("MONTH", calendar.get(Calendar.MONTH));
        int day = incomingIntent.getIntExtra("DAY", calendar.get(Calendar.DAY_OF_MONTH));

        String date = (month + 1) + "/" + day + "/" + year;
        theDate.setText(date);

        theToolbar.setNavigationOnClickListener(v -> onBackPressed());

        DoseRecyclerViewAdapter adapter = new DoseRecyclerViewAdapter();
        adapter.setDate(year, month, day);
        MedicationsListViewModel medicationsListViewModel = ViewModelProviders.of(this).get(MedicationsListViewModel.class);
        medicationsListViewModel.getMedicationsLiveData().observe(this, medications -> {
            if (medications != null) {
                adapter.setDoses(medications);
            }
        });
        MedActivitiesListViewModel medActivitiesListViewModel = ViewModelProviders.of(this).get(MedActivitiesListViewModel.class);
        medActivitiesListViewModel.getMedActivitiesLiveData().observe(this, medActivities -> {
            if (medActivities != null) {
                adapter.setMedActivities(getApplication(), medActivities);
            }
        });
        doseRecyclerView.setAdapter(adapter);
    }
}