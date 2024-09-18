package com.example.percobaan2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    //menginialisiasi
    EditText Fullname, Username, Email, Password, Confirm;
    Button Registerbutton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize views
        Fullname = findViewById(R.id.reg_fullname);
        Username = findViewById(R.id.req_username);
        Email = findViewById(R.id.reg_email);
        Password = findViewById(R.id.reg_password);
        Confirm = findViewById(R.id.reg_konfirmasi);
        Registerbutton = findViewById(R.id.req_btn);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Register button click
        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Capture input data
                String strfullname = Fullname.getText().toString().trim();
                String stremail = Email.getText().toString().trim();
                String strpassword = Password.getText().toString().trim();
                String strkonfirm = Confirm.getText().toString().trim();

                if (strfullname.isEmpty() || stremail.isEmpty() || strpassword.isEmpty() || strkonfirm.isEmpty()) {
                    Toast.makeText(ActivityRegister.this, "tolong isikan smua data", Toast.LENGTH_SHORT).show();
                } else if (!strpassword.equals(strkonfirm)) {//jika psword dan cofirm tidak sama
                    Toast.makeText(ActivityRegister.this, "Password salah silahkan isi kembali", Toast.LENGTH_SHORT).show();
                } else {
                    // data ditangkap dan akan disimpan didalam preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", stremail);
                    editor.putString("password", strpassword);
                    editor.apply();

                    Toast.makeText(ActivityRegister.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    // Redirect to LoginActivity
                    Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();  // Finish this activity to prevent going back
                }
            }
        });
    }
}
