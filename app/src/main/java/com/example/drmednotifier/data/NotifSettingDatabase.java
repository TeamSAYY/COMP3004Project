package com.example.drmednotifier.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NotifSetting.class}, version = 1, exportSchema = false)
public abstract class NotifSettingDatabase extends RoomDatabase {
    public abstract NotifSettingDao notifSettingDao();

    private static volatile NotifSettingDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
