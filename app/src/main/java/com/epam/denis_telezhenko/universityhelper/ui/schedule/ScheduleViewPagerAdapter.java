package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.ui.utils.DateUtils;

public class ScheduleViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numbers;

    public ScheduleViewPagerAdapter(FragmentManager fm, int numbers) {
        super(fm);
        this.numbers = numbers;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(new DateUtils(position).getResultDate());
    }

    @Override
    public int getCount() {
        return numbers;
    }



}
