package com.epam.denis_telezhenko.universityhelper.ui.login.view;


import com.epam.denis_telezhenko.universityhelper.core.entity.User;

public interface LoginView {

    void startMainActivity();

    void showFailedToast(String message);

    void saveUser(User user);
}
