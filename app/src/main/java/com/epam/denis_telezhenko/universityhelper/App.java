package com.epam.denis_telezhenko.universityhelper;

import android.app.Application;

import com.epam.denis_telezhenko.universityhelper.core.AppDatabase;

public class App extends Application {
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = AppDatabase.getAppDatabase(this);
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
