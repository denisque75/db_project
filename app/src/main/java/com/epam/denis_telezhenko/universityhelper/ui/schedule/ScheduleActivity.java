package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.App;
import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.dao.ScheduleDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.epam.denis_telezhenko.universityhelper.ui.utils.DateUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleDialogActivity.OnGroupClicked,
        MenuItem.OnMenuItemClickListener {

    private FloatingActionButton floatingBut;
    private TextView entranceTextView;
    private final static int WEEK_COUNT = 7;

    private List<Data> dataList;

    private SchedulePresenter presenter;

    private void findID(){
        floatingBut = findViewById(R.id.schedule_floating_button);
        entranceTextView = findViewById(R.id.schedule_group_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        findID();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        ScheduleDao dao = ((App) getApplication()).getDatabase().getScheduleDao();

        presenter = new SchedulePresenter(database, dao);

        String groupName = getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE)
                .getString(Constants.GROUP, "КУ-31");
        presenter.returnScheduleByGroup(groupName);
        presenter.getLiveData().observe(this, this::saveToDB);
        presenter.getIsSaved().observe(this, this::openFragments);

        Toolbar toolbar = findViewById(R.id.schedule_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        floatingBut.setOnClickListener(v -> {
            ScheduleDialogActivity sda =
                    new ScheduleDialogActivity(ScheduleActivity.this, entranceTextView, this);
            sda.show();
        });

    }

    @Override
    public void onGroupClicked(String group) {
        presenter.returnScheduleByGroup(group);
    }

    private void openFragments(Boolean aBoolean) {
        if (aBoolean) {
            pageSwitcher();
        } else {
            Toast.makeText(getApplicationContext(), "See Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToDB(List<Data> data) {
        if (data != null) {
            dataList = data;
            presenter.saveDataAndShow(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_schadule_toolbar, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.menu.activity_schadule_toolbar){
            //edit
        }
        return super.onOptionsItemSelected(item);
    }

    private void pageSwitcher() {
        ViewPager viewPager = findViewById(R.id.schedule_viewpager);
        ScheduleViewPagerAdapter adapter = new ScheduleViewPagerAdapter(getSupportFragmentManager(), WEEK_COUNT, dataList);
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
