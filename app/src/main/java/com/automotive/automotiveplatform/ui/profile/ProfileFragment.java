package com.automotive.automotiveplatform.ui.profile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.automotive.automotiveplatform.DashboardActivity;
import com.automotive.automotiveplatform.R;
import com.automotive.automotiveplatform.ui.profileupdate.ProfileUpdateFragment;

import api.ApiClass;
import api.UserApi;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    String id;
    private TextView name, email, phone, address;
    private Button btnEdit, btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.Name);
        email = view.findViewById(R.id.Email);
        phone = view.findViewById(R.id.Phone);
        address = view.findViewById(R.id.Address);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        loadProfile();

        return view;
    }

    private void loadProfile() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);
        SharedPreferences preferences = getActivity().getSharedPreferences("tokens", MODE_PRIVATE);
        final String token = preferences.getString("token", null);

        Call<UserModel> userModelCall = usersAPI.getMe(token);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                id = userModel.get_id();
                name.setText("Name :" + userModel.getFirst_Name()+" "+userModel.getLast_Name());
                email.setText("Email :" + userModel.getEmail());
                phone.setText("Phone :" + userModel.getPhone());
                address.setText("Address :" + userModel.getAddress());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
                ProfileUpdateFragment fragment = new ProfileUpdateFragment(id,ProfileFragment.this);
                getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();

                break;

            case R.id.btnLogout:
                SharedPreferences sp = getContext().getSharedPreferences("tokens", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token", "");
                editor.apply();
                Intent i = new Intent(getActivity(), DashboardActivity.class);
                startActivity(i);
        }
    }
}