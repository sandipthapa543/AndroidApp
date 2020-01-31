package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Bll.LoginBLL;
import model.UserModel;
import strictmode.StrictModeClass;
import url.ApiUrl;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emails, passwords;
    Button btnlogin;
    public static String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emails = findViewById(R.id.login_emailid);
        passwords = findViewById(R.id.login_password);
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(this);

        emails.setText("");
        passwords.setText("");


    }

    @Override
    public void onClick(View v) {
        login();


    }

    public void login() {
        String email = emails.getText().toString();
        String password = passwords.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(email, password)) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            Toast.makeText(this, LoginBLL.token, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
            SharedPreferences preferences = getSharedPreferences("tokens", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("token", LoginBLL.token);
            editor.commit();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            emails.requestFocus();
        }
    }


}
