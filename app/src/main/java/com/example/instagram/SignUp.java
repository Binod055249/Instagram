package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtSignUpEmail,edtSignUpUsername,edtSignUpPassword;
    private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtSignUpEmail=findViewById(R.id.edtSignUpEmail);
        edtSignUpUsername=findViewById(R.id.edtSignUpUsername);
        edtSignUpPassword=findViewById(R.id.edtSignUpPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(SignUp.this);
        btnLogIn.setOnClickListener(SignUp.this);

//        if(ParseUser.getCurrentUser()!=null){
//            ParseUser.getCurrentUser().logOut();
//        }

    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()){

            case R.id.btnSignUp:
                final ParseUser parseUser=new ParseUser();
                parseUser.setUsername(edtSignUpUsername.getText().toString());
                parseUser.setEmail(edtSignUpEmail.getText().toString());
                parseUser.setPassword(edtSignUpPassword.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUp.this, parseUser.getUsername()+" is Signed up",
                                    Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }else{
                            FancyToast.makeText(SignUp.this,"There is an error: "+ e.getMessage(),
                                    Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }
                    }
                });
                break;

            case R.id.btnLogIn:
                Intent intent =new Intent(SignUp.this,LogInActivity.class);
                startActivity(intent);
                break;

        }




    }
}