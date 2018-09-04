package com.denis_telezhenko.universityhelper.ui.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.core.entity.User;
import com.denis_telezhenko.universityhelper.ui.login.LoginPresenter;
import com.denis_telezhenko.universityhelper.ui.main.view.MainActivity;
import com.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{

    private static final String TAG = "LOGIN.ACTIVITY";
    private TextInputEditText login;
    private TextInputEditText password;
    private Button signIn;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        findID();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        presenter = new LoginPresenter(this, auth, database);

        signIn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (presenter.clientIsAuthAlready()) {
            startMainActivity();
        }
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        Log.d(TAG, "startMainActivity");
        startActivity(intent);
        finish();
    }

    private void findID(){
        login = findViewById(R.id.login_log_text);
        password = findViewById(R.id.login_password_text);
        signIn = findViewById(R.id.login_button);
    }

    @Override
    public void showFailedToast(String message) {
        Toast.makeText(LoginActivity.this, message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            //make validation
            presenter.signIn(login.getText().toString(), password.getText().toString());
        }
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.USER_SHARED, MODE_PRIVATE).edit();
        editor.putString(Constants.USER_NAME, user.getUid());
        editor.putString(Constants.EMAIL, user.getEmail());
        editor.putBoolean(Constants.IS_ADMIN, user.isAdmin());
        editor.putString(Constants.GROUP, user.getGroup());
        editor.apply();
        Log.d(TAG, "saveUser");
    }
}
