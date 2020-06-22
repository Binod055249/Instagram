package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

//        if(ParseUser.getCurrentUser()!=null){
//            ParseUser.getCurrentUser().logOut();
//        }

    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()){

            case R.id.btnLogInActivity:
                ParseUser.logInInBackground(edtLogInEmail.getText().toString(),
                        edtLogInPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user!=null && e==null){
                                    FancyToast.makeText(LogInActivity.this, "LoginSuccessfull",
                                            Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                                }else{
                                    FancyToast.makeText(LogInActivity.this, e.getMessage(),
                                            Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();

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
}