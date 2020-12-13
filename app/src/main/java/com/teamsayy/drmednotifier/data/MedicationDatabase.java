package com.teamsayy.drmednotifier.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class MedicationDatabase extends RoomDatabase {
    public abstract MedicationDao medicationDao();

    private static volatile MedicationDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MedicationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MedicationDatabase.class,
                            "medication_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
