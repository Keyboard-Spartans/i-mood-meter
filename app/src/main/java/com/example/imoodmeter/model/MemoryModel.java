package com.example.imoodmeter.model;

import android.net.Uri;

public class MemoryModel {
    private final String mTitle;
    private final String mDescription;
    private final Uri mImageUri;

    public MemoryModel(String title, String description, Uri imageUri) {
        mTitle = title;
        mDescription = description;
        mImageUri = imageUri;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Uri getImageUri() {
        return mImageUri;
    }
}
