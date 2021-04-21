package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import com.example.imoodmeter.adapter.MemoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewMemoriesFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    MemoryAdapter mMemoriesAdapter = new MemoryAdapter();
    SearchView searchView;

    public ViewMemoriesFragment() {
        super(R.layout.view_memories);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Put the memories in the recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mMemoriesAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Link to add memory screen
        FloatingActionButton addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        // Enable search
        searchView = view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMemoriesAdapter.reset();
        searchView.setQuery("", false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            Intent intent = new Intent(this.getContext(), AddMemoryActivity.class);
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