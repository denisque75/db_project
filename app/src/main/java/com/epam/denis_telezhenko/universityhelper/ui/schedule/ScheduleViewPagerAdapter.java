package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.ui.utils.DateUtils;

public class ScheduleViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numbers;
    private boolean flag;

    public ScheduleViewPagerAdapter(FragmentManager fm, int numbers, boolean flag) {
        super(fm);
        this.numbers = numbers;
        this.flag = flag;;
    }

    @Override
    public Fragment getItem(int position) {
        if (flag)
            return ScheduleFragment.newInstance(new DateUtils(position).getResultDate());
        else
            return EditScheduleFragment.newInstance(new DateUtils(position).getResultDate(), position);
    }

    @Override
    public int getCount() {
        return numbers;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
