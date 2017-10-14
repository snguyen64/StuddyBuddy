package edu.gatech.hackgt.studdybuddy.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.utils.FormValidator;

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

        FormValidator fn = new FormValidator(firstName, "First name");
        FormValidator ln = new FormValidator(lastName, "Last name");
        FormValidator un = new FormValidator(username, "Username");
        FormValidator pa = new FormValidator(password, "Password");
        FormValidator cp = new FormValidator(confirmPass, "Confirm password");

        firstName.addTextChangedListener(fn);
        firstName.setOnFocusChangeListener(fn);
        lastName.addTextChangedListener(ln);
        lastName.setOnFocusChangeListener(ln);
        username.addTextChangedListener(un);
        username.setOnFocusChangeListener(un);
        password.addTextChangedListener(pa);
        password.setOnFocusChangeListener(pa);
        confirmPass.addTextChangedListener(cp);
        confirmPass.setOnFocusChangeListener(cp);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view) {
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String confirmPW = confirmPass.getText().toString();
        if (!pass.equals(confirmPW)) {
            confirmPass.setError("Passwords don't match!");
        }
        boolean isValid = firstName.getError() == null && lastName.getError() == null
                && username.getError() == null && password.getError() == null
                && confirmPass.getError() == null && !TextUtils.isEmpty(first)
                && !TextUtils.isEmpty(last) && !TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)
                && !TextUtils.isEmpty(confirmPW);

    }
}
