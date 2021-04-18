package com.codepath.password_storer;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    private EditText etNewUsername;
    private EditText etNewPassword1;
    private EditText etNewPassword2;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnCreate = findViewById(R.id.btnCreate);
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword1 = findViewById(R.id.etNewPassword1);
        etNewPassword2 = findViewById(R.id.etNewPassword2);



        btnCreate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v)
            {
                if (etNewPassword1.getText().toString().equals(etNewPassword2.getText().toString()))
                {
                    ParseUser user = new ParseUser();
                    // Set the user's username and password, which can be obtained by a forms
                    user.setUsername(etNewUsername.getText().toString());
                    user.setPassword(etNewPassword1.getText().toString());
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUpActivity.this, "User Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(SignUpActivity.this, "Fail to Register User..", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Fail to Register User..", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}