package edu.gatech.hackgt.studdybuddy.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.User;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import edu.gatech.hackgt.studdybuddy.util.FormValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button submit;
    private Button cancel;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private EditText confirmPass;
    private EditText email;

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
        email = (EditText) findViewById(R.id.emailText);

        FormValidator fn = new FormValidator(firstName, "First name");
        FormValidator ln = new FormValidator(lastName, "Last name");
        FormValidator un = new FormValidator(username, "Username");
        FormValidator pa = new FormValidator(password, "Password");
        FormValidator cp = new FormValidator(confirmPass, "Confirm password");
        FormValidator em = new FormValidator(email, "Email");

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
        email.addTextChangedListener(em);
        email.setOnFocusChangeListener(em);
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
        String em = email.getText().toString();

        if (!pass.equals(confirmPW)) {
            confirmPass.setError("Passwords don't match!");
        }

        boolean isValid = firstName.getError() == null && lastName.getError() == null
                && username.getError() == null && password.getError() == null
                && confirmPass.getError() == null && email.getError() == null
                && !TextUtils.isEmpty(first) && !TextUtils.isEmpty(em)
                && !TextUtils.isEmpty(last) && !TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)
                && !TextUtils.isEmpty(confirmPW);

        if (isValid) {
            User userr = new User(first, last, user, pass, em);
            APIClient.getInstance().register(userr).enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if (!response.body().isSuccess()) {
                        AlertDialog ad = new AlertDialog.Builder(RegisterActivity.this)
                                .setMessage(response.body().getMessage())
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).create();
                        ad.show();
                    } else {
                        int userId = Integer.parseInt(response.body().getMessage());
                        Intent intent = new Intent(RegisterActivity.this,
                                ProfileCourseActivity.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {
                    Log.e("RegisterActivity", t.getMessage());
                    AlertDialog ad = new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("An unexpected error occurred. Please try again later.")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).create();
                    ad.show();
                }
            });
        } else {
            Toast.makeText(this, "One or more fields is invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
