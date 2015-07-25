package com.wordpress.logitopia.ifindher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Phenomeno on 5/19/15.
 */
public class SignupActivity extends AppCompatActivity {

    EditText username, userEmail, userPassword, userPasswordVerify;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.loginUserNameid);
        userEmail = (EditText) findViewById(R.id.loginEmailid);
        userPassword = (EditText) findViewById(R.id.loginPasswordid);
        userPasswordVerify = (EditText) findViewById(R.id.loginPasswordVerifyid);

        signupButton = (Button) findViewById(R.id.signupButtonid);

        Parse.initialize(this, "zC6COTxa5RCGEbl55rkTq4bzAIChirEbC9b5u9dJ", "PqSsd40OA67EBxxf1ro2Wlx2SxXhilvw7r9hskbM");


    }

    public void signUpClick(View view) {

        ParseUser users = new ParseUser();

        users.setUsername(username.getText().toString());
        users.setEmail(userEmail.getText().toString());

        if (userPassword.getText().toString().equals(userPasswordVerify.getText().toString())) {
            users.setPassword(userPasswordVerify.getText().toString());
        } else Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show();

        users.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
//                    Toast.makeText(SignupActivity.this, "Sign up Worked", Toast.LENGTH_LONG).show();
                    Intent gotoHomePage = new Intent(SignupActivity.this,HomePageActivity.class);
                    startActivity(gotoHomePage);

//                    Start using the app
                } else {
                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
