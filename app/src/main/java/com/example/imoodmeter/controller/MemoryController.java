package com.example.imoodmeter.controller;

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
        singletonController.memories.add(new MemoryModel("Hello world 1", "Lorem ipsum dolor"));
        singletonController.memories.add(new MemoryModel("Hello world 2", "Some other description"));
        singletonController.memories.add(new MemoryModel("Hello world 3", "This marks the last!"));
    }
}
