package com.epam.denis_telezhenko.universityhelper.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.entity.Note;
import com.epam.denis_telezhenko.universityhelper.ui.create_note.CreateNoteActivity;
import com.epam.denis_telezhenko.universityhelper.ui.details.DetailsActivity;
import com.epam.denis_telezhenko.universityhelper.ui.login.LoginActivity;
import com.epam.denis_telezhenko.universityhelper.ui.main.adapter.EventsRecyclerViewAdapter;
import com.epam.denis_telezhenko.universityhelper.ui.schedule.ScheduleActivity;
import com.epam.denis_telezhenko.universityhelper.ui.schedule_of_bells.BellsScheduleActivity;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EventsRecyclerViewAdapter.OnClickItem {

    private static final String TAG = "MainActivity.TAG";
    private DatabaseReference database;
    private String uid;
    private EventsRecyclerViewAdapter noteAdapter;

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
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });

        setDrawerAndToggle(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference();

        setDatabaseEventListener();
        setRecyclerView();
    }

    private void setDatabaseEventListener() {
        ValueEventListener noteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Note> noteList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d("FirebaseNote", data.toString());
                    noteList.add(data.getValue(Note.class));
                }
                noteAdapter.setNoteEntities(noteList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        database.child(Constants.NOTES_NODE).child(uid).addValueEventListener(noteListener);
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_main__recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new EventsRecyclerViewAdapter(null, this);
        recyclerView.setAdapter(noteAdapter);
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

}
