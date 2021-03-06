package com.denis_telezhenko.universityhelper.ui.create_note;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.core.entity.Note;
import com.denis_telezhenko.universityhelper.ui.dialog.BaseDatePicker;
import com.denis_telezhenko.universityhelper.ui.dialog.BaseTimePicker;
import com.denis_telezhenko.universityhelper.ui.dialog.DatePickerCreateNoteFragment;
import com.denis_telezhenko.universityhelper.ui.dialog.DatePickerEditNoteFragment;
import com.denis_telezhenko.universityhelper.ui.dialog.OnPickerCompleteListener;
import com.denis_telezhenko.universityhelper.ui.dialog.TimeDialogEditNoteFragment;
import com.denis_telezhenko.universityhelper.ui.dialog.TimePickerCreateNoteFragment;
import com.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.denis_telezhenko.universityhelper.ui.utils.TimeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNoteActivity extends AppCompatActivity implements OnPickerCompleteListener {
    public static final String RESULT = "result";

    private TextView date;
    private TextView time;
    private EditText title;
    private EditText desc;
    private CardView cardView;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Toolbar toolbar = findViewById(R.id.create_note__toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        if (getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE).getBoolean(Constants.IS_ADMIN, false)) {
            findViewById(R.id.create_note__asAdmin_cart).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.create_note__asAdmin_cart).setVisibility(View.GONE);
        }

        title = findViewById(R.id.new_note__title);
        desc = findViewById(R.id.new_note__desc);
        cardView = findViewById(R.id.new_note__alarm_clock_settings);

        date = findViewById(R.id.new_note__choose_date);
        time = findViewById(R.id.new_note__choose_time);

        RadioGroup radioGroup = findViewById(R.id.new_note__alarm);
        radioGroup.setOnCheckedChangeListener(this::changeAlarmState);

        time.setOnClickListener(this::openTimeDialog);
        date.setOnClickListener(this::openDateDialog);

        reference = FirebaseDatabase.getInstance().getReference();
        Button editButton = findViewById(R.id.new_note__create_button);
        editButton.setOnClickListener(this::saveButton);
    }

    private void openDateDialog(View view) {
        BaseDatePicker datePicker = new DatePickerCreateNoteFragment();
        datePicker.onAttach(getApplicationContext());
        datePicker.show(getSupportFragmentManager(), DatePickerEditNoteFragment.TAG);
    }

    private void openTimeDialog(View view) {
        BaseTimePicker timePicker = new TimePickerCreateNoteFragment();
        timePicker.onAttach(getApplicationContext());
        timePicker.show(getSupportFragmentManager(), TimeDialogEditNoteFragment.TAG);
    }

    private void saveButton(View view) {
        Note note = new Note();
        note.setTitle(title.getText().toString().trim());
        note.setDescription(desc.getText().toString().trim());
        note.setId(System.currentTimeMillis()/100);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CheckBox checkBox = findViewById(R.id.create_note__asAdmin);
        if (getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE).getBoolean(Constants.IS_ADMIN, false)
                && checkBox.isChecked()) {
            String group = getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE).getString(Constants.GROUP, "waste");
            note.setGlobal(true);
            reference.child(Constants.GLOBAL_NOTES).child(group).push().setValue(note);
        } else {
            reference.child(Constants.NOTES_NODE).child(uid).push().
                    setValue(note);
        }
        finish();
    }

    private void changeAlarmState(RadioGroup radioGroup, int i) {
        if (i == R.id.new_note__alarm_on) {
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void pickerComplete(Bundle data) {
        switch (data.getInt(RESULT)) {
            case DatePickerEditNoteFragment.DATE_REQUEST_CODE:
                int year = data.getInt(DatePickerEditNoteFragment.YEAR_SELECTED, -1);
                int month = data.getInt(DatePickerEditNoteFragment.MONTH_SELECTED, -1);
                int day = data.getInt(DatePickerEditNoteFragment.DAY_SELECTED, -1);
                String formattedDate = TimeUtils.getDateInString(year, month, day);
                date.setText(formattedDate);
                break;
            case TimeDialogEditNoteFragment.TIME_REQUEST_CODE:
                int hour = data.getInt(TimeDialogEditNoteFragment.HOUR_SELECTED, -1);
                int minutes = data.getInt(TimeDialogEditNoteFragment.MINUTE_SELECTED, -1);
                String formattedTime = TimeUtils.getTimeInString(hour, minutes);
                time.setText(formattedTime);
                break;
        }
    }
}
