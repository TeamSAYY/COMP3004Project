package com.teamsayy.drmednotifier.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.room.Room;

import com.teamsayy.drmednotifier.data.GenerateRandomInt;
import com.teamsayy.drmednotifier.data.Medication;
import com.teamsayy.drmednotifier.data.MedicationDao;
import com.teamsayy.drmednotifier.data.MedicationDatabase;
import com.teamsayy.drmednotifier.data.NotifSetting;
import com.teamsayy.drmednotifier.data.NotifSettingDao;
import com.teamsayy.drmednotifier.data.NotifSettingDatabase;
import com.teamsayy.drmednotifier.service.AlarmService;
import com.teamsayy.drmednotifier.service.RefillReminderService;
import com.teamsayy.drmednotifier.service.RescheduleAlarmsService;

import java.util.Calendar;

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

        if(intent.getBooleanExtra("REFILL", false)) {
            startRefillReminderService(context);
            return;
        }

        if (!notifSetting.isEnableNotif()) {
            return;
        }

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            startRescheduleAlarmsService(context);
        } else {
            if (alarmIsToday(intent)) {
                startAlarmService(context, intent);
            }

            MedicationDatabase medicationDatabase = Room.databaseBuilder(context.getApplicationContext(), MedicationDatabase.class, "medication_database").allowMainThreadQueries().build();
            MedicationDao medicationDao = medicationDatabase.medicationDao();

            Medication m = medicationDao.loadSingle(intent.getIntExtra(MED_ID, GenerateRandomInt.get()));

            try {
                m.schedule(context);
            } catch (NullPointerException e) {
                // m is null, which means it's not an alarm in the database
            }
        }
    }

    private boolean alarmIsToday(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        switch(today) {
            case Calendar.MONDAY:
                return intent.getBooleanExtra(MONDAY, false);
            case Calendar.TUESDAY:
                return intent.getBooleanExtra(TUESDAY, false);
            case Calendar.WEDNESDAY:
                return intent.getBooleanExtra(WEDNESDAY, false);
            case Calendar.THURSDAY:
                return intent.getBooleanExtra(THURSDAY, false);
            case Calendar.FRIDAY:
                return intent.getBooleanExtra(FRIDAY, false);
            case Calendar.SATURDAY:
                return intent.getBooleanExtra(SATURDAY, false);
            case Calendar.SUNDAY:
                return intent.getBooleanExtra(SUNDAY, false);
        }
        return false;
    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmService.class);
        intentService.putExtra(MED_ID, intent.getIntExtra(MED_ID, GenerateRandomInt.get()));
        intentService.putExtra(TITLE, intent.getStringExtra(TITLE));
        intentService.putExtra(MED_NAME, intent.getStringExtra(MED_NAME));
        intentService.putExtra(MED_DOSE, intent.getIntExtra(MED_DOSE, 0));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        context.startService(intentService);
    }

    private void startRefillReminderService(Context context) {
        Intent intentService = new Intent(context, RefillReminderService.class);
        context.startService(intentService);
    }
}
