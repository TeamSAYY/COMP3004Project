package com.example.drmednotifier.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MedActivityRepository {
    private MedActivityDao medActivityDao;
    private LiveData<List<MedActivity>> medActivitiesLiveData;
    private LiveData<List<MedActivity>> medActivitiesLiveDataLastWeek;

    public MedActivityRepository(Application application) {
        MedActivityDatabase db = MedActivityDatabase.getDatabase(application);
        medActivityDao = db.medActivityDao();
        medActivitiesLiveData = medActivityDao.getMedActivity();

        final int DAY = 24 * 60 * 60 * 1000;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        long dayet = calendar.getTimeInMillis();

        calendar.setTimeInMillis(System.currentTimeMillis() - 6*DAY);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long dayst = calendar.getTimeInMillis();

        medActivitiesLiveDataLastWeek = medActivityDao.getMedActivityLastWeek(dayst, dayet);
    }

    public void deleteByMedId(int medId) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> medActivityDao.deleteByMedId(medId));
    }

    public void insert(final MedActivity medActivity) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> medActivityDao.insert(medActivity));
    }

    public void insertAll(final List<MedActivity> medActivities) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> medActivityDao.insertAll(medActivities));
    }

    public void update(final MedActivity medActivity) {
        MedActivityDatabase.databaseWriteExecutor.execute(() -> medActivityDao.update(medActivity));
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveData() {
        return medActivitiesLiveData;
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveDataLastWeek() {
        return medActivitiesLiveDataLastWeek;
    }

    public MedActivity loadSingle(int id) {
        return medActivityDao.loadSingle(id);
    }

    public MedActivity loadByMedDate(int medId, Date date) {
        return medActivityDao.loadByMedDate(medId, date);
    }
}
