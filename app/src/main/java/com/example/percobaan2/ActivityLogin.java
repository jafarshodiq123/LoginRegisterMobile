package com.example.percobaan2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

                if (emailInput.equals(registeredEmail) && passwordInput.equals(registeredPassword)) {
                    Toast.makeText(ActivityLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // Redirect to home or dashboard activity
                    Intent intent = new Intent(ActivityLogin.this, MahasiswaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
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
}
