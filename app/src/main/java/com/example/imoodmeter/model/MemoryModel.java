package com.example.imoodmeter.model;

import android.net.Uri;

import java.time.LocalDateTime;

public class MemoryModel {
    private final String mTitle;
    private final String mDescription;
    private final Uri mImageUri;
    private final LocalDateTime mTimeAdded;

    public MemoryModel(String title, String description, Uri imageUri, LocalDateTime timeAdded) {
        mTitle = title;
        mDescription = description;
        mImageUri = imageUri;
        mTimeAdded = timeAdded;
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

    public LocalDateTime getTimeRecorded() {
        return mTimeAdded;
    }
}
