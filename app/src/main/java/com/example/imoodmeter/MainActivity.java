package com.example.imoodmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        // Landing page is record mood page
        bottomNavigation.setSelectedItemId(R.id.record_page);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class<? extends androidx.fragment.app.Fragment> fragmentClass;

        if (item.getItemId() == R.id.record_page) {
            fragmentClass = BlankFragment.class; // TODO: replace with record page fragment
        } else if (item.getItemId() == R.id.summary_page) {
            fragmentClass = BlankFragment.class; // TODO: replace with summary page fragment
        } else if (item.getItemId() == R.id.memories_page) {
            fragmentClass = ViewMemoriesFragment.class;
        } else {
            return false;
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragmentClass, null)
                .commit();
        return true;
    }
}