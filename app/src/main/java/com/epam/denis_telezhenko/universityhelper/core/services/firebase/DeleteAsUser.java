package com.epam.denis_telezhenko.universityhelper.core.services.firebase;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeleteAsUser implements DeleteStrategy {

    private static final String TAG = "DELETE.AS.USER";

    @Override
    public void deleteNote(DatabaseReference database, String uid, long noteId, MutableLiveData<Boolean> isDeleted) {
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
}