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
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import java.util.ArrayList;

    public class DashboardFragment extends Fragment {
        private RecyclerView recyclerView;
        private  DashboardAdapter adapter;
        private ArrayList<String>list;
        SharedPreferences sharedPreferences;
        private TextView textViewfullname;
        private TextView textViewemail;
        @Nullable

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
             textViewfullname = view.findViewById(R.id.textnama2);
             textViewemail = view.findViewById(R.id.textemail2);

        // Mengambil SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        // Mengambil data dari SharedPreferences
        String userFullname = sharedPreferences.getString("fullname", "Default Name");
        String userEmail = sharedPreferences.getString("email", "Default Email");

        textViewfullname.setText("Selamat datang " +userFullname);
        textViewemail.setText(userEmail);
            recyclerView = view.findViewById(R.id.rcycleview);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            list = new ArrayList<>();
            list.add("ahmad");
            list.add("nurul");
            list.add("mamang");

            adapter = new DashboardAdapter(list);
            recyclerView.setAdapter(adapter);

            return view;
        }

    }
