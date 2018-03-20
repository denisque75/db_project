package com.epam.denis_telezhenko.universityhelper.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TimePicker;

public class TimeDialogEditNoteFragment extends BaseTimePicker {

    public TimeDialogEditNoteFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d("TIME", "hour=" + hourOfDay + ":" + minute);
        Intent intent = new Intent();
        intent.putExtra(MINUTE_SELECTED, minute);
        intent.putExtra(HOUR_SELECTED, hourOfDay);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
