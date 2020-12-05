package com.example.drmednotifier.service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationRepository;

import java.util.List;

public class DescheduleAlarmsService extends LifecycleService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        MedicationRepository medicationRepository = new MedicationRepository(getApplication());

        medicationRepository.getMedicationsLiveData().observe(this, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                if (medications == null) return;
                for (Medication m : medications) {
                    m.deschedule(getApplicationContext());
                }
                Log.d("myTag", "Deschedule Alarms Service");
//                stopForeground(true);
                stopSelf();
            }
        });

//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("Notification Disabled")
//                .setContentText("")
//                .setSmallIcon(R.drawable.logo)
//                .build();

//        startForeground(2, notification);

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
