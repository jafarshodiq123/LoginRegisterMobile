package com.example.percobaan2.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASES_NAME = "PR01";
    //table
    private static final String TABLE_NAME = "user";
    //colom
    private static final String Col_ID = "id";
    private static final String Col_FULLNAME = "fullname";
    private static final String Col_USERNAME = "username";
    private static final String Col_TANGGALLAHIR = "tgl_lahir";
    private static final String Col_EMAIL = "email";
    private static final String Col_PASSWORD = "password";
    private static final String Col_KONFIRMASI = "konfirm";
    private static final String Col_GENDER = "gender";
    private static final String Col_NOHP = "nohp";
    private static final String Col_ALAMAT = "alamat";

    public DbHelper(Context context){
        super(context,DATABASES_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel users
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Col_FULLNAME + " TEXT, " +
                Col_USERNAME + " TEXT, " +
                Col_TANGGALLAHIR + " DATE, " +
                Col_EMAIL + " TEXT, " +
                Col_PASSWORD + " TEXT, " +
                Col_KONFIRMASI + " TEXT, " +
                Col_GENDER + " TEXT, " +
                Col_NOHP + " TEXT, " +
                Col_ALAMAT + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    //MENGHAPUS TABEL LAMA JIKA ADA PERUBAHAN
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int Newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
