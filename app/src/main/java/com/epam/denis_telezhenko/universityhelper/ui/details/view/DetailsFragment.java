package com.epam.denis_telezhenko.universityhelper.ui.details.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.denis_telezhenko.universityhelper.App;
import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.dao.NoteDao;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.services.firebase.Factory;
import com.epam.denis_telezhenko.universityhelper.ui.StubUtils;
import com.epam.denis_telezhenko.universityhelper.ui.details.DetailsPresenter;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DetailsFragment extends Fragment implements DetailsView {
    public static final String TAG = "details_fragment";
    private long id;
    private boolean isGlobal = false;

    //if user delete note, the system must delete it from server and local db, and after finish
    //current activity
    private int deleteCounter;
    private final String keyCounter = "counter";

    private TextView titleText;
    private TextView descText;
    private TextView timeText;

    private Button editButton;
    private Button removeButton;

    private DetailsPresenter presenter;

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

        if (savedInstanceState != null) {
            deleteCounter = savedInstanceState.getInt(keyCounter);
        } else {
            deleteCounter = 0;
        }

        NoteDao dao = ((App)getActivity().getApplication()).getDatabase().getNoteDao();
        presenter = new DetailsPresenter(this, dao, FirebaseDatabase.getInstance().getReference());

        id = getArguments().getLong(DetailsActivity.NOTE_ID_TAG, 0);
        presenter.showNoteById(id);
        presenter.getIsDeleted().observe(this, this::deletedNote);

        titleText = rootView.findViewById(R.id.details__title);
        descText = rootView.findViewById(R.id.details__description);timeText = rootView.findViewById(R.id.details__time);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(keyCounter, deleteCounter);
    }

    private void deletedNote(Boolean isDeleted) {
        if (isDeleted != null && isDeleted) {
            deleteCounter++;
            if (deleteCounter > 1) {
                Toast.makeText(getContext(), "Все изменения были сохранены", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        } else {
            Toast.makeText(getActivity(), "Something Went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showNote(Note note) {
        boolean isAdmin = getActivity().getSharedPreferences(Constants.USER_SHARED, Context.MODE_PRIVATE)
                .getBoolean(Constants.IS_ADMIN, false);
        if (note.isGlobal()) {
            isGlobal = true;
            if (!isAdmin) {
                editButton.setEnabled(false);
                removeButton.setEnabled(false);
            }
        }
        titleText.setText(note.getTitle());
        descText.setText(note.getDescription());
        if (note.getDate() == null && getView() != null) {
            getView().findViewById(R.id.details__card_time).setVisibility(View.GONE);
        } else {
            timeText.setText(TimeUtils.getTimeInString(note.getDate()));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        editButton = view.findViewById(R.id.details__edit);
        editButton.setOnClickListener(this::editButton);
        removeButton = view.findViewById(R.id.details__remove);
        removeButton.setOnClickListener(this::removeNote);
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

    private void removeNote(View view) {
        if (isGlobal) {
            String group = getActivity().getSharedPreferences(Constants.USER_SHARED, Context.MODE_PRIVATE).getString(Constants.GROUP, "");
            presenter.deleteNote(group, id, Factory.DELETE_AS_ADMIN);
        } else {
            presenter.deleteNote(FirebaseAuth.getInstance().getCurrentUser().getUid(), id, Factory.DELETE_AS_USER);
        }
    }
}
