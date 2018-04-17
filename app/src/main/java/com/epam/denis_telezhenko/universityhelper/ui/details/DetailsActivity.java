package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.epam.denis_telezhenko.universityhelper.R;

public class DetailsActivity extends AppCompatActivity {
    public static final String NOTE_ID_TAG = "note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.create_note__toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        if (savedInstanceState == null) {
            long id = getIntent().getLongExtra(NOTE_ID_TAG, 0);
            setDetailsFragment(id);
        }
    }

    private void setDetailsFragment(long id) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(DetailsFragment.TAG);
        if (fragment == null){
            fragment = DetailsFragment.newInstance(id);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.details_container, fragment, DetailsFragment.TAG)
                .commit();
    }
}
