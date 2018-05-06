package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.ui.utils.DateUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private View view;
    private TextView dateTextView;
    private static int mPosition;
    private final static String TAG = "POS";

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance(int position) {
        mPosition = position;
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, position);
        fragment.setArguments(args);
        return fragment;
    }

    private void findID(){
        dateTextView = view.findViewById(R.id.schedule_date_text_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        GlobalBus.getBus().register(this);
        findID();
        test();
//        dateTextView.setText(new DateUtils(mPosition).getResultDate());
        return view;
    }

    private void setSchedule(ArrayList<Data> parentGroup, ArrayList<ArrayList<Data>> childGroup){
        ExpandableListView scheduleList = view.findViewById(R.id.schedule_list);
        if (parentGroup != null) {
            scheduleList.setAdapter(new ExpScheduleAdapter(getContext(), parentGroup, childGroup));
        }
    }

    @Override
    public void onDestroy() {
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        if (event.getPosition() == mPosition){
//            String day = new DateUtils(mPosition).getResultDate();
            dateTextView.setText(event.getMessage());
        }
    }

    private void test(){
        ArrayList<Data> firstData = new ArrayList<>();
        ArrayList<ArrayList<Data>> secondData = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            Data data = new Data(
                    "Пара " + Integer.toString(i)
                    , "Препод  " + Integer.toString(i)
                    , "Время " + Integer.toString(i)
                    , "Аудитория " + Integer.toString(i)
                    , i
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
        firstData.add(new Data("Межфакультетская лекция", 5));

        setSchedule(firstData, secondData);



        }

}
