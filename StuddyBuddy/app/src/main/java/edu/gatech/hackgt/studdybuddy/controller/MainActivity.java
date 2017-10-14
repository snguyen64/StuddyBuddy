package edu.gatech.hackgt.studdybuddy.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.hackgt.studdybuddy.R;
import edu.gatech.hackgt.studdybuddy.model.APIMessage;
import edu.gatech.hackgt.studdybuddy.model.LoginUser;
import edu.gatech.hackgt.studdybuddy.model.User;
import edu.gatech.hackgt.studdybuddy.util.APIClient;
import edu.gatech.hackgt.studdybuddy.util.FormValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private EditText user;
    private EditText pass;

    public static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registerButton);
        user = (EditText) findViewById(R.id.usernameText);
        pass = (EditText) findViewById(R.id.passwordText);

        FormValidator usernameFV = new FormValidator(user, "Username");
        FormValidator passwordFV = new FormValidator(pass, "Password");

        user.addTextChangedListener(usernameFV);
        user.setOnFocusChangeListener(usernameFV);
        pass.addTextChangedListener(passwordFV);
        pass.setOnFocusChangeListener(passwordFV);
    }

    public void login(View view) {
        String un = user.getText().toString();
        String pw = pass.getText().toString();
        boolean isValid = user.getError() == null
                && pass.getError() == null && !TextUtils.isEmpty(un)
                && !TextUtils.isEmpty(pw);
        if (isValid) {
            LoginUser currentUser = new LoginUser(un, pw);
            APIClient.getInstance().login(currentUser).enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if (!response.body().isSuccess()) {
                        AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
                                .setMessage(response.body().getMessage())
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).create();
                        ad.show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, CourseSelectionActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {
                    AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
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
            Toast.makeText(this, "Username or Password invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
