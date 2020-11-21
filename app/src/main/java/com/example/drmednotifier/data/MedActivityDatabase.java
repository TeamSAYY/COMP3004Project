package com.example.drmednotifier.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MedActivity.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MedActivityDatabase extends RoomDatabase {
    public abstract MedActivityDao medActivityDao();

    private static volatile MedActivityDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MedActivityDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedActivityDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MedActivityDatabase.class,
                            "medactivity_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}