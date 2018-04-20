package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import com.epam.denis_telezhenko.universityhelper.R;

public class ScheduleDialogActivity extends Dialog implements View.OnClickListener {

    public ScheduleDialogActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.schedule_dialog);
    }

    @Override
    public void onClick(View v) {

    }
}
