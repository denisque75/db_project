package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.entity.NoteEntity;
import com.epam.denis_telezhenko.universityhelper.ui.StubUtils;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.Date;
import java.util.List;

public class DetailsFragment extends Fragment {
    private List<NoteEntity> noteEntities;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(long id) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(DetailsActivity.NOTE_ID_TAG, id);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        long id = getArguments().getLong(DetailsActivity.NOTE_ID_TAG, 0);
        noteEntities = StubUtils.getNotes();
        NoteEntity noteEntity = getNoteById(id);

        TextView titleText = rootView.findViewById(R.id.details__title);
        titleText.setText(noteEntity.getTitle());

        TextView descText = rootView.findViewById(R.id.details__description);
        descText.setText(noteEntity.getDescrition());

        TextView timeText = rootView.findViewById(R.id.details__time);
        timeText.setText(TimeUtils.getTimeInString(noteEntity.getDate()));

        return rootView;
    }

    private NoteEntity getNoteById(long id) {
        for (int i = 0; i < noteEntities.size(); i++) {
            if (noteEntities.get(i).getId() == id) {
                return noteEntities.get(i);
            }
        }
        return noteEntities.get(0);
    }
}
