package com.epam.denis_telezhenko.universityhelper.core.services.firebase;

import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.firebase_callbacks.DataFromDBCallback;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadFromFirebase {

    private static final String TAG = "ReadFromFirebase";

    public static void readNotes(DataFromDBCallback<Note> callback,
                                 DatabaseReference database, String uid) {
        ValueEventListener noteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Note> noteList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d("FirebaseNote", data.toString());
                    noteList.add(data.getValue(Note.class));
                }
                callback.newDataFromDB(noteList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        database.child(Constants.NOTES_NODE).child(uid).addValueEventListener(noteListener);
    }

}
