package com.epam.denis_telezhenko.universityhelper.ui.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.denis_telezhenko.universityhelper.R;

public class EditNoteFragment extends Fragment {

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(String param1, String param2) {
        return new EditNoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }
}
