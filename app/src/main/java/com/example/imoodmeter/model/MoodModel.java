package com.example.imoodmeter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MoodModel {
    private final int moodValue;
    private final String moodDescription;
    private final LocalDateTime moodTimeRecorded;

    public MoodModel(int mood_value, String mood_description, LocalDateTime mood_time_recorded) {
        moodValue = mood_value;
        moodDescription = mood_description;
        moodTimeRecorded = mood_time_recorded;
    }

    public int getMoodValue() { return moodValue; }
    public String getMoodDescription() { return moodDescription; }
    public LocalDateTime getMoodTimeRecorded() { return moodTimeRecorded; }
}
