package com.example.imoodmeter.model;

public class MemoryModel {
    private final String mTitle;
    private final String mDescription;

    public MemoryModel(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
