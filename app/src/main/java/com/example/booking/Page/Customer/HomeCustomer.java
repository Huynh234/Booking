package com.example.booking.Page.Customer;

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
import com.example.booking.Page.Customer.FragmentCustomer.DetailUser;
import com.example.booking.Page.Customer.FragmentCustomer.FindRoom;
import com.example.booking.Page.Customer.FragmentCustomer.ListRoom;
import com.example.booking.Page.Customer.FragmentCustomer.NotifiUser;
import com.example.booking.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeCustomer extends AppCompatActivity {
    TextView tvUsername;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        //
        tvUsername = findViewById(R.id.tvUsername);

        //
        Intent intent = getIntent();
        TaiKhoan taiKhoan = (TaiKhoan) intent.getSerializableExtra("taiKhoan");
        tvUsername.setText("Xin chào, " + taiKhoan.getUsername() + " - " + taiKhoan.getRole());
        //
        bottomNavigationView = findViewById(R.id.bottom_nav_cus);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Load fragment mặc định
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_con_cus, new RoomManager())
                    .commit();
        }
    }
    @SuppressLint("NonConstantResourceId")
    public final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_cus_home) {
                    selectedFragment = new com.example.booking.Page.Customer.FragmentCustomer.HomeCustomer();
                } else if (item.getItemId() == R.id.nav_cus_find) {
                    selectedFragment = new FindRoom();
                } else if (item.getItemId() == R.id.nav_cus_list) {
                    selectedFragment = new ListRoom();
                }else if (item.getItemId() == R.id.nav_cus_notifi) {
                    selectedFragment = new NotifiUser();
                } else if (item.getItemId() == R.id.nav_cus_user) {
                    selectedFragment = new DetailUser();
                } else {
                    // Fragment mặc định
                    selectedFragment = new com.example.booking.Page.Customer.FragmentCustomer.HomeCustomer();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_con_cus, selectedFragment)
                        .commit();
                return true;
            };
}