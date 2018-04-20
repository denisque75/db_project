package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.ui.details.DetailsFragment;
import com.epam.denis_telezhenko.universityhelper.ui.details.EditNoteFragment;

public class ScheduleActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private FloatingActionButton floatingBut;
    private TextView entranceTextView;

    private void findID(){
        floatingBut = findViewById(R.id.schedule_floating_button);
        entranceTextView = findViewById(R.id.schedule_group_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        findID();

        Toolbar toolbar = findViewById(R.id.schedule_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }
        setScheduleFragment();

        floatingBut.setOnClickListener(v -> {
            ScheduleDialogActivity sda = new ScheduleDialogActivity(ScheduleActivity.this, entranceTextView);
            sda.show();
        });
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

    private void setScheduleFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
    }

}
