package com.epam.denis_telezhenko.universityhelper.ui;

public class BasePresenter<View> {
    private View view;

    public BasePresenter(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
