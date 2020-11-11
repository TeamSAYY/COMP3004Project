package com.example.drmednotifier.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class MedActivityRepository {
    private MedActivityDao medActivityDao;
    private LiveData<List<MedActivity>> medActivitiesLiveData;

    public MedActivityRepository(Application application) {
        MedActivityDatabase db = MedActivityDatabase.getDatabase(application);
        medActivityDao = db.medActivityDao();
        medActivitiesLiveData = medActivityDao.getMedActivity();
    }

    public void insert(final MedActivity medActivity) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> {
            medActivityDao.insert(medActivity);
        });
    }

    public void insertAll(final List<MedActivity> medActivities) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> {
            medActivityDao.insertAll(medActivities);
        });
    }

    public void update(final MedActivity medActivity) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> {
            medActivityDao.update(medActivity);
        });
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveData() {
        return medActivitiesLiveData;
    }

    public MedActivity loadSingle(int id) {
        return medActivityDao.loadSingle(id);
    }

    public MedActivity loadByMedDate(int medId, Date date) {
        return medActivityDao.loadByMedDate(medId, date);
    }
}
