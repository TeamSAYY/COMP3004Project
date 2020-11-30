package com.example.drmednotifier.medicationslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.MedActivityRepository;

import java.util.List;

public class MedActivitiesListViewModel extends AndroidViewModel {
    private MedActivityRepository medActivityRepository;
    private LiveData<List<MedActivity>> medActivitiesLiveData;
    private LiveData<List<MedActivity>> medActivitiesLiveDataLastWeek;

    public MedActivitiesListViewModel(@NonNull Application application) {
        super(application);

        medActivityRepository = new MedActivityRepository(application);
        medActivitiesLiveData = medActivityRepository.getMedActivitiesLiveData();
        medActivitiesLiveDataLastWeek = medActivityRepository.getMedActivitiesLiveDataLastWeek();
    }

    public void deleteByMedId(int medId) {
        medActivityRepository.deleteByMedId(medId);
    }

    public void update(MedActivity medActivity) {
        medActivityRepository.update(medActivity);
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveData() {
        return medActivitiesLiveData;
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveDataLastWeek() {
        return medActivitiesLiveDataLastWeek;
    }
}
