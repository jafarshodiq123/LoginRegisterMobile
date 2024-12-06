package com.example.percobaan2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotificationFragment extends Fragment {
    private TextView textViewFullname;
    private TextView textViewEmail;
    private SharedPreferences sharedPreferences;
    private Button Logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        // Inisialisasi TextView
        textViewFullname = view.findViewById(R.id.textnama);
        textViewEmail = view.findViewById(R.id.textemail);
        Logout = view.findViewById(R.id.logout);

        // Mengambil SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("Setting", Context.MODE_PRIVATE);

        // Mengambil data dari SharedPreferences
        String userFullname = sharedPreferences.getString("fullname", "Default Name");
        String userEmail = sharedPreferences.getString("email", "Default Email");

        // Mengatur teks ke TextView
        textViewFullname.setText("Selamat datang, " + userFullname);
        textViewEmail.setText(userEmail);

        // Set onClickListener untuk tombol logout
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengubah status login di SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("islogin", false);
                editor.apply();

                // Mengarahkan ke ActivityLogin
                Intent intent = new Intent(requireActivity(),ActivityLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Menambahkan flag untuk membersihkan stack aktivitas
                startActivity(intent);
                getActivity().finish(); // Menutup aktivitas yang sedang berjalan
            }
        });

        return view;
    }
}
