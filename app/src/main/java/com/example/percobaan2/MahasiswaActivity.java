package com.example.percobaan2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MahasiswaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mahasiswa);

        // Handling insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mengambil ListView dari layout
        list = findViewById(R.id.listMhs);

        // Mengambil data dari string resources (R.array.daftar_mhs)
        adapter = ArrayAdapter.createFromResource(this, R.array.daftar_mhs, android.R.layout.simple_list_item_1);

        // Set adapter ke ListView
        list.setAdapter(adapter);

        // Set listener untuk klik item pada ListView
        list.setOnItemClickListener(this);
    }
    // Override metode onItemClick
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Ambil item yang diklik berdasarkan posisinya
        String selectedItem = adapterView.getItemAtPosition(position).toString();

        // Tampilkan toast dengan nama item yang diklik
        Toast.makeText(MahasiswaActivity.this, "Saya adalah " + selectedItem, Toast.LENGTH_SHORT).show();
    }
}