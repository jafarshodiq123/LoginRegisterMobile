package com.example.percobaan2;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView List;
    private ArrayAdapter<CharSequence> arrayAdapter;
    SharedPreferences preferences;
    private TextView textViewFullname ;
    private TextView  textViewEmail;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textViewFullname = view.findViewById(R.id.textnama1);
        textViewEmail = view.findViewById(R.id.textemail1);

        // Mengambil SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        // Mengambil data dari SharedPreferences
        String userFullname = sharedPreferences.getString("fullname", "Default Name");
        String userEmail = sharedPreferences.getString("email", "Default Email");

        textViewFullname.setText("Selamat datang " +userFullname);
        textViewEmail.setText(userEmail);

        // Perbaiki inisialisasi ListView
        List = view.findViewById(R.id.listMhs);
        arrayAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.daftar_mhs, android.R.layout.simple_list_item_1);
        List.setAdapter(arrayAdapter);
        List.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Implementasikan logika untuk item click di sini
        int position;
        String selectedItem = adapterView.getItemAtPosition(i).toString();

        // Tampilkan toast dengan nama item yang diklik
        Toast.makeText(requireContext(),"Saya adalah " + selectedItem, Toast.LENGTH_SHORT).show();
    }
}
