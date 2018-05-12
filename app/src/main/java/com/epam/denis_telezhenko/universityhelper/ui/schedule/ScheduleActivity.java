package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;

import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {

    private FloatingActionButton selectGroup;
    private FloatingActionButton addSchedule;
    private TextView entranceTextView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private MenuItem mEdit;


    private final static int WEEK_COUNT = 7;
    private final static String TAG = "schedule_activity";
    private int count;

    private void findID(){
        toolbar = findViewById(R.id.schedule_toolbar);
        collapsingToolbar = findViewById(R.id.schedule_collapsing_toolbar);
        selectGroup = findViewById(R.id.schedule_floating_button);
        entranceTextView = findViewById(R.id.schedule_group_name);
        addSchedule = findViewById(R.id.add_schedule_floating_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        findID();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        selectGroup.setOnClickListener(v -> {
            ScheduleDialogActivity sda = new ScheduleDialogActivity(ScheduleActivity.this, entranceTextView);
            sda.show();
        });
        pageSwitcher(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_schadule_toolbar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mEdit = menu.findItem(R.id.schedule_edit);
        collapseMenuItems();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.schedule_edit)
            animOnClick();
        return super.onOptionsItemSelected(item);
    }

    private void collapseMenuItems(){
        AppBarLayout appBarLayout = findViewById(R.id.schedule_appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mEdit.setVisible(true);
                    isShow = true;
                } else if (isShow) {
                    mEdit.setVisible(false);
                    isShow = false;
                }
            }
        });
    }

    private void animOnClick(){
        View v1 = toolbar.findViewById(R.id.schedule_edit);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v1, "rotation", v1.getRotation() + 360);
        animator.start();
        switch (count) {
            case 1:
                collapsingToolbar.setTitle(getResources().getString(R.string.schedule_title));
                mEdit.setIcon(R.drawable.ic_edit_white_24dp);
                pageSwitcher(true);
                selectGroup.setVisibility(View.VISIBLE);
                addSchedule.hide();
                addSchedule.setVisibility(View.GONE);
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                count = 0;
                break;

            case 0:
                collapsingToolbar.setTitle(getResources().getString(R.string.edit_schedule_title));
                mEdit.setIcon(R.drawable.ic_done_24dp);
                pageSwitcher(false);
                selectGroup.setVisibility(View.GONE);
                addSchedule.show();
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                count++;
                break;
        }
    }

    private void pageSwitcher(boolean flag) {
        ViewPager viewPager = findViewById(R.id.schedule_viewpager);
        ScheduleViewPagerAdapter adapter = new ScheduleViewPagerAdapter(getSupportFragmentManager(), WEEK_COUNT, flag);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}
