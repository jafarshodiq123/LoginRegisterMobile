package com.example.percobaan2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    TextView welcomeTextView, emailTextView;
    SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Pastikan layout ini ada

        // Inisialisasi BottomNavigationView setelah setContentView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        // Set default fragment saat pertama kali activity dimulai
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Ambil nama pengguna dan email dari SharedPreferences
        String userFullname = sharedPreferences.getString("fullname", "fullname");
        String userEmail = sharedPreferences.getString("email", "Email tidak ditemukan");

        // Tampilkan pesan selamat datang
        welcomeTextView = findViewById(R.id.welcomeTextView);
        emailTextView = findViewById(R.id.emailTextView); // Tambahkan TextView untuk email
        welcomeTextView.setText("Selamat datang, " + userFullname);
        emailTextView.setText("Email: " + userEmail);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            return true;
        } else if (id == R.id.nav_dashboard) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
            return true;
        } else if (id == R.id.nav_notifications) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).commit();
            return true;
        }

        return false;
    }
}

