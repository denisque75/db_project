package com.denis_telezhenko.universityhelper.ui.details;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.denis_telezhenko.universityhelper.core.entity.Note;
import com.denis_telezhenko.universityhelper.core.services.firebase.ModifyFirebase;
import com.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.denis_telezhenko.universityhelper.ui.details.view.DetailsView;
import com.google.firebase.database.DatabaseReference;

public class DetailsPresenter extends BasePresenter<DetailsView> {
    public static final String TAG = "DetailsPresenter";
    private NoteDao noteDao;
    private DatabaseReference database;

    private MutableLiveData<Boolean> isDeleted;

    public DetailsPresenter(DetailsView detailsView, NoteDao noteDao, DatabaseReference database) {
        super(detailsView);
        this.noteDao = noteDao;
        this.database = database;
        isDeleted = new MutableLiveData<>();
    }

    public void showNoteById(long id) {
        new SelectAsyncTask(noteDao, getView()).execute(id);
    }

    public void deleteNote(String uid, long id, String strategy) {
        new DeleteByIdAsyncTask(noteDao,isDeleted).execute(id);
        ModifyFirebase.deleteNote(uid, id, database, isDeleted, strategy);
    }

    public MutableLiveData<Boolean> getIsDeleted() {
        return isDeleted;
    }

    private static class SelectAsyncTask extends AsyncTask<Long, Void, Note> {
        NoteDao noteDao;
        DetailsView detailsView;

        SelectAsyncTask(NoteDao noteDao, DetailsView detailsView) {
            this.noteDao = noteDao;
            this.detailsView = detailsView;
        }

        @Override
        protected Note doInBackground(Long... id) {
            Log.d(TAG, "doInBackground selecting from db");
            return noteDao.readNoteById(id[0]);
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled selecting");
        }

        @Override
        protected void onPostExecute(Note response) {
            Log.d(TAG, "onPostExecute: " + response);
            detailsView.showNote(response);
        }
    }

    private static class DeleteByIdAsyncTask extends AsyncTask<Long, Void, Void> {
        NoteDao noteDao;
        MutableLiveData<Boolean> isDeleted;

        DeleteByIdAsyncTask(NoteDao noteDao, MutableLiveData<Boolean> isDeleted) {
            this.noteDao = noteDao;
            this.isDeleted = isDeleted;
        }

        @Override
        protected Void doInBackground(Long... id) {
            Log.d(TAG, "doInBackground selecting from db");
            noteDao.deleteNote(id[0]);
            return null;
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled selecting");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            isDeleted.setValue(true);
        }
    }
}
