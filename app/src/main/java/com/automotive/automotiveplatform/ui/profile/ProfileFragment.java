package com.automotive.automotiveplatform.ui.profile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.automotive.automotiveplatform.DashboardActivity;
import com.automotive.automotiveplatform.R;
import com.automotive.automotiveplatform.SensorActivity;
import com.automotive.automotiveplatform.ui.profileupdate.ProfileUpdateFragment;

import java.io.IOException;

import Bll.LoginBLL;
import api.ApiClass;
import api.UserApi;
import model.User;
import retrofit2.Call;
import retrofit2.Response;
import strictmode.StrictModeClass;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    String id;
    private TextView name, email, phone, address;
    private Button btnEdit,btnShowSensors, btnLogout;
   private Toolbar toolbarHeading;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sp = getContext().getSharedPreferences("tokens", MODE_PRIVATE);
        editor = sp.edit();
        editor.apply();

        name = view.findViewById(R.id.Name);
        email = view.findViewById(R.id.Email);
        phone = view.findViewById(R.id.Phone);
        address = view.findViewById(R.id.Address);
        btnEdit = view.findViewById(R.id.btnEdit);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnShowSensors = view.findViewById(R.id.btnShowSensor);
        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnShowSensors.setOnClickListener(this);

        loadProfile();

        return view;
    }

    private void loadProfile() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);
        SharedPreferences preferences = getActivity().getSharedPreferences("tokens", MODE_PRIVATE);
        final String token = preferences.getString("token", null);

        Call<User> userModelCall = usersAPI.getMe(token);
        StrictModeClass.StrictMode();
        try {
            Response<User> response = userModelCall.execute();
            if (response.isSuccessful()) {
                User userModel = response.body();
                id = userModel.get_id();
                Log.d("id_user", id);
                name.setText( userModel.getFirst_Name() + " " + userModel.getLast_Name());
                email.setText( userModel.getEmail());
                phone.setText( userModel.getPhone());
                address.setText( userModel.getAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
                ProfileUpdateFragment fragment = new ProfileUpdateFragment(id,ProfileFragment.this);
                getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;

            case R.id.btnShowSensor:
                Intent intent = new Intent(getActivity(), SensorActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLogout:
                editor.putString("token", "");
                editor.putString("id", "");
                editor.apply();
                Intent i = new Intent(getActivity(), DashboardActivity.class);
                startActivity(i);
        }
    }
}