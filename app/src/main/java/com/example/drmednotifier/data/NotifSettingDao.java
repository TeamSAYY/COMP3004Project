package com.example.drmednotifier.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotifSettingDao {
    @Insert
    void insert(NotifSetting notifSetting);

    @Query("DELETE FROM notifsetting_table")
    void deleteAll();

    @Query("SELECT * FROM notifsetting_table")
    List<NotifSetting> getNotifSettings();

    @Update
    void update(NotifSetting notifSetting);
}