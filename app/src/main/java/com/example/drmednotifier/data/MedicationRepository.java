package com.example.drmednotifier.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicationRepository {
    private MedicationDao medicationDao;
    private LiveData<List<Medication>> medicationsLiveData;

    public MedicationRepository(Application application) {
        MedicationDatabase db = MedicationDatabase.getDatabase(application);
        medicationDao = db.medicationDao();
        medicationsLiveData = medicationDao.getMedications();
    }

    public void insert(final Medication medication) {
        MedicationDatabase.databaseWriteExecutor.execute(() -> {
            medicationDao.insert(medication);
        });
    }

    public void update(final Medication medication) {
        MedicationDatabase.databaseWriteExecutor.execute(() -> {
            medicationDao.update(medication);
        });
    }

    public LiveData<List<Medication>> getMedicationsLiveData() {
        return medicationsLiveData;
    }

    public Medication loadSingle(int id) {
        return medicationDao.loadSingle(id);
    }

    public void deleteById(int id) {
        MedicationDatabase.databaseWriteExecutor.execute(() -> {
            medicationDao.deleteById(id);
        });
    }
}
