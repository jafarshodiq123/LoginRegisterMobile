package com.example.percobaan2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import android.view.MenuItem;

import java.util.ArrayList;

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

//        recyclerView = findViewById(R.id.rcycleview); // Inisialisasi RecyclerView
//        userList = new ArrayList<>();
//        userList.add("Selamat datang "+userFullname);
////        userList.add(userEmail); // Menambahkan email ke daftar user
//
//
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
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

