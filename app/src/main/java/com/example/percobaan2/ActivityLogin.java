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

//    menginialisasi eleemen dan id yang ada di xml
        EditText emailLogin, Passwordlogin;
        Button BtnLogin;
        TextView textView;
        SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //meginisialisai id yang ada di xml disimpan ke variabel
        emailLogin = findViewById(R.id.email_login);
        Passwordlogin = findViewById(R.id.login_password);
        BtnLogin = findViewById(R.id.btn_login);
        textView = findViewById(R.id.loreqId);

        // menginisialisasi Sharpreference untuk mengambil dan menyimpandata yang ada di register
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // manampilkan datadan menyimpan data yang sudah terdaftar akanditampilkan di emaillogin atau editteknya
        String savedEmail = sharedPreferences.getString("email", "");
        emailLogin.setText(savedEmail);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = emailLogin.getText().toString().trim();
                String passwordInput = Passwordlogin.getText().toString().trim();
                // Fetch stored email and password


                //untuk mengambil data yang sudah ditampung sebelumnya
                String registeredEmail = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");
                String registeredFullname = sharedPreferences.getString("fullname", "");
                //mengecek jika data sama akan diarahkan ke dashboard
                if (emailInput.equals(registeredEmail) && passwordInput.equals(registeredPassword)) {
                    Toast.makeText(ActivityLogin.this, "Login sukses", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityLogin.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {//jika salah
                    showInvalidLoginDialog();
                    Toast.makeText(ActivityLogin.this, "invalid email dan pasword", Toast.LENGTH_SHORT).show();
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
    }//alert untuk sesion jika salah amaka akan diarahkan ke pilihan
    private void showInvalidLoginDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Gagal")
                .setMessage("Email atau password salah")
                .setPositiveButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ActivityLogin.this, ActivityLogin.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
