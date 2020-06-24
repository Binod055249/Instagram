package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLogInEmail,edtLogInPassword;
    private Button btnLogInActivity,btnSignUpLogInActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");

        edtLogInEmail=findViewById(R.id.edtLogInEmail);
        edtLogInPassword=findViewById(R.id.edtLogInPassword);
        btnLogInActivity=findViewById(R.id.btnLogInActivity);
        btnSignUpLogInActivity=findViewById(R.id.btnSignUpLogInActivity);

        btnLogInActivity.setOnClickListener(LogInActivity.this);
        btnSignUpLogInActivity.setOnClickListener(LogInActivity.this);

        if(ParseUser.getCurrentUser()!=null){
           // ParseUser.getCurrentUser().logOut();
         transitionToSocialMediaActivity();
        }

        edtLogInPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER &&
                        event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnLogInActivity);
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View buttonView) {
        switch (buttonView.getId()){
            case R.id.btnLogInActivity:
                ParseUser.logInInBackground(edtLogInEmail.getText().toString(),
                            edtLogInPassword.getText().toString(),
                             new LogInCallback(){
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(LogInActivity.this, "Login Successfull",
                                                Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                        transitionToSocialMediaActivity();
                                    } else {
                                        FancyToast.makeText(LogInActivity.this, "Email and password is required",
                                                Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                                    }
                                }
                            });
                    break;

            case R.id.btnSignUpLogInActivity:
                ParseUser.logOut();
                finish();
                break;

        }

    }
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity(){
        Intent intent=new Intent(LogInActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}