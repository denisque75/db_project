package com.denis_telezhenko.universityhelper.ui.main.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.denis_telezhenko.universityhelper.App;
import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.denis_telezhenko.universityhelper.ui.login.view.LoginActivity;
import com.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutDialogFragment extends DialogFragment {
    public static final String TAG = "logout_tag";

    public LogoutDialogFragment() {

    }

    public static void show(FragmentManager fragmentManager) {
        new LogoutDialogFragment().show(fragmentManager,
                LogoutDialogFragment.class.getSimpleName());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.log_out)
                .setMessage(R.string.are_you_sure)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.submit, new LogoutCallback())
                .create();
    }

    private class LogoutCallback implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (DialogInterface.BUTTON_POSITIVE == which) {
                logout();
            }
        }

        private void logout() {
            FirebaseAuth.getInstance().signOut();
            getActivity().getSharedPreferences(Constants.USER_SHARED, Context.MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            new ClearAsyncTask(((App)getActivity().getApplication()).getDatabase().getNoteDao()).execute();
            Intent i = new Intent(getContext(), LoginActivity.class);
            startActivity(i);
            getActivity().finish();
        }

    }

    private static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;

        ClearAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground clear");
            noteDao.clearTable();
            return null;
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled clear");
        }

        @Override
        protected void onPostExecute(Void responses) {
            Log.d(TAG, "onPostExecute: all data clear" + responses);
        }
    }
}
