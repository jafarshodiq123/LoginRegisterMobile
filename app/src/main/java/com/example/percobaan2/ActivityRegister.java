package com.example.percobaan2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.percobaan2.Helper.DbHelper;
import com.example.percobaan2.model.userModel;

import java.util.Calendar;

public class ActivityRegister extends AppCompatActivity {
    EditText fullname, username, email, password, confirm, dob, nohp, alamat;
    Button registerBtn;
    Spinner genderSpinner;
    String selectedGender;
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi
        dbHelper = new DbHelper(this);
        fullname = findViewById(R.id.reg_fullname);
        username = findViewById(R.id.req_username);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        confirm = findViewById(R.id.reg_konfirmasi);
        dob = findViewById(R.id.reg_tgllahir);
        nohp = findViewById(R.id.reg_nohp);
        alamat = findViewById(R.id.reg_alamat);
        genderSpinner = findViewById(R.id.spinner_gender);
        registerBtn = findViewById(R.id.req_btn);

        sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        // Setup Gender Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedGender = "";
            }
        });

        // Setup Date Picker
        dob.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityRegister.this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        selectedMonth += 1; // Bulan dimulai dari 0
                        dob.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Register Button
        registerBtn.setOnClickListener(view -> {
            String strFullname = fullname.getText().toString().trim();
            String strUsername = username.getText().toString().trim();
            String strDob = dob.getText().toString().trim();
            String strEmail = email.getText().toString().trim();
            String strPassword = password.getText().toString().trim();
            String strConfirm = confirm.getText().toString().trim();
            String strNohp = nohp.getText().toString().trim();
            String strAlamat = alamat.getText().toString().trim();

            // Validasi
            if (strFullname.isEmpty() || strUsername.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty() ||
                    strConfirm.isEmpty() || strDob.isEmpty() || selectedGender.isEmpty() || strNohp.isEmpty() || strAlamat.isEmpty()) {
                Toast.makeText(ActivityRegister.this, "Tolong isi semua data", Toast.LENGTH_SHORT).show();
            } else if (!strPassword.equals(strConfirm)) {
                Toast.makeText(ActivityRegister.this, "Konfirmasi password salah", Toast.LENGTH_SHORT).show();
            } else {
                // Simpan user ke database
                userModel user = new userModel(0, strFullname, strUsername, strDob, strEmail, strPassword, selectedGender, strNohp, strAlamat);
                dbHelper.addUser(user);

                // Simpan ke SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fullname", strFullname);
                editor.putString("username", strUsername);
                editor.putString("email", strEmail);
                editor.putBoolean("islogin", true);
                editor.apply();

                Toast.makeText(ActivityRegister.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityRegister.this, ActivityLogin.class));
                finish();
            }
        });
    }
}
