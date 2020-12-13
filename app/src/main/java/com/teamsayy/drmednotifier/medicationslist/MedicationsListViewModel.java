package com.teamsayy.drmednotifier.medicationslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.teamsayy.drmednotifier.data.Medication;
import com.teamsayy.drmednotifier.data.MedicationRepository;

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

    public LiveData<List<Medication>> getMedicationsLiveData() {
        return medicationsLiveData;
    }

    public void deleteById(int id) {
        medicationRepository.deleteById(id);
    }
}
