package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.epam.denis_telezhenko.universityhelper.ui.utils.DateUtils;

import java.util.List;

public class ScheduleViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numbers;
    private List<Data> dataList;

    public ScheduleViewPagerAdapter(FragmentManager fm, int numbers, List<Data> data) {
        super(fm);
        this.numbers = numbers;
        dataList = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(new DateUtils(position).getResultDate(), dataList);
    }

    @Override
    public int getCount() {
        return numbers;
    }



}
