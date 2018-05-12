package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.ui.StubUtils;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.List;

public class DetailsFragment extends Fragment {
    public static final String TAG = "details_fragment";
    private List<Note> noteEntities;
    private long id;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(long id) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putLong(DetailsActivity.NOTE_ID_TAG, id);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        id = getArguments().getLong(DetailsActivity.NOTE_ID_TAG, 0);
        //TODO: noteEntities save in MainActivity to DB and then read from DB here by id;
        noteEntities = StubUtils.getNotes();
        Note note = getNoteById();

        TextView titleText = rootView.findViewById(R.id.details__title);
        titleText.setText(note.getTitle());

        TextView descText = rootView.findViewById(R.id.details__description);
        descText.setText(note.getDescription());

        TextView timeText = rootView.findViewById(R.id.details__time);
        timeText.setText(TimeUtils.getTimeInString(note.getDate()));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button editButton = view.findViewById(R.id.details__edit);
        editButton.setOnClickListener(this::editButton);
    }

    private void editButton(View view) {
        Fragment fragment = getFragmentManager().findFragmentByTag(EditNoteFragment.TAG);
        if (fragment == null){
            fragment = EditNoteFragment.newInstance(id);
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.details_container, fragment, EditNoteFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    private Note getNoteById() {
        for (int i = 0; i < noteEntities.size(); i++) {
            if (noteEntities.get(i).getId() == id) {
                return noteEntities.get(i);
            }
        }
        return noteEntities.get(0);
    }
}
