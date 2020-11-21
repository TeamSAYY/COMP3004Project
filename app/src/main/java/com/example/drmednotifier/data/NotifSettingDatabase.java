package com.example.drmednotifier.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotifSetting.class}, version = 1, exportSchema = false)
public abstract class NotifSettingDatabase extends RoomDatabase {
    public abstract NotifSettingDao notifSettingDao();

    private static volatile NotifSettingDatabase INSTANCE;

    public static NotifSettingDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotifSettingDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotifSettingDatabase.class,
                            "notifsetting_database"
                    ).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
