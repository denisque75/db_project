package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.entity.NoteEntity;
import com.epam.denis_telezhenko.universityhelper.ui.StubUtils;
import com.epam.denis_telezhenko.universityhelper.ui.details.dialog.DatePickerFragment;
import com.epam.denis_telezhenko.universityhelper.ui.details.dialog.TimeDialogFragment;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.List;

import static com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils.getDateInString;

public class EditNoteFragment extends Fragment {
    public static final String TAG = "edit_note_fragment";

    private List<NoteEntity> noteEntities;
    private long id;

    private TextView date;
    private TextView time;
    private EditText title;
    private EditText desc;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(long id) {
        EditNoteFragment editNoteFragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putLong(DetailsActivity.NOTE_ID_TAG, id);
        editNoteFragment.setArguments(args);
        return editNoteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_note, container, false);

        noteEntities = StubUtils.getNotes();
        id = getArguments().getLong(DetailsActivity.NOTE_ID_TAG, 0);
        NoteEntity note = getNoteById();

        title = rootView.findViewById(R.id.details_edit__title);
        title.setText(note.getTitle());

        desc = rootView.findViewById(R.id.details_edit__desc);
        desc.setText(note.getDescrition());

        date = rootView.findViewById(R.id.details_edit__choose_date);
        time = rootView.findViewById(R.id.details_edit__choose_time);

        RadioGroup radioGroup = rootView.findViewById(R.id.details_edit__alarm);
        radioGroup.setOnCheckedChangeListener(this::changeAlarmState);

        if (note.getDate() != null) {
            setVisibleDateText(View.VISIBLE);
            date.setText(getDateInString(note.getDate()));
            time.setText(TimeUtils.getTimeInString(note.getDate()));
            ((RadioButton) rootView.findViewById(R.id.details_edit__alarm_on)).setChecked(true);
        } else {
            setVisibleDateText(View.GONE);
            ((RadioButton) rootView.findViewById(R.id.details_edit__alarm_off)).setChecked(false);
        }

        rootView.findViewById(R.id.details_edit__edit_button).setOnClickListener(this::changeData);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        time.setOnClickListener(this::openTimeDialog);
        date.setOnClickListener(this::openDateDialog);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case DatePickerFragment.DATE_REQUEST_CODE:
                    int year = data.getIntExtra(DatePickerFragment.YEAR_SELECTED, -1);
                    int month = data.getIntExtra(DatePickerFragment.MONTH_SELECTED, -1);
                    int day = data.getIntExtra(DatePickerFragment.DAY_SELECTED, -1);
                    String formattedDate = TimeUtils.getDateInString(year, month, day);
                    date.setText(formattedDate);
                    break;
                case TimeDialogFragment.TIME_REQUEST_CODE:
                    int hour = data.getIntExtra(TimeDialogFragment.HOUR_SELECTED, -1);
                    int minutes = data.getIntExtra(TimeDialogFragment.MINUTE_SELECTED, -1);
                    String formattedTime = TimeUtils.getTimeInString(hour, minutes);
                    time.setText(formattedTime);
                    break;
            }
        }
    }

    private void openDateDialog(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(this, DatePickerFragment.DATE_REQUEST_CODE);
        datePickerFragment.show(getFragmentManager(), DatePickerFragment.TAG);
    }

    private void openTimeDialog(View view) {
        TimeDialogFragment timeDialogFragment = new TimeDialogFragment();
        timeDialogFragment.setTargetFragment(this, TimeDialogFragment.TIME_REQUEST_CODE);
        timeDialogFragment.show(getFragmentManager(), TimeDialogFragment.TAG);
    }

    private void changeData(View view) {
        //TODO: save data and finish fragment
    }

    private void changeAlarmState(RadioGroup radioGroup, int i) {
        if (i == R.id.details_edit__alarm_on) {
            time.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
        } else {
            time.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
        }
    }

    public NoteEntity getNoteById() {
        for (int i = 0; i < noteEntities.size(); i++) {
            if (noteEntities.get(i).getId() == id) {
                return noteEntities.get(i);
            }
        }
        return noteEntities.get(0);
    }

    public void setVisibleDateText(int visibility) {
        date.setVisibility(visibility);

        time.setVisibility(visibility);
    }
}
