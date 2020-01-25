package com.automotive.automotiveplatform.ui.account;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.automotive.automotiveplatform.LoginActivity;
import com.automotive.automotiveplatform.R;
import com.automotive.automotiveplatform.SignupActivity;
import com.automotive.automotiveplatform.ui.dashboard.DashboardFragment;
import com.automotive.automotiveplatform.ui.home.HomeFragment;


public class AccountFragment extends Fragment implements View.OnClickListener {
    private Button btnSignUp, btnSignIn;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btnSignIn= view.findViewById(R.id.btnSignIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.btnSignUp:
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);


                break;


            case R.id.btnSignIn:

                Intent intents =new Intent(getActivity(), LoginActivity.class);
                startActivity(intents);
                break;

        }
    }
}
