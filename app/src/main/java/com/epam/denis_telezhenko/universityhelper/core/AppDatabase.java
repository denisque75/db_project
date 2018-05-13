package com.epam.denis_telezhenko.universityhelper.core;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.dao.ScheduleDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.ClassInfo;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Day;
import com.epam.denis_telezhenko.universityhelper.ui.utils.DataConverter;

@Database(entities = {Note.class, Data.class, Day.class}, version = 2, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "student-helper-database";

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract NoteDao getNoteDao();

    public abstract ScheduleDao getScheduleDao();

}
