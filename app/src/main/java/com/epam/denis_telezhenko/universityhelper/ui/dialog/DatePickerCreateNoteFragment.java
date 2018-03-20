package com.epam.denis_telezhenko.universityhelper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import com.epam.denis_telezhenko.universityhelper.ui.create_note.CreateNoteActivity;

public class DatePickerCreateNoteFragment extends BaseDatePicker {
    private OnPickerCompleteListener onPickerCompleteListener;

    public DatePickerCreateNoteFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPickerCompleteListener) {
            onPickerCompleteListener = (OnPickerCompleteListener) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Bundle response = new Bundle();
        response.putInt(CreateNoteActivity.RESULT, DATE_REQUEST_CODE);
        response.putInt(YEAR_SELECTED, year);
        response.putInt(MONTH_SELECTED, month);
        response.putInt(DAY_SELECTED, day);
        onPickerCompleteListener.pickerComplete(response);
    }
}
