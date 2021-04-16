package com.example.imoodmeter.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imoodmeter.R;
import com.example.imoodmeter.ViewSingleMemoryActivity;
import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView titleView;
        public final TextView descriptionView;

        public ViewHolder(View v) {
            super(v);

            titleView = v.findViewById(R.id.title);
            descriptionView = v.findViewById(R.id.description);

            v.setOnClickListener(this);
        }

        public void setTitle(String title) {
            titleView.setText(title);
        }

        public void setDescription(String description) {
            descriptionView.setText(description);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ViewSingleMemoryActivity.class);
            intent.putExtra("position", getAdapterPosition());
            v.getContext().startActivity(intent);
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
