package com.example.drmednotifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.medicationslist.MedicationRecyclerViewAdapter;
import com.example.drmednotifier.medicationslist.MedicationsListViewModel;

import java.util.List;


public class Medicine_List extends AppCompatActivity {

    private Toolbar toolbar;
    private MedicationRecyclerViewAdapter medicationRecyclerViewAdapter;
    private MedicationsListViewModel medicationsListViewModel;
    private RecyclerView medicationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__list);






                medicationRecyclerViewAdapter = new MedicationRecyclerViewAdapter();
        medicationsListViewModel = ViewModelProviders.of(this).get(MedicationsListViewModel.class);
        medicationsListViewModel.getAlarmsLiveData().observe(this, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                if (medications != null) {
                    medicationRecyclerViewAdapter.setMedications(medications);
                }
            }
        });



        medicationsRecyclerView = findViewById(R.id.home_listmed_recylerView);
        medicationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicationsRecyclerView.setAdapter(medicationRecyclerViewAdapter);

        toolbar =  findViewById(R.id.toolbar_Medicine_list);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }




}