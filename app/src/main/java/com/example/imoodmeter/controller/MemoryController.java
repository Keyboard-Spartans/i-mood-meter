package com.example.imoodmeter.controller;

import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.imoodmeter.R;
import com.example.imoodmeter.model.MemoryModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryController {
    private ArrayList<MemoryModel> memories = new ArrayList<>();

    private static MemoryController singletonController;

    private MemoryController() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<MemoryModel> getMemories() {
        initialize();
        return Collections.unmodifiableList(singletonController.memories);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void addMemory(MemoryModel memory) {
        initialize();
        singletonController.memories.add(0, memory);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void initialize() {
        if (singletonController != null) {
            return;
        }

        singletonController = new MemoryController();

        // Add some dummy data
        Uri defaultUri = Uri.parse("android.resource://com.example.imoodmeter/" + R.drawable.upload_icon);
        singletonController.memories.add(new MemoryModel("Hello world 1", "Lorem ipsum dolor", defaultUri,
                LocalDateTime.of(2021, 3, 18, 12, 30)));
        singletonController.memories.add(new MemoryModel("Hello world 2", "Some other description", defaultUri,
                LocalDateTime.of(2021, 3, 19, 16, 10)));
        singletonController.memories.add(new MemoryModel("Hello world 3", "This marks the last!", defaultUri,
                LocalDateTime.of(2021, 3, 20, 8, 50)));
    }
}
