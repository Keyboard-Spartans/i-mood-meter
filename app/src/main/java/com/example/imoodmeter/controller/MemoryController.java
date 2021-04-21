package com.example.imoodmeter.controller;

import android.net.Uri;

import com.example.imoodmeter.R;
import com.example.imoodmeter.model.MemoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryController {
    private ArrayList<MemoryModel> memories = new ArrayList<>();

    private static MemoryController singletonController;

    private MemoryController() {}

    public static List<MemoryModel> getMemories() {
        initialize();
        return Collections.unmodifiableList(singletonController.memories);
    }

    public static void addMemory(MemoryModel memory) {
        initialize();
        singletonController.memories.add(0, memory);
    }

    private static void initialize() {
        if (singletonController != null) {
            return;
        }

        singletonController = new MemoryController();

        // Add some dummy data
        Uri defaultUri = Uri.parse("android.resource://com.example.imoodmeter/" + R.drawable.upload_icon);
        singletonController.memories.add(new MemoryModel("Hello world 1", "Lorem ipsum dolor", defaultUri));
        singletonController.memories.add(new MemoryModel("Hello world 2", "Some other description", defaultUri));
        singletonController.memories.add(new MemoryModel("Hello world 3", "This marks the last!", defaultUri));
    }
}
