package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.example.imoodmeter.adapter.MemoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewMemoriesActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    MemoryAdapter mMemoriesAdapter = new MemoryAdapter();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_memories);

        // Put the memories in the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mMemoriesAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Link to add memory screen
        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        // Enable search
        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMemoriesAdapter.reset();
        searchView.setQuery("", false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            Intent intent = new Intent(this, AddMemoryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mMemoriesAdapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mMemoriesAdapter.filter(newText);
        return true;
    }
}