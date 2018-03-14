package com.epam.denis_telezhenko.universityhelper.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.ui.StubUtils;
import com.epam.denis_telezhenko.universityhelper.ui.create_note.CreateNoteActivity;
import com.epam.denis_telezhenko.universityhelper.ui.details.DetailsActivity;
import com.epam.denis_telezhenko.universityhelper.ui.login.LoginActivity;
import com.epam.denis_telezhenko.universityhelper.ui.main.adapter.EventsRecyclerViewAdapter;
import com.epam.denis_telezhenko.universityhelper.ui.schedule.ScheduleActivity;
import com.epam.denis_telezhenko.universityhelper.ui.schedule_of_bells.BellsScheduleActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EventsRecyclerViewAdapter.OnClickItem {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setDrawerAndToggle(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_main__recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventsRecyclerViewAdapter(StubUtils.getNotes(), this));
    }

    private void setDrawerAndToggle(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav__schedule) {
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav__bells_schedule) {
            Intent intent = new Intent(this, BellsScheduleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav__note) {
            Intent intent = new Intent(this, CreateNoteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav__login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void clickItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.NOTE_ID_TAG, id);
        startActivity(intent);
    }

}
