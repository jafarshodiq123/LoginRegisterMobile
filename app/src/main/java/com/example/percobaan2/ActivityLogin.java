package com.example.percobaan2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    EditText emailLogin, Passwordlogin;
    Button BtnLogin;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailLogin = findViewById(R.id.email_login);
        Passwordlogin = findViewById(R.id.login_password);
        BtnLogin = findViewById(R.id.btn_login);
        textView = findViewById(R.id.loreqId);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // manampilkan data yang sudah terdaftar akanditampilkan di emaillogin atau editteknya
        String savedEmail = sharedPreferences.getString("email", "");
        emailLogin.setText(savedEmail);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = emailLogin.getText().toString().trim();
                String passwordInput = Passwordlogin.getText().toString().trim();

                // Fetch stored email and password
                //manampung data dari register
                String registeredEmail = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");
                String registeredFullname = sharedPreferences.getString("fullname", "");


                if (emailInput.equals(registeredEmail) && passwordInput.equals(registeredPassword)) {
                    Toast.makeText(ActivityLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ActivityLogin.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showInvalidLoginDialog();
                    Toast.makeText(ActivityLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
    }
    private void showInvalidLoginDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Email atau password salah. Apakah Anda ingin melanjutkan ke daftar siswa?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ActivityLogin.this, MahasiswaActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
