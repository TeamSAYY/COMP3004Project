package com.example.drmednotifier.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName = "notifsetting_table")
public class NotifSetting {
    @PrimaryKey
    @NonNull
    private int notifSettingId;

    private boolean enableNotif;

    private int notifTypeId;
    private int remindInMinutesId;

    private String notifMessage;

    private boolean enableRefillNotif;

    private int daysBeforeRefillId;

    private String refillNotifMessage;

    private int notifSoundId;

    private int snoozeMinutes;

    @Ignore
    private int snoozeTimes[] = {0, 2, 5, 10, 30, 60};

    @Ignore
    public NotifSetting(int notifSettingId, boolean enableNotif, int notifTypeId, int remindInMinutesId, String notifMessage,
                        boolean enableRefillNotif, int daysBeforeRefillId, String refillNotifMessage,
                        int notifSoundId, int snoozeMinutes) {
        this.notifSettingId = notifSettingId;
        this.enableNotif = enableNotif;
        this.notifTypeId = notifTypeId;
        this.remindInMinutesId = remindInMinutesId;
        this.notifMessage = notifMessage;
        this.enableRefillNotif = enableRefillNotif;
        this.daysBeforeRefillId = daysBeforeRefillId;
        this.refillNotifMessage = refillNotifMessage;
        this.notifSoundId = notifSoundId;
        this.snoozeMinutes = snoozeMinutes;
    }

    // Default notification setting constructor
    public NotifSetting() {
        this.notifSettingId = new Random().nextInt(Integer.MAX_VALUE);;
        this.enableNotif = true;
        this.notifTypeId = 2;
        this.remindInMinutesId = 0;
        this.notifMessage = "Please take your medication!";
        this.enableRefillNotif = true;
        this.daysBeforeRefillId = 0;
        this.refillNotifMessage = "It's time for a medication refill!";
        this.notifSoundId = 0;
        this.snoozeMinutes = snoozeTimes[0];
    }

    public int getNotifSettingId() {
        return notifSettingId;
    }

    public void setNotifSettingId(int notifSettingId) {
        this.notifSettingId = notifSettingId;
    }

    public boolean isEnableNotif() {
        return enableNotif;
    }

    public void setEnableNotif(boolean enableNotif) {
        this.enableNotif = enableNotif;
    }

    public int getNotifTypeId() {
        return notifTypeId;
    }

    public void setNotifTypeId(int notifTypeId) {
        this.notifTypeId = notifTypeId;
    }

    public int getRemindInMinutesId() {
        return remindInMinutesId;
    }

    public void setRemindInMinutesId(int remindInMinutesId) {
        this.remindInMinutesId = remindInMinutesId;
        try {
            this.snoozeMinutes = snoozeTimes[remindInMinutesId];
        } catch (IndexOutOfBoundsException e) {
            // Do nothing
        }
    }

    public String getNotifMessage() {
        return notifMessage;
    }

    public void setNotifMessage(String notifMessage) {
        this.notifMessage = notifMessage;
    }

    public boolean isEnableRefillNotif() {
        return enableRefillNotif;
    }

    public void setEnableRefillNotif(boolean enableRefillNotif) {
        this.enableRefillNotif = enableRefillNotif;
    }

    public int getDaysBeforeRefillId() {
        return daysBeforeRefillId;
    }

    public void setDaysBeforeRefillId(int daysBeforeRefillId) {
        this.daysBeforeRefillId = daysBeforeRefillId;
    }

    public String getRefillNotifMessage() {
        return refillNotifMessage;
    }

    public void setRefillNotifMessage(String refillNotifMessage) {
        this.refillNotifMessage = refillNotifMessage;
    }

    public int getNotifSoundId() {
        return notifSoundId;
    }

    public void setNotifSoundId(int notifSoundId) {
        this.notifSoundId = notifSoundId;
    }

    public int getSnoozeMinutes() {
        return snoozeMinutes;
    }

    public void setSnoozeMinutes(int snoozeMinutes) {
        this.snoozeMinutes = snoozeMinutes;
    }
}
