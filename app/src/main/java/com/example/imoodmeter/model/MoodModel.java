package com.example.imoodmeter.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoodModel {
    private final int moodValue;
    private final String moodDescription;
    private final LocalDateTime moodTimeRecorded;

    public MoodModel(int mood_value, String mood_description, LocalDateTime mood_time_recorded) {
        moodValue = mood_value;
        moodDescription = mood_description;
        moodTimeRecorded = mood_time_recorded;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String print(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = moodTimeRecorded.format(formatter);

        return String.format("Mood Entry: %d %s %s",moodValue, moodDescription, formattedDateTime);
    }

    public int getMoodValue() { return moodValue; }
    public String getMoodDescription() { return moodDescription; }
    public LocalDateTime getMoodTimeRecorded() { return moodTimeRecorded; }
}
