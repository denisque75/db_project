package com.epam.denis_telezhenko.universityhelper.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.firebase_callbacks.DataFromDBCallback;
import com.epam.denis_telezhenko.universityhelper.core.services.firebase.ReadFromFirebase;
import com.epam.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.epam.denis_telezhenko.universityhelper.ui.main.view.MainListView;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MainPresenter extends BasePresenter<MainListView> implements DataFromDBCallback<Note> {
    private static final String TAG = "MainPresenter";
    private DatabaseReference database;
    private NoteDao dao;

    private MutableLiveData<Boolean> isDataSavedToDb;

    public MainPresenter(MainListView mainListView, DatabaseReference database, NoteDao dao) {
        super(mainListView);
        this.database = database;
        this.dao = dao;
        isDataSavedToDb = new MutableLiveData<>();
    }

    public void setDatabaseEventListener(String uid) {
        ReadFromFirebase.readNotes(this,database, uid);
    }

    @Override
    public void newDataFromServer(List<Note> data) {
        saveDataToDb(data);
        getView().setEntitiesToAdapter(data);
    }

    private void saveDataToDb(List<Note> data) {
        new SaveNotesAsyncTask(dao, isDataSavedToDb).execute(data);
    }

    public MutableLiveData<Boolean> getIsDataSavedToDb() {
        return isDataSavedToDb;
    }

    public void showNotes() {
        new SelectAsyncTask(dao, getView()).execute();
    }

    private static class SelectAsyncTask extends AsyncTask<Void, Void, List<Note>> {
        NoteDao noteDao;
        MainListView notes;

        SelectAsyncTask(NoteDao noteDao, MainListView notes) {
            this.noteDao = noteDao;
            this.notes = notes;
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground selecting from db");
            return noteDao.readNotes();
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled selecting");
        }

        @Override
        protected void onPostExecute(List<Note> responses) {
            Log.d(TAG, "onPostExecute: " + responses);
            notes.setEntitiesToAdapter(responses);
        }
    }

    private static class SaveNotesAsyncTask extends AsyncTask<List<Note>, Void, Void> {
        NoteDao noteDao;
        MutableLiveData<Boolean> isDataSaveToDB;

        SaveNotesAsyncTask(NoteDao noteDao, MutableLiveData<Boolean> isDataSaveToDB) {
            this.noteDao = noteDao;
            this.isDataSaveToDB = isDataSaveToDB;
        }

        @Override
        protected Void doInBackground(final List<Note>... params) {
            Log.d(TAG, "doInBackground: saving");
            if (params[0] != null) {
                noteDao.insertNotes(params[0]);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled saving");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            isDataSaveToDB.setValue(true);
        }
    }
}
