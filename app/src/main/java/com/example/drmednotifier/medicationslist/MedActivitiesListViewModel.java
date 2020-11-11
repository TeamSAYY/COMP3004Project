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

    public MedActivitiesListViewModel(@NonNull Application application) {
        super(application);

        medActivityRepository = new MedActivityRepository(application);
        medActivitiesLiveData = medActivityRepository.getMedActivitiesLiveData();
    }

    public void update(MedActivity medActivity) {
        medActivityRepository.update(medActivity);
    }

    public LiveData<List<MedActivity>> getMedActivitiesLiveData() {
        return medActivitiesLiveData;
    }
}
