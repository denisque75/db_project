package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.os.AsyncTask;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.dao.ScheduleDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.epam.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.epam.denis_telezhenko.universityhelper.ui.main.view.MainListView;

import java.util.Date;
import java.util.List;

public class ScheduleDbPresenter extends BasePresenter<ScheduleView> {
    private static final String TAG = "ScheduleFragmentPres";

    private ScheduleDao dao;

    public ScheduleDbPresenter(ScheduleView view, ScheduleDao dao) {
        super(view);
        this.dao = dao;
    }

    public void findAndShowSchedule() {
        new SelectAsyncTask(dao, getView()).execute();
    }

    private static class SelectAsyncTask extends AsyncTask<Void , Void, List<Data>> {
        ScheduleDao dao;
        ScheduleView view;

        SelectAsyncTask(ScheduleDao  dao, ScheduleView view) {
            this.dao = dao;
            this.view = view;
        }

        @Override
        protected List<Data> doInBackground(Void... str) {
            Log.d(TAG, "doInBackground selecting from db");
            return dao.findAllDates();
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled selecting");
        }

        @Override
        protected void onPostExecute(List<Data> responses) {
            Log.d(TAG, "onPostExecute: " + responses);
            view.showData(responses);
        }
    }
}
