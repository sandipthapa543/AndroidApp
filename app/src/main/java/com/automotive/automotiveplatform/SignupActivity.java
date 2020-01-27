package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import api.ApiClass;
import api.Check;
import api.UserApi;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import serverresponse.UserResponse;
import url.ApiUrl;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText firstName, lastName, emailid, password, phone, address;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.Phone);
        address = findViewById(R.id.Address);
        btnSignup=findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Register();

}
private void Register(){
    String fname = firstName.getText().toString();
    String lname = lastName.getText().toString();
    String emails= emailid.getText().toString();
    String passwords = password.getText().toString();
    String phones = phone.getText().toString();
    String addresss = address.getText().toString();


    UserModel users = new UserModel(fname, lname, emails, passwords,phones,addresss );

    UserApi usersAPI = ApiUrl.getInstance().create(UserApi.class);
    Call<UserResponse> signUpCall = usersAPI.register(users);

    signUpCall.enqueue(new Callback<UserResponse>() {
        @Override
        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
            if (!response.isSuccessful()) {
                Toast.makeText(SignupActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
        }



        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {
            Toast.makeText(SignupActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    });

}

}






