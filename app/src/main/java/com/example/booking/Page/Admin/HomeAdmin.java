package com.example.booking.Page.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.booking.Model.TaiKhoan;
import com.example.booking.Page.Admin.FragmentAdmin.ApprovalOfComment;
import com.example.booking.Page.Admin.FragmentAdmin.ManageOffers;
import com.example.booking.Page.Admin.FragmentAdmin.RoomManager;
import com.example.booking.Page.Admin.FragmentAdmin.RoomTypeManagement;
import com.example.booking.Page.Admin.FragmentAdmin.ServiceManagement;
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
                    .replace(R.id.fragment_container, new RoomManager())
                    .commit();
        }

    }
    @SuppressLint("NonConstantResourceId")
    public final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_ad_room) {
                    selectedFragment = new RoomManager();
                } else if (item.getItemId() == R.id.nav_ad_room_type) {
                    selectedFragment = new RoomTypeManagement();
                } else if (item.getItemId() == R.id.nav_ad_offer) {
                    selectedFragment = new ManageOffers();
                }else if (item.getItemId() == R.id.nav_ad_service) {
                    selectedFragment = new ServiceManagement();
                } else if (item.getItemId() == R.id.nav_ad_comment) {
                    selectedFragment = new ApprovalOfComment();
                } else {
                    // Fragment mặc định
                    selectedFragment = new RoomManager();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            };
}