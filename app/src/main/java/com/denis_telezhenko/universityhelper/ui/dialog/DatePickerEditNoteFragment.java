package com.denis_telezhenko.universityhelper.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;

public class DatePickerEditNoteFragment extends BaseDatePicker {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
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
