package com.example.booking.Page.Customer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.booking.Model.TaiKhoan;
import com.example.booking.R;

public class HomeCustomer extends AppCompatActivity {
    TextView tvUsername;
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

    }
}