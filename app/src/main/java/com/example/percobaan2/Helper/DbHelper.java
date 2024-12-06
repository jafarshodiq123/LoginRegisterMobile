package com.example.percobaan2.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.percobaan2.model.userModel;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "percobaan2";
    private static final String TABLE_NAME = "users";

    // Kolom tabel
    private static final String COL_ID = "id";
    private static final String COL_FULLNAME = "fullname";
    private static final String COL_USERNAME = "username";
    private static final String COL_TANGGAL_LAHIR = "tanggal_lahir";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_GENDER = "gender";
    private static final String COL_NOHP = "nomer_hp";
    private static final String COL_ALAMAT = "alamat";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FULLNAME + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_TANGGAL_LAHIR + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_GENDER + " TEXT, " +
                COL_NOHP + " TEXT, " +
                COL_ALAMAT + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Menambahkan user
    public void addUser(userModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FULLNAME, user.getFullname());
        values.put(COL_USERNAME, user.getUsername());
        values.put(COL_TANGGAL_LAHIR, user.getTanggal_lahir());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_GENDER, user.getGender());
        values.put(COL_NOHP, user.getNohp());
        values.put(COL_ALAMAT, user.getAlamat());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Memeriksa user berdasarkan email dan password
    public boolean userExists(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID},
                COL_EMAIL + "=? AND " + COL_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Mengambil user berdasarkan email dan password
    public userModel getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,
                COL_EMAIL + "=? AND " + COL_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            userModel user = new userModel();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
            user.setFullname(cursor.getString(cursor.getColumnIndexOrThrow(COL_FULLNAME)));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME)));
            user.setTanggal_lahir(cursor.getString(cursor.getColumnIndexOrThrow(COL_TANGGAL_LAHIR)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COL_GENDER)));
            user.setNohp(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOHP)));
            user.setAlamat(cursor.getString(cursor.getColumnIndexOrThrow(COL_ALAMAT)));
            cursor.close();
            db.close();
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }

    // Mengambil semua user
    public List<userModel> getAllUsers() {
        List<userModel> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                userModel user = new userModel();
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
                user.setFullname(cursor.getString(cursor.getColumnIndexOrThrow(COL_FULLNAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME)));
                user.setTanggal_lahir(cursor.getString(cursor.getColumnIndexOrThrow(COL_TANGGAL_LAHIR)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
                user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COL_GENDER)));
                user.setNohp(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOHP)));
                user.setAlamat(cursor.getString(cursor.getColumnIndexOrThrow(COL_ALAMAT)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }
}
