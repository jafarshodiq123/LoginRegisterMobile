package com.example.percobaan2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.percobaan2.Helper.DbHelper;
import com.example.percobaan2.model.userModel;

public class ActivityLogin extends AppCompatActivity {

    // Deklarasi elemen UI dan variabel
    private DbHelper dbHelper;
    private EditText emailLogin, passwordLogin;
    private Button btnLogin;
    private TextView registerLink;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi elemen UI
        dbHelper = new DbHelper(this);
        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        registerLink = findViewById(R.id.loreqId);

        // Inisialisasi SharedPreferences
        sharedPreferences = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);

        // Cek status login
        boolean isLogin = sharedPreferences.getBoolean("islogin", false);
        if (isLogin) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }

        // Menampilkan email yang tersimpan
        String savedEmail = sharedPreferences.getString("email", "");
        emailLogin.setText(savedEmail);

        // Aksi ketika tombol login diklik
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = emailLogin.getText().toString().trim();
                String passwordInput = passwordLogin.getText().toString().trim();

                // Cek user di database
                userModel user = dbHelper.getUser(emailInput, passwordInput);

                if (user != null) {
                    Toast.makeText(ActivityLogin.this, "Login sukses", Toast.LENGTH_SHORT).show();

                    // Simpan status login
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", emailInput);
                    editor.putString("password", passwordInput); // Hati-hati menyimpan password dalam SharedPreferences
                    editor.putBoolean("islogin", true);
                    editor.apply();

                    // Pindah ke Dashboard
                    Intent intent = new Intent(ActivityLogin.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showInvalidLoginDialog();
                }
            }
        });

        // Aksi ketika link register diklik
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
            }
        });
    }

    // Tampilkan dialog login gagal
    private void showInvalidLoginDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Gagal")
                .setMessage("Email atau password salah")
                .setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
