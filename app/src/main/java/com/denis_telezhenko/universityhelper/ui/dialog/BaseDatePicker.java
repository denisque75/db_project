package com.denis_telezhenko.universityhelper.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public abstract class BaseDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "date_dialog_fragment";
    public static final int DATE_REQUEST_CODE = 1133;
    public static final String YEAR_SELECTED = "year";
    public static final String MONTH_SELECTED = "month";
    public static final String DAY_SELECTED = "day";

    public BaseDatePicker() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
}
