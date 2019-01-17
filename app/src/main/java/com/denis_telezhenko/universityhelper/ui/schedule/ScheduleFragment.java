package com.denis_telezhenko.universityhelper.ui.schedule;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.denis_telezhenko.universityhelper.ui.utils.Days;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private View view;
    private TextView dateTextView;
    private String mDate;
    private final static String TAG = "POS";
    private final static String SCHEDULE = "Schedule";

    private ScheduleDbPresenter presenter;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance(String date, List<Data> data) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(TAG, date);
        args.putParcelableArrayList(SCHEDULE, (ArrayList<? extends Parcelable>) data);
        fragment.setArguments(args);
        return fragment;
    }

    private void findID(){
        dateTextView = view.findViewById(R.id.schedule_date_text_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        findID();

        List<Data> data = getArguments().getParcelableArrayList(SCHEDULE);
        for (Data sched : data) {
            if (getArguments().getString(TAG).contains(sched.getDayName())) {
                setSchedule(sched);
            }
        }

        setDate();
        return view;
    }

    private String getDateName(String key) {
        for (Days day : Days.values()) {
            if (key.contains(day.toString())) {
                return day.toString();
            }
        }
        return "Воскресенье";
    }

    private void setDate(){
        String date = getArguments().getString(TAG);
        mDate = date;
        dateTextView.setText(date);
    }

//    private int getCurrentDay(){
//        return 1;
//    }

    private void setSchedule(Data dataList){
        ExpandableListView scheduleList = view.findViewById(R.id.schedule_list);
        scheduleList.setAdapter(new ExpScheduleAdapter(getContext(), dataList));
    }

/*private void test(){
        ArrayList<Data> firstData = new ArrayList<>();
        ArrayList<ArrayList<Data>> secondData = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            Data data = new Data(
                    "Пара " + Integer.toString(i)
                    , "Препод  " + Integer.toString(i)
                    , "Время " + Integer.toString(i)
                    , "Аудитория " + Integer.toString(i)
                    , i
                    , "ПЗ"
                    );
            ArrayList<Data> arrayList = new ArrayList<>();
            arrayList.add(data);
            secondData.add(arrayList);
            firstData.add(data);
        }

        Data data1 = new Data(
                "Пара " + Integer.toString(5)
                , "Препод  " + Integer.toString(5)
                , "Время " + Integer.toString(5)
                , "Аудитория " + Integer.toString(5)
                , 5
        );
        Data data2 = new Data(
                "Пара " + Integer.toString(6)
                , "Препод  " + Integer.toString(6)
                , "Время " + Integer.toString(6)
                , "Аудитория " + Integer.toString(6)
                , 6
        );
        Data data3 = new Data(
                "Пара " + Integer.toString(7)
                , "Препод  " + Integer.toString(7)
                , "Время " + Integer.toString(7)
                , "Аудитория " + Integer.toString(7)
                , 7
        );
        ArrayList<Data> arrayList = new ArrayList<>();
        arrayList.add(data1);
        arrayList.add(data2);
        arrayList.add(data3);
        secondData.add(arrayList);
        firstData.add(new Data("Межфакультетская лекция", "ЛЗ", 5));

        setSchedule(firstData, secondData);
    }*/
}
