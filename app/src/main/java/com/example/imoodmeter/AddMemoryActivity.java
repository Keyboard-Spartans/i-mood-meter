package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;
import com.google.android.material.textfield.TextInputLayout;

public class AddMemoryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout titleView;
    private TextInputLayout descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_memory);

        titleView = findViewById(R.id.title);
        descriptionView = findViewById(R.id.description);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_button) {
            String title = titleView.getEditText().getText().toString();
            String description = descriptionView.getEditText().getText().toString();
            MemoryController.addMemory(new MemoryModel(title, description, null));
            finish();
        }
    }
}