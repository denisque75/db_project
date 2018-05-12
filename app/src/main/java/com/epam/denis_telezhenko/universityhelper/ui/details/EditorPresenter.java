package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.services.firebase.ModifyFirebase;
import com.epam.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.epam.denis_telezhenko.universityhelper.ui.details.view.DetailsView;
import com.epam.denis_telezhenko.universityhelper.ui.details.view.EditView;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class EditorPresenter extends BasePresenter<EditView> {
    public static final String TAG = "EditorPresenter";
    private NoteDao noteDao;
    private DatabaseReference reference;

    public EditorPresenter(EditView editView, NoteDao noteDao, DatabaseReference reference){
        super(editView);
        this.noteDao = noteDao;
        this.reference = reference;
    }

    public void editNote(Note note, String uid) {
        ModifyFirebase.modifyNote(note, uid, reference);
        new ChangeNoteAsyncTask(noteDao, getView()).execute(note);
    }

    public void showNoteById(long id) {
        new SelectAsyncTask(noteDao, getView()).execute(id);
    }

    private static class ChangeNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;
        EditView editView;

        ChangeNoteAsyncTask(NoteDao noteDao, EditView editView) {
            this.noteDao = noteDao;
            this.editView = editView;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            Log.d(TAG, "doInBackground: saving");
            if (params[0] != null) {
                noteDao.deleteNote(params[0].getId());
                noteDao.insertNote(params[0]);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled saving");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            editView.finishActivity();
        }
    }

    private static class SelectAsyncTask extends AsyncTask<Long, Void, Note> {
        NoteDao noteDao;
        EditView detailsView;

        SelectAsyncTask(NoteDao noteDao, EditView detailsView) {
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
}
