package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EditScheduleFragment extends Fragment implements AddDialogActivity.OnClickDialogListener,DialogInterface.OnDismissListener{

    public final static  String TAG_DATE = "edit_schedule_fragment";
    public static  String TAG_POSITION= "edit_schedule_fragment_position";
    private View view;
    private TextView dateTextView;
    private Data data;
    private AddDialogActivity add;

    public EditScheduleFragment() {
        // Required empty public constructor
    }

    private void findID(){
        dateTextView = view.findViewById(R.id.edit_schedule_date_text_view);
    }

    private HashMap<String, ArrayList<String>> test(){
        HashMap<String, ArrayList<String>> dbData = new HashMap<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();
        ArrayList<String> arrayList4 = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            arrayList1.add("Пара " + Integer.toString(i));
            arrayList2.add("Предмет " + Integer.toString(i));
            arrayList3.add("Препод " + Integer.toString(i));
            arrayList4.add("Аудитория" + Integer.toString(i));
        }
        dbData.put(AddDialogActivity.keys[0], arrayList1);
        dbData.put(AddDialogActivity.keys[1], arrayList2);
        dbData.put(AddDialogActivity.keys[2], arrayList3);
        dbData.put(AddDialogActivity.keys[3], arrayList4);
        return dbData;
    }

    public static EditScheduleFragment newInstance(String date, int position) {
        EditScheduleFragment fragment = new EditScheduleFragment();
        Bundle args = new Bundle();
        args.putString(TAG_DATE, date);
        args.putInt(TAG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        findID();

        dateTextView.setText(getArguments().getString(TAG_DATE));

        add = new AddDialogActivity(getContext(), test());
        add.setClickDialogListener(this);
        add.setOnDismissListener(this);

        return view;
    }

    @Override
    public void setNewData(Data data) {
        this.data = data;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // отписываемся от регистрации при закрытии фрагмента
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(MessageEvent event){
        if (event.message == 1){
            add.show();
        }
        if (event.message == 2){
            Toast.makeText(getContext(), "edit", Toast.LENGTH_SHORT).show();
//            add.show();
        }
        if (event.message == 3){
            Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();
            CheckBox checkBox = view.findViewById(R.id.edit_delete_box);
            checkBox.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.edit_box_delete);
            checkBox.startAnimation(animation);

//            add.show();
        }
        event.setMessage(0);
    }

}
