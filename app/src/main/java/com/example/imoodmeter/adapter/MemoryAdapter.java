package com.example.imoodmeter.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imoodmeter.R;
import com.example.imoodmeter.ViewSingleMemoryActivity;
import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {
    List<MemoryModel> memories = MemoryController.getMemories();

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleView;
        private final TextView descriptionView;
        private final ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            titleView = v.findViewById(R.id.title);
            descriptionView = v.findViewById(R.id.description);
            imageView = v.findViewById(R.id.image);

            v.setOnClickListener(this);
        }

        public void setTitle(String title) {
            titleView.setText(title);
        }

        public void setDescription(String description) {
            descriptionView.setText(description);
        }

        public void setImage(Uri imageUri) {
            if (imageUri != null) {
                imageView.setImageURI(imageUri);
            } else {
                imageView.setImageDrawable(null);
            }
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
        MemoryModel memory = memories.get(position);
        viewHolder.setTitle(memory.getTitle());
        viewHolder.setDescription(memory.getDescription());
        viewHolder.setImage(memory.getImageUri());
    }

    @Override
    public int getItemCount() {
        return memories.size();
    }

    public void filter(String keyword) {
        Pattern pattern = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE);
        memories = new ArrayList<>();

        for (MemoryModel memory : MemoryController.getMemories()) {
            if (pattern.matcher(memory.getTitle()).find()
                    || pattern.matcher(memory.getDescription()).find()) {
                memories.add(memory);
            }
        }

        notifyDataSetChanged();
    }

    public void reset() {
        memories = MemoryController.getMemories();
        notifyDataSetChanged();
    }
}
