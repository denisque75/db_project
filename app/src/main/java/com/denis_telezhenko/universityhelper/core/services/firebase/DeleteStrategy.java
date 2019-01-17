package com.denis_telezhenko.universityhelper.core.services.firebase;

import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;

public interface DeleteStrategy {

    void deleteNote(DatabaseReference database, String uid, long noteId, MutableLiveData<Boolean> isDeleted);
}
