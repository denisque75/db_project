package com.denis_telezhenko.universityhelper.ui.schedule;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.denis_telezhenko.universityhelper.core.dao.ScheduleDao;
import com.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.denis_telezhenko.universityhelper.core.services.firebase.ReadFromFirebase;
import com.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class SchedulePresenter extends BasePresenter{
    private DatabaseReference reference;
    private ScheduleDao dao;
    private MutableLiveData<List<Data>> liveData;
    private static final String TAG = "SchedulePresenter";
    private MutableLiveData<Boolean> isSaved;

    public SchedulePresenter(DatabaseReference reference, ScheduleDao dao) {
        super(null);
        this.reference = reference;
        this.dao = dao;
        liveData = new MutableLiveData<>();
        isSaved = new MutableLiveData<>();
    }

    public MutableLiveData<List<Data>> getLiveData() {
        return liveData;
    }

    public void returnScheduleByGroup(String group) {
        ReadFromFirebase.readSchedule(liveData, reference, group);

    }

    public void saveDataAndShow(List<Data> data) {
        new SaveNotesAsyncTask(dao, isSaved).execute(data);
    }

    public MutableLiveData<Boolean> getIsSaved() {
        return isSaved;
    }

    private static class SaveNotesAsyncTask extends AsyncTask<List<Data>, Void, List<Data>> {
        ScheduleDao dao;
        MutableLiveData<Boolean> isSaved;

        SaveNotesAsyncTask(ScheduleDao dao, MutableLiveData<Boolean> isSaved) {
            this.dao = dao;
            this.isSaved = isSaved;
        }

        @Override
        protected List<Data> doInBackground(final List<Data>... params) {
            Log.d(TAG, "doInBackground: saving");
            if (params[0] != null) {
                dao.addSchedule(params[0]);
            }
            return params[0];
        }

        @Override
        protected void onCancelled() {
            isSaved.setValue(false);
            Log.d(TAG, "onCancelled saving");
        }

        @Override
        protected void onPostExecute(List<Data> data) {
            isSaved.setValue(true);
            Log.d(TAG, "onPostExecute: data saved");
        }
    }
}
