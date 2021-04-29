package com.example.imoodmeter;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imoodmeter.controller.MemoryController;
import com.example.imoodmeter.model.MemoryModel;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;

public class AddMemoryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout titleView;
    private TextInputLayout descriptionView;
    private Button uploadButton;
    private Uri imageUri;

    private final ActivityResultLauncher<String[]> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.OpenDocument(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri == null) {
                        return;
                    }

                    imageUri = uri;
                    getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    uploadButton.setText(R.string.reselect);
                    LinearLayout selectedLayout = findViewById(R.id.selected_info);
                    selectedLayout.setVisibility(View.VISIBLE);

                    ImageView thumbnailView = findViewById(R.id.thumbnail);
                    thumbnailView.setImageURI(imageUri);
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeAppTitle();

        setContentView(R.layout.add_memory);

        titleView = findViewById(R.id.title);
        descriptionView = findViewById(R.id.description);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

        uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(this);

        Button closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_button) {
            String title = titleView.getEditText().getText().toString();
            String description = descriptionView.getEditText().getText().toString();
            MemoryController.addMemory(new MemoryModel(title, description, imageUri, LocalDateTime.now()));
            finish();
        } else if (v.getId() == R.id.upload_button) {
            getContentLauncher.launch(new String[]{"image/*"});
        } else if (v.getId() == R.id.close_button) {
            finish();
        }
    }

    public void removeAppTitle(){
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException ignored) {}
    }

}