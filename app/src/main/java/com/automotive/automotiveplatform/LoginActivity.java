package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.login_emailid);
        password =findViewById(R.id.login_password);
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:

        }
    }
}
