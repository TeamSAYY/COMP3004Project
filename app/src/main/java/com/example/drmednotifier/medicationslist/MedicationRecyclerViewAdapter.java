package com.example.drmednotifier.medicationslist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.Edit_Medication;
import com.example.drmednotifier.R;
import com.example.drmednotifier.data.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationRecyclerViewAdapter extends RecyclerView.Adapter<MedicationViewHolder> {
    private List<Medication> medications;
    Context context;
    MedicationsListViewModel medicationsListViewModel;
    MedActivitiesListViewModel medActivitiesListViewModel;

    public MedicationRecyclerViewAdapter(Context context, MedicationsListViewModel medicationsListViewModel, MedActivitiesListViewModel medActivitiesListViewModel) {
        this.medications = new ArrayList<Medication>();
        this.context = context;
        this.medicationsListViewModel = medicationsListViewModel;
        this.medActivitiesListViewModel = medActivitiesListViewModel;
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication, parent, false);
        return new MedicationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication medication = medications.get(position);
        holder.bind(medication);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editMedicationIntent = new Intent(holder.itemView.getContext(), Edit_Medication.class);
                editMedicationIntent.putExtra("MED_ID", medication.getMedId());
                editMedicationIntent.putExtra("MED_NAME", medication.getName());
                editMedicationIntent.putExtra("DESCRIPTION", medication.getDescription());
                editMedicationIntent.putExtra("QUANTITY", medication.getQuantity());
                editMedicationIntent.putExtra("SHAPE_ID", medication.getShape_id());
                editMedicationIntent.putExtra("CREATED", medication.getCreated());
                editMedicationIntent.putExtra("MONDAY", medication.isMonday());
                editMedicationIntent.putExtra("TUESDAY", medication.isTuesday());
                editMedicationIntent.putExtra("WEDNESDAY", medication.isWednesday());
                editMedicationIntent.putExtra("THURSDAY", medication.isThursday());
                editMedicationIntent.putExtra("FRIDAY", medication.isFriday());
                editMedicationIntent.putExtra("SATURDAY", medication.isSaturday());
                editMedicationIntent.putExtra("SUNDAY", medication.isSunday());
                editMedicationIntent.putExtra("TIMES", medication.getTimes());
                editMedicationIntent.putExtra("HOUR_1", medication.getHour_1());
                editMedicationIntent.putExtra("MINUTE_1", medication.getMinute_1());
                editMedicationIntent.putExtra("DOSE_1", medication.getDose_1());
                editMedicationIntent.putExtra("HOUR_2", medication.getHour_2());
                editMedicationIntent.putExtra("MINUTE_2", medication.getMinute_2());
                editMedicationIntent.putExtra("DOSE_2", medication.getDose_2());
                editMedicationIntent.putExtra("HOUR_3", medication.getHour_3());
                editMedicationIntent.putExtra("MINUTE_3", medication.getMinute_3());
                editMedicationIntent.putExtra("DOSE_3", medication.getDose_3());
                editMedicationIntent.putExtra("HOUR_4", medication.getHour_4());
                editMedicationIntent.putExtra("MINUTE_4", medication.getMinute_4());
                editMedicationIntent.putExtra("DOSE_4", medication.getDose_4());
                holder.itemView.getContext().startActivity(editMedicationIntent);
            }
        });

        Button deleteBtn = holder.itemView.findViewById(R.id.item_medication_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition(); //get position

                AlertDialog.Builder builder = new AlertDialog.Builder(context); //alert for confirm to delete
                builder.setMessage("Are you sure to delete?");    //set message

                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyItemRemoved(position);    //item removed from recylcerview
                        Medication medication = getMedByPos(position);
                        medication.deschedule(context);
                        medicationsListViewModel.deleteById(medication.getMedId());
                        medActivitiesListViewModel.deleteByMedId(medication.getMedId());
                        return;
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyDataSetChanged();
                        return;
                    }
                }).show();  //show alert dialog

            }
        });
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
        notifyDataSetChanged();
    }

    public Medication getMedByPos(int position) {
        return medications.get(position);
    }

    @Override
    public void onViewRecycled(@NonNull MedicationViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
