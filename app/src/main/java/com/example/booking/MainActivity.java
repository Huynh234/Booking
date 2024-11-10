package com.example.booking;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.Database.MySQLite;
import com.example.booking.Model.TaiKhoan;
import com.example.booking.Page.Admin.HomeAdmin;
import com.example.booking.Page.Customer.HomeCustomer;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogIn;
    MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        mySQLite = new MySQLite(MainActivity.this, MySQLite.DATABASE_NAME,null, 1);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtUsername.getText().toString().trim();
                String matKhau = edtPassword.getText().toString().trim();
                if (tenDangNhap.length() > 0 && matKhau.length() > 0) {
                    TaiKhoan taiKhoan = mySQLite.kiemTraDangNhap(tenDangNhap, matKhau);
                    String msg = taiKhoan.getRole() == 0 ? "dung" : "sai";
//                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    if (taiKhoan.getId() >= 0 && taiKhoan.getRole() >= 0) {
                        if (taiKhoan.getRole() == 0) {
                            Intent intentQuanTri = new Intent(MainActivity.this, HomeAdmin.class);
                            intentQuanTri.putExtra("taiKhoan", taiKhoan);
                            startActivity(intentQuanTri);
                        }
                        if (taiKhoan.getRole() == 1) {
                            Intent intentDatHang = new Intent(MainActivity.this, HomeCustomer.class);
                            intentDatHang.putExtra("taiKhoan", taiKhoan);
                            startActivity(intentDatHang);
                        }
                    } else Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity.this, "Vui lòng nhập tài khoản và mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}