package com.example.drmednotifier.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.MedicationDao;
import com.example.drmednotifier.data.MedicationDatabase;
import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;
import com.example.drmednotifier.service.AlarmService;
import com.example.drmednotifier.service.RescheduleAlarmsService;

import java.util.Calendar;
import java.util.Random;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String MED_ID = "MED_ID";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String RECURRING = "RECURRING";
    public static final String TITLE = "TITLE";
    public static final String MED_NAME = "MED_NAME";
    public static final String MED_DOSE = "MED_DOSE";
    public static final String HOUR = "HOUR";
    public static final String MINUTE = "MINUTE";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotifSettingDatabase notifSettingDatabase = NotifSettingDatabase.getDatabase(context);
        NotifSettingDao notifSettingDao = notifSettingDatabase.notifSettingDao();
        NotifSetting notifSetting = notifSettingDao.getNotifSettings().get(0);

        if (!notifSetting.isEnableNotif()) {
            return;
        }

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startRescheduleAlarmsService(context);
        } else {
            String toastText = String.format("Alarm Received");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            if (alarmIsToday(intent)) {
                startAlarmService(context, intent);
            }

            MedicationDatabase medicationDatabase = Room.databaseBuilder(context.getApplicationContext(), MedicationDatabase.class, "medication_database").allowMainThreadQueries().build();
            MedicationDao medicationDao = medicationDatabase.medicationDao();

            Medication m = medicationDao.loadSingle(intent.getIntExtra(MED_ID, new Random().nextInt(Integer.MAX_VALUE)));

            try {
                m.schedule(context);
            } catch (NullPointerException e) {
                // m is null, which means it's not an alarm in the database
                Log.d("myTag", "Snooze Alarm Received");
            }
        }
    }

    private boolean alarmIsToday(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        switch(today) {
            case Calendar.MONDAY:
                if (intent.getBooleanExtra(MONDAY, false))
                    return true;
                return false;
            case Calendar.TUESDAY:
                if (intent.getBooleanExtra(TUESDAY, false))
                    return true;
                return false;
            case Calendar.WEDNESDAY:
                if (intent.getBooleanExtra(WEDNESDAY, false))
                    return true;
                return false;
            case Calendar.THURSDAY:
                if (intent.getBooleanExtra(THURSDAY, false))
                    return true;
                return false;
            case Calendar.FRIDAY:
                if (intent.getBooleanExtra(FRIDAY, false))
                    return true;
                return false;
            case Calendar.SATURDAY:
                if (intent.getBooleanExtra(SATURDAY, false))
                    return true;
                return false;
            case Calendar.SUNDAY:
                if (intent.getBooleanExtra(SUNDAY, false))
                    return true;
                return false;
        }
        return false;
    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmService.class);
        intentService.putExtra(MED_ID, intent.getIntExtra(MED_ID, new Random().nextInt(Integer.MAX_VALUE)));
        intentService.putExtra(TITLE, intent.getStringExtra(TITLE));
        intentService.putExtra(MED_NAME, intent.getStringExtra(MED_NAME));
        intentService.putExtra(MED_DOSE, intent.getIntExtra(MED_DOSE, 0));

        Log.d("myTag", String.format("ALARM NAME: %s", intent.getStringExtra(MED_NAME)));

        Log.d("myTag", String.format("ALARM DOSE: %d", intent.getIntExtra(MED_DOSE, 0)));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }
}
