package com.epam.denis_telezhenko.universityhelper.ui.details.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public static final String TAG = "time_dialog_fragment";
    public static final int TIME_REQUEST_CODE = 1122;
    public static final String HOUR_SELECTED = "hour";
    public static final String MINUTE_SELECTED = "minute";

    public TimeDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
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
