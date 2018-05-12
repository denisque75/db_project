package com.epam.denis_telezhenko.universityhelper.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.ui.login.LoginActivity;
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
            Intent i = new Intent(getContext(), LoginActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }
}
