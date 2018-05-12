package com.epam.denis_telezhenko.universityhelper.ui.details.view;

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
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.App;
import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.ui.details.EditorPresenter;
import com.epam.denis_telezhenko.universityhelper.ui.dialog.DatePickerEditNoteFragment;
import com.epam.denis_telezhenko.universityhelper.ui.dialog.TimeDialogEditNoteFragment;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils.getDateInString;

public class EditNoteFragment extends Fragment implements EditView {
    public static final String TAG = "edit_note_fragment";

    private long id;
    private Note note;

    private TextView date;
    private TextView time;
    private EditText title;
    private EditText desc;

    private EditorPresenter presenter;

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

        NoteDao dao = ((App)getActivity().getApplication()).getDatabase().getNoteDao();
        presenter = new EditorPresenter(this, dao, FirebaseDatabase.getInstance().getReference());

        id = getArguments().getLong(DetailsActivity.NOTE_ID_TAG, 0);

        title = rootView.findViewById(R.id.details_edit__title);
        desc = rootView.findViewById(R.id.details_edit__desc);

        date = rootView.findViewById(R.id.details_edit__choose_date);
        time = rootView.findViewById(R.id.details_edit__choose_time);

        RadioGroup radioGroup = rootView.findViewById(R.id.details_edit__alarm);
        radioGroup.setOnCheckedChangeListener(this::changeAlarmState);

        rootView.findViewById(R.id.details_edit__edit_button).setOnClickListener(this::changeData);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.showNoteById(id);
        time.setOnClickListener(this::openTimeDialog);
        date.setOnClickListener(this::openDateDialog);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case DatePickerEditNoteFragment.DATE_REQUEST_CODE:
                    int year = data.getIntExtra(DatePickerEditNoteFragment.YEAR_SELECTED, -1);
                    int month = data.getIntExtra(DatePickerEditNoteFragment.MONTH_SELECTED, -1);
                    int day = data.getIntExtra(DatePickerEditNoteFragment.DAY_SELECTED, -1);
                    String formattedDate = TimeUtils.getDateInString(year, month, day);
                    date.setText(formattedDate);
                    break;
                case TimeDialogEditNoteFragment.TIME_REQUEST_CODE:
                    int hour = data.getIntExtra(TimeDialogEditNoteFragment.HOUR_SELECTED, -1);
                    int minutes = data.getIntExtra(TimeDialogEditNoteFragment.MINUTE_SELECTED, -1);
                    String formattedTime = TimeUtils.getTimeInString(hour, minutes);
                    time.setText(formattedTime);
                    break;
            }
        }
    }

    private void openDateDialog(View view) {
        DatePickerEditNoteFragment datePickerEditNoteFragment = new DatePickerEditNoteFragment();
        datePickerEditNoteFragment.setTargetFragment(this, DatePickerEditNoteFragment.DATE_REQUEST_CODE);
        datePickerEditNoteFragment.show(getFragmentManager(), DatePickerEditNoteFragment.TAG);
    }

    private void openTimeDialog(View view) {
        TimeDialogEditNoteFragment timeDialogEditNoteFragment = new TimeDialogEditNoteFragment();
        timeDialogEditNoteFragment.setTargetFragment(this, TimeDialogEditNoteFragment.TIME_REQUEST_CODE);
        timeDialogEditNoteFragment.show(getFragmentManager(), TimeDialogEditNoteFragment.TAG);
    }

    @Override
    public void showNote(Note note) {
        this.note = note;
        title.setText(note.getTitle());
        desc.setText(note.getDescription());
        if (note.getDate() != null) {
            setVisibleDateText(View.VISIBLE);
            date.setText(getDateInString(note.getDate()));
            time.setText(TimeUtils.getTimeInString(note.getDate()));
            ((RadioButton) getView().findViewById(R.id.details_edit__alarm_on)).setChecked(true);
        } else {
            setVisibleDateText(View.GONE);
            ((RadioButton) getView().findViewById(R.id.details_edit__alarm_off)).setChecked(false);
        }
    }

    private void changeData(View view) {
        presenter.editNote(note, FirebaseAuth.getInstance().getCurrentUser().getUid());
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

    public void setVisibleDateText(int visibility) {
        date.setVisibility(visibility);

        time.setVisibility(visibility);
    }

    @Override
    public void finishActivity() {
        Toast.makeText(getContext(), "Все изменения были сохранены", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}
