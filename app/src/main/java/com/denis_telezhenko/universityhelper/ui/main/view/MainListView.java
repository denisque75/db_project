package com.denis_telezhenko.universityhelper.ui.main.view;

import com.denis_telezhenko.universityhelper.core.entity.Note;

import java.util.List;

public interface MainListView {

    void setEntitiesToAdapter(List<Note> noteList);
}
