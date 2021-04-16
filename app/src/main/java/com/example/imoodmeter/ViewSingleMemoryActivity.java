package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;

public class ViewSingleMemoryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_memory_detail);

        TextView titleView = findViewById(R.id.title);
        TextView descriptionView = findViewById(R.id.description);

        Intent intent = getIntent();
        MemoryModel memory = MemoryController.getMemories().get(intent.getIntExtra("position", 0));
        titleView.setText(memory.getTitle());
        descriptionView.setText(memory.getDescription());

        Button closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}