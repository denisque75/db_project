package com.epam.denis_telezhenko.universityhelper.core.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.epam.denis_telezhenko.universityhelper.core.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotes(List<Note> noteList);

    @Query("DELETE FROM Note")
    void clearTable();

    @Query("SELECT * FROM Note")
    List<Note> readNotes();

    @Query("SELECT * FROM Note WHERE :id LIKE Note.id")
    Note readNoteById(long id);

    @Query("DELETE FROM Note WHERE :id LIKE Note.id")
    void deleteNote(long id);
}
