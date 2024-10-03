package com.example.percobaan2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotificationFragment extends Fragment {
    private TextView textViewFullname;
    private TextView textViewEmail;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        // Inisialisasi TextView
        textViewFullname = view.findViewById(R.id.textnama);
        textViewEmail = view.findViewById(R.id.textemail);

        // Mengambil SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        // Mengambil data dari SharedPreferences
        String userFullname = sharedPreferences.getString("fullname", "Default Name");
        String userEmail = sharedPreferences.getString("email", "Default Email");

        // Mengatur teks ke TextView
        textViewFullname.setText("Selamat datang" +userFullname);
        textViewEmail.setText(userEmail);

        return view;
    }
}
