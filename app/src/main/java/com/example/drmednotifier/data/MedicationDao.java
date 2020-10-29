package com.example.drmednotifier.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicationDao {
    @Insert
    void insert(Medication medication);

    @Query("DELETE FROM medication_table")
    void deleteAll();

    @Query("SELECT * FROM medication_table ORDER BY created ASC")
    LiveData<List<Medication>> getMedications();

    @Query("SELECT * FROM medication_table WHERE medId =:id")
    Medication loadSingle(int id);

    @Update
    void update(Medication medication);
}
