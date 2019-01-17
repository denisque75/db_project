package com.denis_telezhenko.universityhelper.ui.schedule_of_bells;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis_telezhenko.universityhelper.R;

public class BellsScheduleFragment extends Fragment {

    public BellsScheduleFragment() {
        // Required empty public constructor
    }

    public static BellsScheduleFragment newInstance(String param1, String param2) {
        return new BellsScheduleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bells_schedule, container, false);
    }


}
