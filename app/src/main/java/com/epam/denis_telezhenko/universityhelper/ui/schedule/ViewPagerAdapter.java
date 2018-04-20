package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numbers;

    public ViewPagerAdapter(FragmentManager fm, int numbers) {
        super(fm);
        this.numbers = numbers;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return numbers;
    }
}
