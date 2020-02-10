package com.automotive.automotiveplatform;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.automotive.automotiveplatform.ui.account.AccountFragment;
import com.automotive.automotiveplatform.ui.dashboard.DashboardFragment;
import com.automotive.automotiveplatform.ui.home.HomeFragment;
import com.automotive.automotiveplatform.ui.notifications.NotificationsFragment;
import com.automotive.automotiveplatform.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DashboardActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String checktoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences = getSharedPreferences("tokens", MODE_PRIVATE);
        checktoken = sharedPreferences.getString("token", "");
        Toast.makeText(this, checktoken, Toast.LENGTH_SHORT).show();

        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.navigation_dashboard:
                        fragment = new DashboardFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.navigation_notifications:
                        if(checktoken==""){
                                fragment =new AccountFragment();
                                loadFragment(fragment);}

                    else{
                        fragment = new NotificationsFragment();
                                loadFragment(fragment);}

                        break;

                    case R.id.navigation_Acc:
                        if (checktoken=="") {
                            fragment = new AccountFragment();
                            loadFragment(fragment);
                        } else {
                            fragment = new ProfileFragment();
                            loadFragment(fragment);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
