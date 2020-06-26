package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLogInEmail);
        edtLoginPassword = findViewById(R.id.edtLogInPassword);
        btnLoginActivity = findViewById(R.id.btnLogInActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLogInActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogInActivity:
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {

                                    FancyToast.makeText(LogInActivity.this,
                                            user.getUsername() + " is Logged in successfully",
                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                            false).show();
                                    transitionToSocialMediaActivity();
                                }
                            }
                        });

                break;

            case R.id.btnSignUpLogInActivity:
                Intent intent = new Intent(LogInActivity.this, SignUp.class);
                startActivity(intent);
                break;


        }
    }


    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LogInActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }


}