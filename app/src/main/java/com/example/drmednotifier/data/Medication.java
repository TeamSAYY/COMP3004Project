package com.example.drmednotifier.data;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver;

import java.util.Calendar;

import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.FRIDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MONDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.RECURRING;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.SATURDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.SUNDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.THURSDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.TITLE;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.TUESDAY;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.WEDNESDAY;

@Entity(tableName = "medication_table")
public class Medication {
    @PrimaryKey
    @NonNull
    private int medId;

    private String name;
    private String description;
    private int quantity;
    private int shape_id;

    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    private int times;

    private int hour_1, minute_1, dose_1;
    private int hour_2, minute_2, dose_2;
    private int hour_3, minute_3, dose_3;
    private int hour_4, minute_4, dose_4;

    private long created;

    public Medication(int medId, String name, String description, int quantity, int shape_id, long created,
                      boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday,
                      int times, int hour_1, int minute_1, int dose_1, int hour_2, int minute_2, int dose_2, int hour_3, int minute_3, int dose_3, int hour_4, int minute_4, int dose_4) {
        this.medId = medId;

        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.shape_id = shape_id;

        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;

        this.times = times;

        this.hour_1 = hour_1;
        this.minute_1 = minute_1;
        this.dose_1 = dose_1;
        this.hour_2 = hour_2;
        this.minute_2 = minute_2;
        this.dose_2 = dose_2;
        this.hour_3 = hour_3;
        this.minute_3 = minute_3;
        this.dose_3 = dose_3;
        this.hour_4 = hour_4;
        this.minute_4 = minute_4;
        this.dose_4 = dose_4;

        this.created = created;
    }

    public Medication(int medId, String name, String description, int quantity, int shape_id, long created,
                      boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday,
                      int hour, int minute, int dose) {
        this.medId = medId;

        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.shape_id = shape_id;

        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;

        this.times = 1;

        this.hour_1 = hour;
        this.minute_1 = minute;
        this.dose_1 = dose;
        this.hour_2 = 0;
        this.minute_2 = 0;
        this.dose_2 = 0;
        this.hour_3 = 0;
        this.minute_3 = 0;
        this.dose_3 = 0;
        this.hour_4 = 0;
        this.minute_4 = 0;
        this.dose_4 = 0;

        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getShape_id() {
        return shape_id;
    }

    public int getTimes() {
        return times;
    }

    public int getHour_1() {
        return hour_1;
    }

    public int getMinute_1() {
        return minute_1;
    }

    public int getDose_1() {return dose_1;}

    public int getHour_2() {
        return hour_2;
    }

    public int getMinute_2() {
        return minute_2;
    }

    public int getDose_2() {return dose_2;}

    public int getHour_3() {
        return hour_3;
    }

    public int getMinute_3() {
        return minute_3;
    }

    public int getDose_3() {return dose_3;}

    public int getHour_4() {
        return hour_4;
    }

    public int getMinute_4() {
        return minute_4;
    }

    public int getDose_4() {return dose_4;}

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(RECURRING, true);
        intent.putExtra(MONDAY, monday);
        intent.putExtra(TUESDAY, tuesday);
        intent.putExtra(WEDNESDAY, wednesday);
        intent.putExtra(THURSDAY, thursday);
        intent.putExtra(FRIDAY, friday);
        intent.putExtra(SATURDAY, saturday);
        intent.putExtra(SUNDAY, sunday);

        intent.putExtra(TITLE, name);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, medId, intent, 0);

        if (times >= 1) {
            Calendar calendar_1 = Calendar.getInstance();
            calendar_1.setTimeInMillis(System.currentTimeMillis());
            calendar_1.set(Calendar.HOUR_OF_DAY, hour_1);
            calendar_1.set(Calendar.MINUTE, minute_1);
            calendar_1.set(Calendar.SECOND, 0);
            calendar_1.set(Calendar.MILLISECOND, 0);

            // if alarm time has already passed, increment day by 1
            if (calendar_1.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar_1.set(Calendar.DAY_OF_MONTH, calendar_1.get(Calendar.DAY_OF_MONTH) + 1);
            }

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar_1.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }

        if (times >= 2) {
            Calendar calendar_2 = Calendar.getInstance();
            calendar_2.setTimeInMillis(System.currentTimeMillis());
            calendar_2.set(Calendar.HOUR_OF_DAY, hour_2);
            calendar_2.set(Calendar.MINUTE, minute_2);
            calendar_2.set(Calendar.SECOND, 0);
            calendar_2.set(Calendar.MILLISECOND, 0);

            // if alarm time has already passed, increment day by 1
            if (calendar_2.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar_2.set(Calendar.DAY_OF_MONTH, calendar_2.get(Calendar.DAY_OF_MONTH) + 1);
            }

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar_2.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }

        if (times >= 3) {
            Calendar calendar_3 = Calendar.getInstance();
            calendar_3.setTimeInMillis(System.currentTimeMillis());
            calendar_3.set(Calendar.HOUR_OF_DAY, hour_3);
            calendar_3.set(Calendar.MINUTE, minute_3);
            calendar_3.set(Calendar.SECOND, 0);
            calendar_3.set(Calendar.MILLISECOND, 0);

            // if alarm time has already passed, increment day by 1
            if (calendar_3.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar_3.set(Calendar.DAY_OF_MONTH, calendar_3.get(Calendar.DAY_OF_MONTH) + 1);
            }

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar_3.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }

        if (times >= 4) {
            Calendar calendar_4 = Calendar.getInstance();
            calendar_4.setTimeInMillis(System.currentTimeMillis());
            calendar_4.set(Calendar.HOUR_OF_DAY, hour_4);
            calendar_4.set(Calendar.MINUTE, minute_4);
            calendar_4.set(Calendar.SECOND, 0);
            calendar_4.set(Calendar.MILLISECOND, 0);

            // if alarm time has already passed, increment day by 1
            if (calendar_4.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar_4.set(Calendar.DAY_OF_MONTH, calendar_4.get(Calendar.DAY_OF_MONTH) + 1);
            }

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar_4.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }
    }
}
