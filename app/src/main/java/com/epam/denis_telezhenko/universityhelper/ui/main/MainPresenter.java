package com.epam.denis_telezhenko.universityhelper.ui.main;

import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.firebase_callbacks.DataFromDBCallback;
import com.epam.denis_telezhenko.universityhelper.core.services.firebase.ReadFromFirebase;
import com.epam.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.epam.denis_telezhenko.universityhelper.ui.main.view.MainListView;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainListView> implements DataFromDBCallback<Note> {
    private static final String TAG = "MainPresenter";
    private DatabaseReference database;

    public MainPresenter(MainListView mainListView, DatabaseReference database) {
        super(mainListView);
        this.database = database;
    }

    public void setDatabaseEventListener(String uid) {
        ReadFromFirebase.readNotes(this,database, uid);
    }

    @Override
    public void newDataFromDB(List<Note> data) {
        getView().setEntitiesToAdapter(data);
    }
}
