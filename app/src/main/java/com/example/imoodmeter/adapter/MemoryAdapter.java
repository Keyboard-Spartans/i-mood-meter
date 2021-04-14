package com.example.imoodmeter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imoodmeter.R;
import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleView;
        public final TextView descriptionView;

        public ViewHolder(View view) {
            super(view);

            titleView = view.findViewById(R.id.title);
            descriptionView = view.findViewById(R.id.description);
        }

        public void setTitle(String title) {
            titleView.setText(title);
        }

        public void setDescription(String description) {
            descriptionView.setText(description);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                  .inflate(R.layout.single_memory_compact, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MemoryModel memory = MemoryController.getMemories().get(position);
        viewHolder.setTitle(memory.getTitle());
        viewHolder.setDescription(memory.getDescription());
    }

    @Override
    public int getItemCount() {
        return MemoryController.getMemories().size();
    }
}
