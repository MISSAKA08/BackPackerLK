package com.example.backpackerlk;

import static com.example.backpackerlk.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backpackerlk.Activities.Categories;
import com.example.backpackerlk.Activities.Home;
import com.example.backpackerlk.Activities.WhoAreYou;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Dashboard extends AppCompatActivity {

    private Button addevent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //add event button
        addevent = findViewById(R.id.addEventButton);

        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Add_Business.class);
                startActivity(intent);
            }
        });

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Enable full-screen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile); // Adjust as needed

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            } else if (itemId == R.id.nav_categories) {
                startActivity(new Intent(getApplicationContext(), Categories.class));
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(getApplicationContext(), WhoAreYou.class));
            }

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        });

        // ScrollView hide/show bottom navigation
        ScrollView scrollView = findViewById(R.id.main1);
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > oldScrollY) {
                // Scrolling down - hide navigation
                bottomNavigationView.animate().alpha(0f).setDuration(200).withEndAction(() ->
                        bottomNavigationView.setVisibility(View.GONE));
            } else {
                // Scrolling up - show navigation
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.animate().alpha(1f).setDuration(200).start();
            }
        });
    }

    // **BACK BUTTON IN PHONE**
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Go to the previous activity
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // Optional transition
    }
}
