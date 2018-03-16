package com.epam.denis_telezhenko.universityhelper.ui.details.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static final String TAG = "date_dialog_fragment";
    public static final int DATE_REQUEST_CODE = 1133;
    public static final String YEAR_SELECTED = "year";
    public static final String MONTH_SELECTED = "month";
    public static final String DAY_SELECTED = "day";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("DATE", "year:" + year + " month:" + month + " day:" + day);
        Intent intent = new Intent();
        intent.putExtra(YEAR_SELECTED, year);
        intent.putExtra(MONTH_SELECTED, month);
        intent.putExtra(DAY_SELECTED, day);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
