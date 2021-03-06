package com.teamsayy.drmednotifier;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamsayy.drmednotifier.medicationslist.MedActivitiesListViewModel;
import com.teamsayy.drmednotifier.medicationslist.MedicationRecyclerViewAdapter;
import com.teamsayy.drmednotifier.medicationslist.MedicationsListViewModel;

public class Medicine_List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__list);

        Toolbar toolbar = findViewById(R.id.toolbar_Medicine_list);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        MedicationsListViewModel medicationsListViewModel = ViewModelProviders.of(this).get(MedicationsListViewModel.class);
        MedActivitiesListViewModel medActivitiesListViewModel = ViewModelProviders.of(this).get(MedActivitiesListViewModel.class);

        MedicationRecyclerViewAdapter medicationRecyclerViewAdapter = new MedicationRecyclerViewAdapter(this, medicationsListViewModel, medActivitiesListViewModel);

        medicationsListViewModel.getMedicationsLiveData().observe(this, medications -> {
            if (medications != null) {
                medicationRecyclerViewAdapter.setMedications(medications);
            }
        });

        RecyclerView medicationsRecyclerView = findViewById(R.id.med_list_recylerView);
        medicationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        medicationsRecyclerView.setAdapter(medicationRecyclerViewAdapter);

/*
        // TODO: medication list item swipe feature
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition(); //get position which is swipe

                if (swipeDir == ItemTouchHelper.LEFT) {    //if swipe left

                    AlertDialog.Builder builder = new AlertDialog.Builder(Medicine_List.this); //alert for confirm to delete
                    builder.setMessage("Are you sure to delete?");    //set message

                    builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            medicationRecyclerViewAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                            Medication medication = medicationRecyclerViewAdapter.getMedByPos(position);
                            medication.deschedule(getApplicationContext());
                            medicationsListViewModel.deleteById(medication.getMedId());
                            return;
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            medicationRecyclerViewAdapter.notifyDataSetChanged();
                            return;
                        }
                    }).show();  //show alert dialog
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(medicationsRecyclerView);
 */
    }
}