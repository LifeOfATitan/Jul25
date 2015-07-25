package com.wordpress.logitopia.ifindher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login, signup;

    private final String TAG = "ParseException";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(this, "zC6COTxa5RCGEbl55rkTq4bzAIChirEbC9b5u9dJ", "PqSsd40OA67EBxxf1ro2Wlx2SxXhilvw7r9hskbM");

        username = (EditText)findViewById(R.id.loginUserNameid);
        password = (EditText)findViewById(R.id.loginPasswordid);

        login = (Button)findViewById(R.id.loginButtonid);
        signup = (Button)findViewById(R.id.signupButtonid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signUpClick(View view) {

        Intent gotoSignup = new Intent(this, SignupActivity.class);
        startActivity(gotoSignup);

        Toast.makeText(this,"You clicked "+signup.getText(),Toast.LENGTH_LONG).show();
    }

    public void loginButtonClick(View view) {

        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                if (parseUser != null)
                {
                    Toast.makeText(LoginActivity.this,"User Logged in",Toast.LENGTH_SHORT).show();
                    Intent gotoHomePage = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(gotoHomePage);
                } else
                {
                    Toast.makeText(LoginActivity.this,"User not logged in",Toast.LENGTH_SHORT).show();
                    Log.i(TAG,e.getMessage());
                }
            }
        });

    }
}
