package com.epam.denis_telezhenko.universityhelper.ui.login;

import com.epam.denis_telezhenko.universityhelper.ui.CheckAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginPresenter implements CheckAuth {
    private FirebaseAuth auth;
    private DatabaseReference reference;

    public LoginPresenter(FirebaseAuth auth, DatabaseReference reference) {
        this.auth = auth;
        this.reference = reference;

    }


    @Override
    public boolean clientIsAuthAlready() {
        if (auth.getCurrentUser() != null) {
            return true;
        }
        return false;
    }
}
