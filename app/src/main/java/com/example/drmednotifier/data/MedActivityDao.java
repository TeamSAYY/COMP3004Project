package com.example.drmednotifier.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface MedActivityDao {
    @Insert
    void insert(MedActivity medActivity);

    @Insert
    void insertAll(List<MedActivity> medActivities);

    @Query("DELETE FROM medactivity_table")
    void deleteAll();

    @Query("SELECT * FROM medactivity_table ORDER BY date ASC")
    LiveData<List<MedActivity>> getMedActivity();

    @Query("SELECT * FROM medactivity_table WHERE medActivityId =:id")
    MedActivity loadSingle(int id);

    @Query("SELECT * FROM medactivity_table WHERE medId =:medId AND date =:date")
    MedActivity loadByMedDate(int medId, Date date);

    @Update
    void update(MedActivity medActivity);
}
