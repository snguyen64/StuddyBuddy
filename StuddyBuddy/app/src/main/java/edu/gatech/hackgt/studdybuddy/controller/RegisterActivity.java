package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.hackgt.studdybuddy.R;

public class RegisterActivity extends AppCompatActivity {

    private Button submit;
    private Button cancel;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        confirmPass = (EditText) findViewById(R.id.confirmPassText);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
