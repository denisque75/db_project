package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.epam.denis_telezhenko.universityhelper.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AddDialogActivity extends Dialog implements AdapterView.OnItemSelectedListener{

    public interface OnClickDialogListener {
        void setNewData(Data data);
    }

    private HashMap<String, ArrayList<String>> dbData;
    private Data data;
    public final static String TAG_ADD_DIALOG = "add_dialog_fragment";
    public final static String[] keys = {"TIME", "SUBJECT", "TEACHER", "ROOM"};
    private AddDialogActivity.OnClickDialogListener listener;

    public AddDialogActivity(@NonNull Context context, HashMap<String, ArrayList<String>> dbData) {
        super(context);
        this.dbData = dbData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_schedule_add_lesson);
        spinnerWork();
        radioButtonWork();
        buttonWork();
    }

    public void setClickDialogListener(AddDialogActivity.OnClickDialogListener listener){
        this.listener = listener;
    }

    private ArrayList<String> getList(int index){
        return dbData.get(keys[index]);
    }

    private void spinnerWork(){
        data = new Data();
        spinnerAssemble(getList(0), R.id.dialog_time_spinner).setOnItemSelectedListener(this);
        spinnerAssemble(getList(1), R.id.dialog_subject_spinner).setOnItemSelectedListener(this);
        spinnerAssemble(getList(2), R.id.dialog_teacher_spinner).setOnItemSelectedListener(this);
        spinnerAssemble(getList(3), R.id.dialog_room_spinner).setOnItemSelectedListener(this);
    }

    private Spinner spinnerAssemble(ArrayList<String> list, int id){
        Spinner spinner = /*view.*/findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.dialog_time_spinner:
                data.setTime(dbData.get(keys[0]).get(position));
                break;
            case R.id.dialog_subject_spinner:
                data.setSubject(dbData.get(keys[1]).get(position));
                break;
            case R.id.dialog_teacher_spinner:
                data.setTeacher(dbData.get(keys[2]).get(position));
                break;
            case R.id.dialog_room_spinner:
                data.setRoom(dbData.get(keys[3]).get(position));
                break;
        }
        listener.setNewData(data);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void radioButtonWork(){
        RadioGroup radioGroup = /*view.*/findViewById(R.id.dialog_radio_group);
        data.setType("ПЗ");
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (group.getCheckedRadioButtonId() == R.id.dialog_type1_radio_button){
                data.setType("ПЗ");
            }else {
                data.setType("ЛЗ");
            }
        });
    }

    private void buttonWork(){
        Button button = /*view.*/findViewById(R.id.dialog_add_button);
        button.setOnClickListener(v -> {
//            Toast.makeText(getContext(), data.getTime() + " " + data.getSubject() + " " + data.getTeacher() + " " + data.getType(), Toast.LENGTH_SHORT).show();
            listener.setNewData(data);
            dismiss();
        });
    }

}
