package com.example.drmednotifier.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationRepository;
import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.drmednotifier.application.App.CHANNEL_ID;

public class RefillReminderService extends LifecycleService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        NotifSettingDatabase notifSettingDatabase = NotifSettingDatabase.getDatabase(getApplicationContext());
        NotifSettingDao notifSettingDao = notifSettingDatabase.notifSettingDao();
        NotifSetting notifSetting = notifSettingDao.getNotifSettings().get(0);

        int days = notifSetting.getDaysBeforeRefill();
        String message = notifSetting.getRefillNotifMessage();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("")
                .setSmallIcon(R.drawable.logo);

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> newNameList = new ArrayList<>();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        MedicationRepository medicationRepository = new MedicationRepository(getApplication());

        medicationRepository.getMedicationsLiveData().observe(this, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                newNameList.clear();
                if (medications == null) return;
                for (Medication m : medications) {
                    int amount = m.getQuantity();
                    int dosage = m.getDose_1() + (m.getTimes()>1 ? m.getDose_2() : 0) + (m.getTimes()>2 ? m.getDose_3() : 0) + (m.getTimes()>3 ? m.getDose_4() : 0);
                    if(amount / dosage == days) {
                        newNameList.add(m.getName());
                    }
                }
                if(!nameList.equals(newNameList)) {
                    nameList.retainAll(newNameList);
                    if(!nameList.equals(newNameList)) { // newNameList contains element that is not existing in old nameList

                        nameList.clear();
                        nameList.addAll(newNameList);

                        String title = days + " days left before " +
                                String.join(", ", newNameList) +
                                " runs out";
                        Notification notification = builder.setContentTitle(title).setContentText(message).build();
                        notification.priority = Notification.PRIORITY_HIGH;
                        notificationManager.notify(3, notification);
                    }
                }
            }
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
