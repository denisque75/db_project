package com.epam.denis_telezhenko.universityhelper.core;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "student-helper-database";

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

}
