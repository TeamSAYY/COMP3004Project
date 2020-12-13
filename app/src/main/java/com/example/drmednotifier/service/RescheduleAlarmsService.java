package com.example.drmednotifier.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationRepository;

public class RescheduleAlarmsService extends LifecycleService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        MedicationRepository medicationRepository = new MedicationRepository(getApplication());
        medicationRepository.getMedicationsLiveData().observe(this, medications -> {
            if (medications == null) return;
            for (Medication m : medications) {
                m.schedule(getApplicationContext());
            }
            stopSelf();
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return null;
    }
}
