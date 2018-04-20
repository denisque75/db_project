package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;

import java.util.ArrayList;

public class ScheduleDialogActivity extends Dialog implements View.OnClickListener {

    private Button okButton;
    private Spinner groupSpinner;

    private String group;

    private TextView textView;

    ScheduleDialogActivity(@NonNull Context context, TextView textView) {
        super(context);
        this.textView = textView;
    }

    private String getGroup() {
        return group;
    }

    private void setGroup(String group) {
        this.group = group;
    }

    private void findID(){
        okButton = findViewById(R.id.schedule_dialog_button);
        groupSpinner = findViewById(R.id.schedule_dialog_spinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_dialog);
        findID();
        okButton.setOnClickListener(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("КУ-31");
        list.add("КБ-31");
        list.add("КС-31");

        spinnerWork(list);
    }

    private void spinnerWork(ArrayList<String> groups){
        if (groups != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            groupSpinner.setAdapter(adapter);

            groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setGroup(groups.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        textView.setText(getGroup());
        dismiss();
    }
}
