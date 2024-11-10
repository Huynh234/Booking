package com.example.booking.Page.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.booking.Model.TaiKhoan;
import com.example.booking.Page.Admin.FragmentAdmin.dashBoardFragment;
import com.example.booking.Page.Admin.FragmentAdmin.homeFragment;
import com.example.booking.Page.Admin.FragmentAdmin.notificationsFragment;
import com.example.booking.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAdmin extends AppCompatActivity {
    TextView tvUsername;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        //
        tvUsername = findViewById(R.id.tvUsername);

        //
        Intent intent = getIntent();
        TaiKhoan taiKhoan = (TaiKhoan) intent.getSerializableExtra("taiKhoan");
        tvUsername.setText("Xin chào, " + taiKhoan.getUsername() + " - " + taiKhoan.getRole());
        //
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Load fragment mặc định
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new homeFragment())
                    .commit();
        }

    }
    @SuppressLint("NonConstantResourceId")
    public final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new homeFragment();
                } else if (item.getItemId() == R.id.nav_dashboard) {
                    selectedFragment = new dashBoardFragment();
                } else if (item.getItemId() == R.id.nav_notifications) {
                    selectedFragment = new notificationsFragment();
                } else {
                    // Fragment mặc định
                    selectedFragment = new homeFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            };
}