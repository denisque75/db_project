package com.epam.denis_telezhenko.universityhelper.core.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSchedule(List<Data> data);

    @Query("SELECT * FROM Data WHERE :dayName LIKE Data.dayName")
    Data findDate(String dayName);

    @Query("SELECT * FROM Data")
    List<Data> findAllDates();

}
