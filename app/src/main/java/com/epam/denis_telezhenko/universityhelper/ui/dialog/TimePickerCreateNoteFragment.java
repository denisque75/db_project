package com.epam.denis_telezhenko.universityhelper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

import com.epam.denis_telezhenko.universityhelper.ui.create_note.CreateNoteActivity;

public class TimePickerCreateNoteFragment extends BaseTimePicker {
    private OnPickerCompleteListener onPickerCompleteListener;

    public TimePickerCreateNoteFragment() {
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
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Bundle response = new Bundle();
        response.putInt(CreateNoteActivity.RESULT, TimeDialogEditNoteFragment.TIME_REQUEST_CODE);
        response.putInt(MINUTE_SELECTED, minute);
        response.putInt(HOUR_SELECTED, hourOfDay);
        onPickerCompleteListener.pickerComplete(response);
    }
}
