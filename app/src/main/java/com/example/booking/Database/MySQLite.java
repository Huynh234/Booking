package com.example.booking.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UFormat;

import com.example.booking.Model.TaiKhoan;

import java.util.ArrayList;

public class MySQLite extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Booking.sql";
    public MySQLite(Context context, String dbName,
                    SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, dbName, cursorFactory, version);
        // Tạo bảng TAI_KHOAN
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "TAI_KHOAN ( " +
                "id integer primary key autoincrement, " +
                "username varchar(255), " +
                "password varchar(255), " +
                "name varchar(255), " +
                "email varchar(255), " +
                "sdt varchar(255), " +
                "cccd varchar(255), " +
                "address varchar(255), " +
                "role integer );"; // 0: quản trị viên | 1: nhân viên
        querySQL(sql);
        // Thêm tài khoản admin
        sql = "SELECT COUNT(id) FROM TAI_KHOAN WHERE username = 'admin';";
        Cursor cursor = getDataFromSQL(sql);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
        }
        if (id <= 0) {
            sql = "INSERT INTO TAI_KHOAN " +
                    "VALUES (null, 'admin', '123456', 'Nguyễn Văn A', 'quantri@gmail.com', '0123456789', '001204014888', 'Hà Nội', 0);";
            querySQL(sql);
        }
        // Thêm tài khoản nhan vien
        sql = "SELECT COUNT(id) FROM TAI_KHOAN WHERE username = 'user1';";
        cursor = getDataFromSQL(sql);
        id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
        }
        if (id <= 0) {
            sql = "INSERT INTO TAI_KHOAN " +
                    "VALUES (null, 'user1', '123456', 'Trần Văn B', 'nhanvien@gmail.com', '0123456989', '001204015888', 'Hà Nội', 1);";
            querySQL(sql);
        }
    }
    // Truy vấn không trả về kết quả: CREATE, UPDATE, DELETE, V...V
    public void querySQL(String sql) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
    // Truy vấn trả về kết quả: SELECT,...
    public Cursor getDataFromSQL(String sql) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }
    public TaiKhoan kiemTraDangNhap(String tenDangNhap, String matKhau) {
        String sql = "SELECT * FROM TAI_KHOAN " +
                "WHERE username = '" + tenDangNhap + "' and password = '" + matKhau + "'";
        Cursor cursor = getDataFromSQL(sql);
        TaiKhoan taiKhoan = new TaiKhoan();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDN = cursor.getString(1);
            String mk = cursor.getString(2);
            String ht = cursor.getString(3);
            String email = cursor.getString(4);
            String sdt = cursor.getString(5);
            String cccd = cursor.getString(6);
            String address = cursor.getString(7);
            int vt = cursor.getInt(8);
            taiKhoan = new TaiKhoan(id, vt, tenDN, mk, email, address, sdt, cccd, ht);

        }
        return taiKhoan;
    }
    public ArrayList<TaiKhoan> docDuLieuTaiKhoan(String sql) {
        ArrayList<TaiKhoan> listTaiKhoan = new ArrayList<>();
        Cursor cursor = getDataFromSQL(sql);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDN = cursor.getString(1);
            String mk = cursor.getString(2);
            String ht = cursor.getString(3);
            String email = cursor.getString(4);
            String sdt = cursor.getString(5);
            String cccd = cursor.getString(6);
            String address = cursor.getString(7);
            int vt = cursor.getInt(8);
            TaiKhoan taiKhoan = new TaiKhoan(id, vt, tenDN, mk, email, address, sdt, cccd, ht);
            listTaiKhoan.add(taiKhoan);
        }
        return listTaiKhoan;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
