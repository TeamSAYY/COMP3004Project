package com.teamsayy.drmednotifier.createmedication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.teamsayy.drmednotifier.data.MedActivity;
import com.teamsayy.drmednotifier.data.MedActivityRepository;
import com.teamsayy.drmednotifier.data.Medication;
import com.teamsayy.drmednotifier.data.MedicationRepository;

import java.util.List;

public class CreateMedicationViewModel extends AndroidViewModel {
    private final MedicationRepository medicationRepository;
    private final MedActivityRepository medActivityRepository;

    public CreateMedicationViewModel(@NonNull Application application) {
        super(application);

        medicationRepository = new MedicationRepository(application);
        medActivityRepository = new MedActivityRepository(application);
    }

    public void insert(Medication medication) {
        medicationRepository.insert(medication);
    }

    public void update(Medication medication) {
        medicationRepository.update(medication);
    }

    public void insert(MedActivity medActivity) {
        medActivityRepository.insert(medActivity);
    }

    public void insertAll(List<MedActivity> medActivities) {
        medActivityRepository.insertAll(medActivities);
    }

    public void update(MedActivity medActivity) {
        medActivityRepository.update(medActivity);
    }
}
