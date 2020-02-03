package com.automotive.automotiveplatform.ui.profileupdate;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.automotive.automotiveplatform.R;

import java.io.IOException;

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
public class ProfileUpdateFragment extends Fragment implements View.OnClickListener {
    String id;
    EditText firstname,lastname, phone, address;
    Button btnSave;
    Fragment currentFragment;

    public ProfileUpdateFragment(String id, Fragment currentFragment) {
        this.currentFragment = currentFragment;
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_profile_update, container, false);
        firstname = root.findViewById(R.id.firstName);
        lastname = root.findViewById(R.id.lastName);
        phone = root.findViewById(R.id.Phone);
        address = root.findViewById(R.id.Address);
        btnSave = root.findViewById(R.id.btnUpdate);
        btnSave.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        updateUser();
    }

    private void updateUser() {
        UserApi usersAPI = ApiClass.getInstance().create(UserApi.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tokens", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String phones = phone.getText().toString();
        String addresses = address.getText().toString();
        Call<UserModel> userCall = usersAPI.updateuser(id, token, fname, lname ,phones,addresses);
        try {
            Response<UserModel> response = userCall.execute();
            if (!response.isSuccessful()) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            } else {
                reload();
                getActivity().getFragmentManager().popBackStack();
                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reload(){
       final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(ProfileUpdateFragment.this).attach(currentFragment).commit();
    }

}
