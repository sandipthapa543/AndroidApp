package com.automotive.automotiveplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.ApiClass;
import api.UserApi;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, phone, address;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        name = findViewById(R.id.firstName);
        phone = findViewById(R.id.Phone);
        address = findViewById(R.id.Address);
        btnSave = findViewById(R.id.btnUpdate);
        btnSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        updateUser();
    }

    private void updateUser() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);

        SharedPreferences sharedPreferences = getSharedPreferences("tokens", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        String _id = sharedPreferences.getString("_id", null);
        String names = name.getText().toString();
        String phones = phone.getText().toString();
        String addresses = address.getText().toString();
        Call<UserModel> userCall = usersAPI.updateuser(_id, token,names,phones,addresses);
        userCall.enqueue(new Callback<UserModel>() {


            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(UpdateProfileActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UpdateProfileActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
