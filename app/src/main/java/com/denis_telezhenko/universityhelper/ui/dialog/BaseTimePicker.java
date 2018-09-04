package com.denis_telezhenko.universityhelper.ui.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

public abstract class BaseTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = "time_dialog_fragment";
    public static final int TIME_REQUEST_CODE = 1122;
    public static final String HOUR_SELECTED = "hour";
    public static final String MINUTE_SELECTED = "minute";

    public BaseTimePicker() {
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
}
