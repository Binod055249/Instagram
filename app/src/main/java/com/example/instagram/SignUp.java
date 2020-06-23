package com.example.instagram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtSignUpEmail, edtSignUpUsername, edtSignUpPassword;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpUsername = findViewById(R.id.edtSignUpUsername);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(SignUp.this);
        btnLogIn.setOnClickListener(SignUp.this);

        if (ParseUser.getCurrentUser() != null) {
            //  ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

        edtSignUpPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()) {

            case R.id.btnSignUp:
                if (edtSignUpUsername.getText().toString().equals("") ||
                        edtSignUpEmail.getText().toString().equals("") ||
                        edtSignUpPassword.getText().toString().equals("")) {
                    FancyToast.makeText(this, "Please fill all details",
                            Toast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtSignUpEmail.getText().toString());
                    appUser.setUsername(edtSignUpUsername.getText().toString());
                    appUser.setPassword(edtSignUpPassword.getText().toString());

//                    final DelayedProgressDialog progressDialog = new DelayedProgressDialog();
//                    progressDialog.show(getSupportFragmentManager(), "Signing up");

                    final ProgressDialog progressDialog =new ProgressDialog(this);
                    progressDialog.setMessage("signing up "+edtSignUpUsername.getText().toString());
                    progressDialog.show();


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.get("username") + " is Signed Up Successfully",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                transitionToSocialMediaActivity();

                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            }
                            //progressDialog.cancel();
                            progressDialog.dismiss();
                        }
                    });
                }
                break;

            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this, LogInActivity.class);
                startActivity(intent);
                break;

        }


    }

    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}