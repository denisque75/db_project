package com.epam.denis_telezhenko.universityhelper.ui.login;

import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.auth.AuthCallback;
import com.epam.denis_telezhenko.universityhelper.core.auth.Authorization;
import com.epam.denis_telezhenko.universityhelper.core.entity.User;
import com.epam.denis_telezhenko.universityhelper.core.services.auth.AuthService;
import com.epam.denis_telezhenko.universityhelper.ui.BasePresenter;
import com.epam.denis_telezhenko.universityhelper.core.auth.CheckAuth;
import com.epam.denis_telezhenko.universityhelper.ui.login.view.LoginView;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenter extends BasePresenter<LoginView> implements AuthCallback,
        CheckAuth, Authorization {
    private static final String TAG = "LoginPresenter";
    private FirebaseAuth auth;
    private DatabaseReference reference;

    public LoginPresenter(LoginView loginView, FirebaseAuth auth, DatabaseReference reference) {
        super(loginView);
        this.auth = auth;
        this.reference = reference;
    }


    @Override
    public boolean clientIsAuthAlready() {
        return auth.getCurrentUser() != null;
    }

    @Override
    public void signIn(String email, String pass) {
        // TODO: 12.05.18   check Internet access
        AuthService.signInUser(auth, this, email, pass);
    }

    @Override
    public void failAuth() {
        getView().showFailedToast("Authentication failed");
    }

    @Override
    public void successAuth() {
        reference.child(Constants.USERS_NODE).child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, "onDataChange: user = " + user);
                user.setAdmin((Boolean) dataSnapshot.child("isAdmin").getValue());
                getView().saveUser(user);
                getView().startMainActivity();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }
}
