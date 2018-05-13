package com.epam.denis_telezhenko.universityhelper.ui.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.App;
import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.ui.create_note.CreateNoteActivity;
import com.epam.denis_telezhenko.universityhelper.ui.details.view.DetailsActivity;
import com.epam.denis_telezhenko.universityhelper.ui.main.MainPresenter;
import com.epam.denis_telezhenko.universityhelper.ui.main.adapter.EventsRecyclerViewAdapter;
import com.epam.denis_telezhenko.universityhelper.ui.schedule.ScheduleActivity;
import com.epam.denis_telezhenko.universityhelper.ui.schedule_of_bells.BellsScheduleActivity;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainListView,
        NavigationView.OnNavigationItemSelectedListener,
        EventsRecyclerViewAdapter.OnClickItem {

    private static final String TAG = "MainActivity.TAG";

    private MainPresenter presenter;

    private EventsRecyclerViewAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        setFABListener(fab);

        setDrawerAndToggle(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        presenter = new MainPresenter(this, database, ((App)getApplication()).getDatabase().getNoteDao());
        presenter.getIsDataSavedToDb().observe(this, this::dataIsSaved);

        String group = getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE).getString(Constants.GROUP, "");
        presenter.setDatabaseEventListener(uid, group);
        setRecyclerView();
    }

    private void dataIsSaved(Boolean isSaved) {
        if (isSaved != null) {
            presenter.showNotes();
        }
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_main__recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new EventsRecyclerViewAdapter(null, this);
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void setEntitiesToAdapter(List<Note> noteList) {
        noteAdapter.setNoteEntities(noteList);
    }

    private void setDrawerAndToggle(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView nav = findViewById(R.id.nav_view);
        TextView groupName = nav.getHeaderView(0).findViewById(R.id.icon_group);
        groupName.setText(getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE)
                .getString(Constants.GROUP, "КУ-31"));
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        } else if (id == R.id.nav__logout) {
            logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        LogoutDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    public void clickItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.NOTE_ID_TAG, id);
        startActivity(intent);
    }

    public void setFABListener(FloatingActionButton fab) {
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        });
    }
}
