package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;

public class EditScheduleFragment extends Fragment {

    public final static  String TAG = "edit_schedule_fragment";
    private View view;
    private TextView dateTextView;

    public EditScheduleFragment() {
        // Required empty public constructor
    }

    private void findID(){
        dateTextView = view.findViewById(R.id.edit_schedule_date_text_view);
    }

    public static EditScheduleFragment newInstance(String date) {
        EditScheduleFragment fragment = new EditScheduleFragment();
        Bundle args = new Bundle();
        args.putString(TAG, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_schedule, container, false);

        findID();

        dateTextView.setText(getArguments().getString(TAG));

        return view;
    }
}
