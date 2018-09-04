package com.denis_telezhenko.universityhelper.core.services.auth;

import android.util.Log;

import com.denis_telezhenko.universityhelper.core.auth.AuthCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthService {

    private static final String TAG = "AuthService";

    public static void signInUser(FirebaseAuth auth, AuthCallback authCallback,
                                  String email, String pass){
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                        authCallback.successAuth();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, "signInWithEmail:failure", task.getException());
                        authCallback.failAuth();
                    }

                });
    }
}
