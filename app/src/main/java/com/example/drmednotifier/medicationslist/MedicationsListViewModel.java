package com.example.drmednotifier.medicationslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationRepository;

import java.util.List;

public class MedicationsListViewModel extends AndroidViewModel {
    private MedicationRepository medicationRepository;
    private LiveData<List<Medication>> medicationsLiveData;

    public MedicationsListViewModel(@NonNull Application application) {
        super(application);

        medicationRepository = new MedicationRepository(application);
        medicationsLiveData = medicationRepository.getMedicationsLiveData();
    }

    public void update(Medication medication) {
        medicationRepository.update(medication);
    }

    public LiveData<List<Medication>> getAlarmsLiveData() {
        return medicationsLiveData;
    }
}
