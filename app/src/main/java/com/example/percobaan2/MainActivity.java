package com.example.percobaan2;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the ActivityLogin after the delay
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Finish MainActivity so it's removed from the back stack
            }
        }, 6000); // 3000 milliseconds = 3 seconds
    }
}
