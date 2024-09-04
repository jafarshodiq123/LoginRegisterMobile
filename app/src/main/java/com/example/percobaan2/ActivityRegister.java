package com.example.percobaan2;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ActivityRegister extends AppCompatActivity {

    EditText Fullname, Username,Tgllahir, Email, Password, Confirm, No_hp, Alamat;
    Spinner GenderSpiner;
    Button Registerbutton;
    String SelecDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
//menginisialisasi dta
        Fullname = findViewById(R.id.reg_fullname);
        Username = findViewById(R.id.req_username);
        Tgllahir = findViewById(R.id.reg_tgllahir);
        Email = findViewById(R.id.reg_email);
        Password = findViewById(R.id.reg_password);
        Confirm = findViewById(R.id.reg_konfirmasi);
        GenderSpiner = findViewById(R.id.spinner_gender);
        No_hp = findViewById(R.id.reg_nohp);
        Alamat = findViewById(R.id.reg_alamat);
        Registerbutton = findViewById(R.id.req_btn);


        // mmebuat untuk spiner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GenderSpiner.setAdapter(adapter);


        //
        Tgllahir.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ActivityRegister.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar selectedDate = Calendar.getInstance();
                            selectedDate.set(year, month, dayOfMonth);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            SelecDate = sdf.format(selectedDate.getTime());
                            Tgllahir.setText(SelecDate);
                        }
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });


        // membuat tombol ketika ditekan
        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // menangkap data yang diinput
                String strfullname = Fullname.getText().toString().trim();
                String strusername = Username.getText().toString().trim();
                String strtglhir = Tgllahir.getText().toString().trim();
                String stremail = Email.getText().toString().trim();
                String strpassword = Password.getText().toString().trim();
                String strkonfirm = Confirm.getText().toString().trim();
                String strgender = GenderSpiner.getSelectedItem().toString().trim();
                String strhp = No_hp.getText().toString().trim();
                String stralmat = Alamat.getText().toString().trim();

                if (strpassword.equals(strkonfirm)) {
                    Toast.makeText(ActivityRegister.this, "anda berhasil register "
                            + "fullname :" + strfullname + "\n" +
                            "username :" + strusername + "\n" +
                            "tanggal lahir :" + strtglhir + "\n" +
                            "Email :" + stremail + "\n" +
                            "Password :" + strpassword + "\n" +
                            "Confirmpasword :" + strkonfirm + "\n" +
                            "gender:" + strgender + "\n" +
                            "No Hp :" + strhp + "\n" +
                            "Almat:" + stralmat, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityRegister.this, "password anda slah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}