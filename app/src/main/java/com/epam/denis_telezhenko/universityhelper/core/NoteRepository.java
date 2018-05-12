package com.epam.denis_telezhenko.universityhelper.core;

import android.arch.lifecycle.LiveData;

import com.epam.denis_telezhenko.universityhelper.core.entity.Note;

import java.util.List;

public interface NoteRepository {

    List<Note> readNotes();

    Note readNoteById(long id);

    void writeNotes(List<Note> notes);

    void writeNote(Note note);
}
