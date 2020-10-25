package com.example.drmednotifier.createmedication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationRepository;

public class CreateMedicationViewModel extends AndroidViewModel {
    private MedicationRepository medicationRepository;

    public CreateMedicationViewModel(@NonNull Application application) {
        super(application);

        medicationRepository = new MedicationRepository(application);
    }

    public void insert(Medication medication) {
        medicationRepository.insert(medication);
    }
}
