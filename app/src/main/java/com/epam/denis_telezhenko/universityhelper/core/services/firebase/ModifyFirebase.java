package com.epam.denis_telezhenko.universityhelper.core.services.firebase;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModifyFirebase {
    private static final String TAG = "ModifyFirebase";

    public static void deleteNote(String uid, long noteId, DatabaseReference database,
                                  MutableLiveData<Boolean> isDeleted) {
        Query remove = database
                .child(Constants.NOTES_NODE)
                .child(uid)
                .orderByChild("id").equalTo(noteId);
        Log.d(TAG, "deleteNote: ");
        remove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot idSnapshot: dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: idSnapshot " + idSnapshot);
                    idSnapshot.getRef().removeValue();
                }
                isDeleted.setValue(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
                isDeleted.setValue(false);
            }
        });
    }

    public static void modifyNote(Note note, String uid, DatabaseReference database) {
        Query remove = database
                .child(Constants.NOTES_NODE)
                .child(uid)
                .orderByChild("id").equalTo(note.getId());
        remove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot idSnapshot: dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: idSnapshot " + idSnapshot);
                    idSnapshot.getRef().child("title").setValue(note.getTitle());
                    idSnapshot.getRef().child("description").setValue(note.getDescription());
                    idSnapshot.getRef().child("date").setValue(note.getDate());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
}
