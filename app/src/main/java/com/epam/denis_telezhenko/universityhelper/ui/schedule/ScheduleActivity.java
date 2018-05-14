package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.animation.ObjectAnimator;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {

    public interface DialogTransaction{
        void setDialog(DialogFragment dialog);
    }

    private FloatingActionButton selectGroup;
    private FloatingActionButton addSchedule;
    private TextView entranceTextView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private MenuItem mEdit;

    private final static int WEEK_COUNT = 7;
    private final static String TAG = "schedule_activity";
    private int fragmentCount;
    private int stateChildFAB;
    private boolean adminFlag;
    private Data data;

    private void findID(){
        toolbar = findViewById(R.id.schedule_toolbar);
        collapsingToolbar = findViewById(R.id.schedule_collapsing_toolbar);
        selectGroup = findViewById(R.id.schedule_floating_button);
        entranceTextView = findViewById(R.id.schedule_group_name);
        addSchedule = findViewById(R.id.edit_schedule_floating_button);
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
        adminFlag = true;//admin account

        fabOnClick();

        pageSwitcher(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_schadule_toolbar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (adminFlag) {
            mEdit = menu.findItem(R.id.schedule_edit);
            collapseMenuItems();
        }
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
        switch (fragmentCount) {
            case 0:
                pageSwitcher(false);
                collapsingToolbar.setTitle(getResources().getString(R.string.edit_schedule_title));
                mEdit.setIcon(R.drawable.ic_done_24dp);
                selectGroup.setVisibility(View.GONE);
                addSchedule.show();
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                fragmentCount++;
                break;

            case 1:
                setStateChildFAB(2);
                pageSwitcher(true);
                collapsingToolbar.setTitle(getResources().getString(R.string.schedule_title));
                mEdit.setIcon(R.drawable.ic_edit_white_24dp);
                selectGroup.setVisibility(View.VISIBLE);
                addSchedule.hide();
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                fragmentCount = 0;
                addSchedule.setVisibility(View.GONE);
                break;
        }
    }

    private void fabOnClick(){
        if (selectGroup != null) {
            selectGroup.setOnClickListener(v -> {
                GroupDialogActivity sda = new GroupDialogActivity(ScheduleActivity.this, entranceTextView);
                sda.show();
            });
        }
        if (addSchedule != null){
            addSchedule.setOnClickListener(v -> setStateChildFAB(stateChildFAB));

            FloatingActionButton fab1 = findViewById(R.id.schedule_add_floating_action_button);
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MessageEvent(1));
                }
            });

            FloatingActionButton fab2 = findViewById(R.id.schedule_edit_floating_action_button);
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MessageEvent(2));
                }
            });

            FloatingActionButton fab3 = findViewById(R.id.schedule_delete_floating_action_button);
            fab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MessageEvent(3));
                }
            });

        }

    }
    //тест
    private HashMap<String, ArrayList<String>> test(){
        HashMap<String, ArrayList<String>> dbData = new HashMap<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();
        ArrayList<String> arrayList4 = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            arrayList1.add("Пара " + Integer.toString(i));
            arrayList2.add("Предмет " + Integer.toString(i));
            arrayList3.add("Препод " + Integer.toString(i));
            arrayList4.add("Аудитория" + Integer.toString(i));
        }
        dbData.put(AddDialogActivity.keys[0], arrayList1);
        dbData.put(AddDialogActivity.keys[1], arrayList2);
        dbData.put(AddDialogActivity.keys[2], arrayList3);
        dbData.put(AddDialogActivity.keys[3], arrayList4);
        return dbData;
    }

    private void setStateChildFAB(int stateChildFAB){
        FloatingActionButton fab2 = findViewById(R.id.schedule_add_floating_action_button);
        FloatingActionButton fab3 = findViewById(R.id.schedule_edit_floating_action_button);
        FloatingActionButton fab1 = findViewById(R.id.schedule_delete_floating_action_button);

        if (stateChildFAB == 0) {
            customShowFab(fab1, 2.0, 0.25);
            customShowFab(fab2, 0.25, 2.0);
            customShowFab(fab3, 1.5, 1.5);
            stateChildFAB++;
        }
        else {
            customHideFab(fab1);
            customHideFab(fab2);
            customHideFab(fab3);
            stateChildFAB = 0;
        }
        this.stateChildFAB = stateChildFAB;
    }

    private void customShowFab(FloatingActionButton fab, double x, double y){
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin = (int) (fab.getWidth() * x);
        layoutParams.bottomMargin = (int) (fab.getHeight() * y);
        fab.setLayoutParams(layoutParams);
        Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        fab.startAnimation(animation);
        fab.setClickable(true);
    }

    private void customHideFab(FloatingActionButton fab){
        if (fab.isClickable()){
            Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);
            fab.startAnimation(animation);
            fab.setClickable(false);
        }
    }

    private void pageSwitcher(boolean flag) {
        if (data != null){
            Log.v("TAG", "OK");
        }
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
