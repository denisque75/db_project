package com.epam.denis_telezhenko.universityhelper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText login;
    private TextInputEditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        }

        findID();

        signIn.setOnClickListener(this);
    }

    private void findID(){
        login = findViewById(R.id.login_log_text);
        password = findViewById(R.id.login_password_text);
        signIn = findViewById(R.id.login_button);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            //make validation
            finish();
        }

    }
}
